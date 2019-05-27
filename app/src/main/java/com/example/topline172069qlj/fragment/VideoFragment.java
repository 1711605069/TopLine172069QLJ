package com.example.topline172069qlj.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.topline172069qlj.R;
import com.example.topline172069qlj.adapter.VideoListAdapter;
import com.example.topline172069qlj.bean.VideoBean;
import com.example.topline172069qlj.utis.Constant;
import com.example.topline172069qlj.utis.JsonParse;
import com.example.topline172069qlj.view.WrapRecyclerView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private MHandler mHandler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WrapRecyclerView recyclerView;
    private VideoListAdapter adapter;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = initView(inflater, container);
        mHandler = new MHandler();
        initData();//调用
        return view;
    }

    private void initData() {//使用OkHttpClient读取数据
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SLTE + Constant.REQUEST_VIDEO_URL).build();
        Call call = okHttpClient.newCall(request);
        //开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
//                Log.i("videoString",res);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
        });
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout = view.findViewById(R.id.pull_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        initData();
                    }
                }, 1000);
            }
        });
        return view;
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        List<VideoBean> videoList = JsonParse.getInstance().getVideoList(vlResult);
                        //Toast.makeText(getContext(), String.valueOf(videoList.size()), Toast.LENGTH_SHORT).show();

                        adapter = new VideoListAdapter(videoList,getActivity());
                        recyclerView.setAdapter(adapter);
                    }
                    break;
            }
        }
    }

}
