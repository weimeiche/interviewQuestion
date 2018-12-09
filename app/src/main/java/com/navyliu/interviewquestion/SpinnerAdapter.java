package com.navyliu.interviewquestion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
    private ArrayList<SpinnerBean> mList = new ArrayList<SpinnerBean>();
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener = null;
    private Context mContext;

    public SpinnerAdapter(Context context, ArrayList<SpinnerBean> mList) {
        mContext = context;
        this.mList = mList;
    }

    // 创建新的view， 被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // 将数据与界面进行绑定操作
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(mList.get(position).getItemStr());
        // 将创建的view注册点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerItemClickListener.onItemClick(holder.itemView, position);
            }
        });
    }


    // 获取数据的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }


    // 自定义的viewholder，持有每个item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textview);
        }
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnRecyclerItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, int position);
    }
}

