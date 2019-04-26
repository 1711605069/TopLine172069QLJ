package com.example.topline172069qlj.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.topline172069qlj.R;
import com.example.topline172069qlj.bean.NewsBean;

public class AdBannerFragment extends Fragment {
    private NewsBean nb;   //广告
    private ImageView iv;  //图片
    public static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(args);
        return af;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        nb = (NewsBean) arg.getSerializable("ad"); //获取一个新闻对象
    }
    @Override
    public void onResume() {
        super.onResume();
        if (nb != null) {
            //调用Glide框架加载图片
            Glide
                    .with(getActivity())
                    .load(nb.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);                           //设置图片宽高参数
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }
    }
