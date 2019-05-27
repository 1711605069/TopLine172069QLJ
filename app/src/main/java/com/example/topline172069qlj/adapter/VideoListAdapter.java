package com.example.topline172069qlj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.topline172069qlj.R;
import com.example.topline172069qlj.bean.VideoBean;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VideoBean> videoList;
    private Context context;

    public VideoListAdapter(List<VideoBean> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    public VideoListAdapter(Context context){
        this.context=context;
    }
    public void setData(List<VideoBean> videoList){
        this.videoList=videoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_item
        ,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
public ImageView iv_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img_round);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
   final VideoBean bean=videoList.get(i);
        System.out.println("aa  "+bean.getImg());
        Glide
                .with(context)
                .load(bean.getImg())
                .error(R.mipmap.ic_launcher)
                .into(((ViewHolder) viewHolder).iv_img);
    }

    @Override
    public int getItemCount() {
        return videoList==null ? 0:videoList.size();
    }
}
