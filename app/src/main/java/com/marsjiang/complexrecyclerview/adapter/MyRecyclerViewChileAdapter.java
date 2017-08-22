package com.marsjiang.complexrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marsjiang.complexrecyclerview.R;

import java.util.ArrayList;

/**
 * RecyclerView的Adapter
 * Created by Jiang on 2017-07-17.
 */

public class MyRecyclerViewChileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义常用的参数
    private Context ctx;
    private ArrayList<String> arrayList;

    public MyRecyclerViewChileAdapter(Context ctx, ArrayList<String> arrayList) {
        this.ctx = ctx;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new ViewHolder1(View.inflate(ctx, R.layout.item4_layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder1) holder).setData(position);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    //为每种布局定义自己的ViewHolder
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private TextView home_read_title;

        public ViewHolder1(View itemView) {
            super(itemView);
            home_read_title = (TextView) itemView.findViewById(R.id.home_read_title);
        }

        public void setData(int position) {
            home_read_title.setText(arrayList.get(position) + "啊哈哈哈哈哈哈");
        }
    }

}
