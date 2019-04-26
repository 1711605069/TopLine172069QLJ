package com.example.topline172069qlj.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.topline172069qlj.adapter.WrapAdapter;

import java.util.ArrayList;

public class WrapRecyclerView extends RecyclerView {
    private WrapAdapter mwrapAdapter;
    private ArrayList<View> mTmpHeaderView=new ArrayList<>();

    public WrapRecyclerView(@NonNull Context context, WrapAdapter mwrapAdapter) {
        super(context);
        this.mwrapAdapter = mwrapAdapter;
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, WrapAdapter mwrapAdapter) {
        super(context, attrs);
        this.mwrapAdapter = mwrapAdapter;
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle, WrapAdapter mwrapAdapter) {
        super(context, attrs, defStyle);
        this.mwrapAdapter = mwrapAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter){
        if(adapter instanceof  WrapAdapter){
            mwrapAdapter=(WrapAdapter) adapter;
            super.setAdapter(adapter);
        }else {
            mwrapAdapter=new WrapAdapter(adapter);
            for (View view:mTmpHeaderView){
                mwrapAdapter.addHeaderView(view);
            }
            if (mTmpHeaderView.size() > 0){
                mTmpHeaderView.clear();
            }
            super.setAdapter(mwrapAdapter);
        }

    }
    public void addHeaderView(View view){
        if (null==view){
            throw new IllegalArgumentException("the view to add must not be null!");
        }else if (mwrapAdapter==null){
            mTmpHeaderView.add(view);
        }else {
            mwrapAdapter.addHeaderView(view);
        }
    }
}
