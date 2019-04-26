package com.example.topline172069qlj.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.topline172069qlj.bean.NewsBean;
import com.example.topline172069qlj.fragment.AdBannerFragment;

import java.util.ArrayList;
import java.util.List;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<NewsBean> ab1;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        ab1=new ArrayList<NewsBean>();
    }

    public void setData(List <NewsBean> ab1){
        this.ab1=ab1;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        if (ab1.size() > 0)
            args.putSerializable("ad", ab1.get(i % ab1.size()));
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return ab1==null?0:ab1.size();
    }
    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
