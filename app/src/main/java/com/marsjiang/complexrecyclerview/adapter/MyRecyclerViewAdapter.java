package com.marsjiang.complexrecyclerview.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marsjiang.complexrecyclerview.R;
import com.marsjiang.complexrecyclerview.bean.User;
import com.marsjiang.complexrecyclerview.util.CommonUtil;
import com.marsjiang.complexrecyclerview.widget.MyStaggerGrildLayoutManger;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的Adapter
 * Created by Jiang on 2017-07-17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义常用的参数
    private Context ctx;
    private int resourceId;
    //JavaBean
    private List<User> users;
    private LayoutInflater inflater;
    //为三种布局定义一个标识
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private final int TYPE4 = 3;
    private final int TYPE5 = 4;

    private MyRecyclerViewChileAdapter myRecyclerViewChileAdapter;

    public MyRecyclerViewAdapter(Context ctx, List<User> objects) {
        this.ctx = ctx;
        this.users = objects;
        //别忘了初始化inflater
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE1:
                holder = new ViewHolder1(View.inflate(ctx, R.layout.itemlayout1, null));
                break;
            case TYPE2:
                holder = new ViewHolder2(View.inflate(ctx, R.layout.itemlayout2, null));
                break;
            case TYPE3:
                holder = new ViewHolder3(View.inflate(ctx, R.layout.itemlayout3, null));
                break;
            case TYPE4:
                holder = new ViewHolder4(View.inflate(ctx, R.layout.horizontal_crollview_main, null));
                break;
            case TYPE5:
                holder = new ViewHolder5(View.inflate(ctx, R.layout.itemlayout4, null));
                break;
            default:
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //由于木有相关的viewType参数，只能通过方法来获取了
        int viewType = getItemViewType(position);
        if (viewType == -1) {
            return;
        }
        switch (viewType) {
            case TYPE1:
                ((ViewHolder1) holder).setData();
                break;
            case TYPE2:
                ((ViewHolder2) holder).setData(position);
                break;
            case TYPE3:
                ((ViewHolder3) holder).setData(position);
                break;
            case TYPE4:
                ((ViewHolder4) holder).setData((ViewHolder4) holder);
                break;
            case TYPE5:
                ((ViewHolder5) holder).setData(position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        User u = users.get(position);
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局
        Log.i("LHD", u.toString());
        Log.i("LHD", "第一个返回值" + u.getItem1_str());
        Log.i("LHD", "第二个返回值" + u.getItem2_str());
        Log.i("LHD", "第三个返回值" + u.getItem3_str());
        if (u.getItem1_str() != null) {
            return TYPE1;
        } else if (u.getItem2_str() != null) {
            return TYPE2;
        } else if (u.getItem3_str() != null) {//如果前两个字段都为空，那就一定是加载第三个布局啦。
            return TYPE3;
        } else if (u.getItem4_str() != null) {
            return TYPE4;
        } else if (u.getItem5_str() != null) {
            return TYPE5;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    //为每种布局定义自己的ViewHolder
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private ViewPager item1_vp;

        public ViewHolder1(View itemView) {
            super(itemView);
            item1_vp = (ViewPager) itemView.findViewById(R.id.item1_vp);
        }

        public void setData() {
            item1_vp.setAdapter(new ViewPagerAdapter(ctx));
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView item2_tv;

        public ViewHolder2(View itemView) {
            super(itemView);
            item2_tv = (TextView) itemView.findViewById(R.id.item2_tv);
        }

        public void setData(int position) {
            item2_tv.setText(users.get(position).getItem2_str());
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private Button item3_btn;

        public ViewHolder3(View itemView) {
            super(itemView);
            item3_btn = (Button) itemView.findViewById(R.id.item3_btn);
        }

        public void setData(int position) {
            item3_btn.setText(users.get(position).getItem3_str());
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        private LinearLayout ll_main;

        public ViewHolder4(View itemView) {
            super(itemView);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
        }

        public void setData(ViewHolder4 itemHolder4) {
            LinearLayout rl_layout = null;
            TextView tv_title = null;
            TextView tv_sub_title = null;
            ImageView iv_test = null;
            for (int i = 0; i < 10; i++) {
                View view = LayoutInflater.from(ctx).inflate(R.layout.scroll_item_layout, null);
                int screenWidth = CommonUtil.getScreenWidth(ctx);
                int screenHeight = CommonUtil.getScreenHeight(ctx);
                //根据屏幕宽度设定横向滑动子View的宽度
                view.setLayoutParams(new LinearLayout.LayoutParams(screenWidth / 2, 120));
                rl_layout = (LinearLayout) view.findViewById(R.id.rl_layout);
                tv_title = (TextView) view.findViewById(R.id.tv_title);
                tv_sub_title = (TextView) view.findViewById(R.id.tv_sub_title);
                tv_sub_title = (TextView) view.findViewById(R.id.tv_sub_title);
                iv_test = (ImageView) view.findViewById(R.id.iv_test);

                tv_title.setText("主标题" + i);
                tv_sub_title.setText("子标题" + i);
                iv_test.setImageResource(R.mipmap.ic_launcher);

                final int finalI = i;
                rl_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ctx, "view" + finalI, Toast.LENGTH_SHORT).show();
                    }
                });

                itemHolder4.ll_main.addView(view);
            }
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private ArrayList<String> arr;

        public ViewHolder5(View itemView) {
            super(itemView);
            arr = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                arr.add(i + "");
            }
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
        }

        public void setData(int position) {
            //初始化recycleview 的适配器 以及布局管理器， 这里的管理器用的是流式布局，至于为何是流式布局，
            // 博客以及github上面有比较详细的讲解，欢迎查看，

            myRecyclerViewChileAdapter = new MyRecyclerViewChileAdapter(ctx, arr);
            recyclerView.setAdapter(myRecyclerViewChileAdapter);
            recyclerView.setLayoutManager(new MyStaggerGrildLayoutManger(ctx, 2, StaggeredGridLayoutManager.VERTICAL));
        }
    }

}
