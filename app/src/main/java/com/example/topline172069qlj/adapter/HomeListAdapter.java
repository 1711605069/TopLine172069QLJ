package com.example.topline172069qlj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.topline172069qlj.R;
import com.example.topline172069qlj.bean.NewsBean;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private List<NewsBean> newsList;//新闻数据集合类
   private static final int TYPE_ONE=1;//一个图片的样式
   private static final int TYPE_TWO=2;//一个图片的样式
   private Context context;//上下文，用于Glide
    public HomeListAdapter(Context context){
        this.context=context;
    }
    public void setData(List<NewsBean> newsList){//设置数据
        this.newsList=newsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==TYPE_TWO){
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.home_item_two,viewGroup,false
            );//加载项目列表布局文件
            TypeTwoViewHolder viewHolder=new TypeTwoViewHolder(view);
            return viewHolder;
        }else {
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.home_item_one,viewGroup,false
            );
            TypeOneViewHolder viewHolder=new TypeOneViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
    if (newsList==null)return;
    final NewsBean bean=newsList.get(i);//获取一条数据
        if (viewHolder instanceof TypeOneViewHolder){//类型判断
            ((TypeOneViewHolder) viewHolder).tv_name.setText(bean.getNewsName());
            ((TypeOneViewHolder) viewHolder).te_news_type_name.setText(bean.getNewsTypeName());
            Glide  //加载图片
            .with(context)
                    .load(bean.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeOneViewHolder) viewHolder).iv_img);
        }else if (viewHolder instanceof TypeTwoViewHolder){
            ((TypeTwoViewHolder) viewHolder).tv_name.setText(bean.getNewsName());
            ((TypeTwoViewHolder) viewHolder).tv_news_type_name.setText(bean.getNewsTypeName());
            Glide
                    .with(context)
                    .load(bean.getImg1())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) viewHolder).iv_img1);
            Glide
                    .with(context)
                    .load(bean.getImg2())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) viewHolder).iv_img2);
            Glide
                    .with(context)
                    .load(bean.getImg3())
                    .error(R.mipmap.ic_launcher)
                    .into(((TypeTwoViewHolder) viewHolder).iv_img3);
        }
    }


    @Override
    public int getItemViewType(int position){
        if (1==newsList.get(position).getType()){
            return TYPE_ONE;//一个图片的类型
        }else if (2==newsList.get(position).getType()){
            return TYPE_TWO;//三个图片的类型
        }else {
            return TYPE_ONE;
        }
    }
    @Override
    public int getItemCount() {
        return newsList==null ?0:newsList.size();
    }
    public class TypeOneViewHolder extends RecyclerView.ViewHolder{//单幅图列表项
        public TextView tv_name,te_news_type_name;
        public ImageView iv_img;
        public TypeOneViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            te_news_type_name=(TextView)itemView.findViewById(R.id.tv_newsType_name);
            iv_img=(ImageView)itemView.findViewById(R.id.iv_img);
        }
    }
    public class TypeTwoViewHolder extends RecyclerView.ViewHolder{
         public TextView tv_name,tv_news_type_name;
         public ImageView iv_img1,iv_img2,iv_img3;
        public TypeTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_news_type_name=(TextView)itemView.findViewById(R.id.tv_newsType_name);
            iv_img1=(ImageView) itemView.findViewById(R.id.iv_img1);
            iv_img2=(ImageView)itemView.findViewById(R.id.iv_img2);
            iv_img3=(ImageView)itemView.findViewById(R.id.iv_img3);
        }
    }
}
