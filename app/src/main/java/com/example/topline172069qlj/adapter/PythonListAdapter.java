package com.example.topline172069qlj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.topline172069qlj.R;
import com.example.topline172069qlj.bean.PythonBean;

import java.util.List;

public class PythonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PythonBean> pbl;//列表数据
    public void setData(List<PythonBean> pbl) {//初始化数据
        this.pbl = pbl;
        notifyDataSetChanged();
    }
    @NonNull
    @Override//创建视图
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.python_list_item, viewGroup, false);
        PythonViewHolder viewHolder = new PythonViewHolder(view);
        return viewHolder;
    }

    @Override//绑定数据
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final PythonBean bean = pbl.get(i);
        ((PythonViewHolder) viewHolder).tv_address.setText(bean.getAddress());
        ((PythonViewHolder) viewHolder).tv_content.setText(bean.getContent());
    }

    @Override//数据数量
    public int getItemCount() {
        return pbl == null ? 0 : pbl.size();
    }
    public class PythonViewHolder extends RecyclerView.ViewHolder {//获取布局文件的控件
        private TextView tv_address, tv_content;
        public ImageView iv_img;
        public PythonViewHolder(View itemView) {
            super(itemView);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_fire);
        }
    }
}
