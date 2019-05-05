package com.example.topline172069qlj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topline172069qlj.R;
import com.example.topline172069qlj.adapter.PythonListAdapter;
import com.example.topline172069qlj.bean.PythonBean;
import com.example.topline172069qlj.fragment.HomeFragment;
import com.example.topline172069qlj.utis.Constant;
import com.example.topline172069qlj.utis.JsonParse;
import com.example.topline172069qlj.view.WrapRecyclerView;
import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class PythonActivity extends AppCompatActivity {
    private SwipeRefreshLayout mPullToRefreshView;
    private RecyclerView recyclerView;
    public static final int REFRESH_DELAY = 1000;
    private MHandler mHandler;
    private PythonListAdapter adapter;
    private TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_python);
        mHandler=new MHandler();
        initData();
        initView();

    }
class MHandler extends Handler{
        @Override
    public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (msg.obj!=null){
                        String vlResult = (String) msg.obj;
                        //使用Gson解析数据
                        List<PythonBean> pythonList = JsonParse.getInstance().getPythonList(vlResult);
                        //Toast.makeText(getApplicationContext(),String.valueOf(pythonList.size()),Toast.LENGTH_LONG).show();
                     adapter.setData(pythonList);//适配器数据赋值
                    }
                    break;
            }
        }
}
    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(Constant.WEB_SLTE+Constant.REQUEST_PYTHON_URL).build();
        Call call=okHttpClient.newCall(request);
        //开房异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res=response.body().string();
               // Log.i("Python",res);
                Message msg=new Message();
                msg.what=1;
                msg.obj=res;
                mHandler.sendMessage(msg);
            }
        });
    }

    private void initView() {
        mPullToRefreshView=findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.setRefreshing(false);
                initData();
            }
        });
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PythonListAdapter();
        recyclerView.setAdapter(adapter);


        tv_back=findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PythonActivity.this.finish();
            }
        });
    }

}
