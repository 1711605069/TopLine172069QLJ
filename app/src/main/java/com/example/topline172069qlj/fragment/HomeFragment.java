package com.example.topline172069qlj.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.topline172069qlj.R;
import com.example.topline172069qlj.adapter.AdBannerAdapter;
import com.example.topline172069qlj.adapter.HomeListAdapter;
import com.example.topline172069qlj.bean.NewsBean;
import com.example.topline172069qlj.utis.Constant;
import com.example.topline172069qlj.utis.JsonParse;
import com.example.topline172069qlj.utis.UtilsHelper;
import com.example.topline172069qlj.view.WrapRecyclerView;
import com.itheima.PullToRefreshView;
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
public class HomeFragment extends Fragment {
    private SwipeRefreshLayout mPullToRefreshView;
    private RecyclerView recycleView;
    private HomeListAdapter adapter;
    private View adBannerLay;//广告部分外框
    private ViewPager adPager;//广告翻页控件
    private AdBannerAdapter ada;//翻页控件适配器
    private OkHttpClient okHttpClient;//网络访问客户端
    private MHandler mHandler;
    public HomeFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        okHttpClient=new OkHttpClient();
        mHandler=new MHandler();
        getADData();
        View view=initView(inflater,container);
        return view;
    }

    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    if (msg.obj!=null){
                        String adResult=(String) msg.obj;
                        List<NewsBean> ad1= JsonParse.getInstance().
                                getAdList(adResult);
                        if (ad1!=null){
                            if (ad1.size()>0){
                                ada.setData(ad1);
                            }
                        }
                    }
                    break;
            }
        }
    }
    private void getADData() {//读取广告Json数据
        Request request=new Request.Builder().url(Constant.WEB_SLTE
        +Constant.REQUEST_AD_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {//开启异步线程访问网络
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
            String res=response.body().string();
               // Log.i("AD",res);
                Message msg=new Message();
                msg.what=1;
                msg.obj=res;
                mHandler.sendMessage(msg);
            }
        });
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
      View view=inflater.inflate(R.layout.fragment_home,container,false);
      mPullToRefreshView=view.findViewById(R.id.pull_to_refresh);
      recycleView =view.findViewById(R.id.recycler_view);
      recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter=new HomeListAdapter();
      recycleView.setAdapter(adapter);
      View headView=inflater.inflate(R.layout.head_view,container,false);
      //recycleView.addHeaderView(headView);//添加头部
       // adBannerLay=headView.findViewById(R.id.adbanner_layout);
        adPager =(ViewPager) view.findViewById(R.id.slidingAdvertBanner);
        ada=new AdBannerAdapter(getActivity().getSupportFragmentManager());
        adPager.setAdapter(ada);
       // resetSize();
      return view;
    }

    private void resetSize() {
        int sw = UtilsHelper.getScreenWidth(getActivity());//屏幕宽度
        int adLheight = sw / 2; //广告条高度
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();//广告部分外框
        adlp.width = sw;//设置宽度
        adlp.height = adLheight;//设置高度
        adBannerLay.setLayoutParams(adlp);
    }


}
