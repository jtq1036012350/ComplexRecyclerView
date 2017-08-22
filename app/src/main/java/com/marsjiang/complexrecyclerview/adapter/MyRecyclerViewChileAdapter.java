package com.marsjiang.complexrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marsjiang.complexrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的Adapter
 * Created by Jiang on 2017-07-17.
 */

public class MyRecyclerViewChileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义常用的参数
    private Context ctx;
    private ArrayList<String> arrayList;
    private List<Integer> mHeights = new ArrayList<>();

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
        private ImageView home_read_piv_iv;

        public ViewHolder1(View itemView) {
            super(itemView);
            home_read_title = (TextView) itemView.findViewById(R.id.home_read_title);
            home_read_piv_iv = (ImageView) itemView.findViewById(R.id.home_read_piv_iv);

            home_read_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setData(int position) {
            if (mHeights.size() <= getItemCount() + 2) {
                //这里只是随机数模拟瀑布流， 实际过程中， 应该根据图片高度来实现瀑布流
                mHeights.add((int) (500 + Math.random() * 400));
            }

            ViewGroup.LayoutParams layoutParams = home_read_piv_iv.getLayoutParams();
            if (mHeights.size() > position)
                //此处取得随机数，如果mheight里面有数则取， 没有则邹走else
                layoutParams.height = mHeights.get(position);
            else layoutParams.height = 589;
            home_read_piv_iv.setLayoutParams(layoutParams);

            home_read_title.setText(arrayList.get(position) + "啊哈哈哈哈哈哈");
        }
    }

}
