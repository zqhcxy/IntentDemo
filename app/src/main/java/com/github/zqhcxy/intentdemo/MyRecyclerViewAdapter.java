package com.github.zqhcxy.intentdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zqh-pc on 2016/4/12.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mList;
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;


    public MyRecyclerViewAdapter(List<String> list) {
        mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mList != null)
            holder.recycler_content.setText(mList.get(position));
        holder.recycler_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(v,mList.get(position));
            }
        });
        holder.recycler_ly.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickListener != null)
                    onLongClickListener.onClick(v,mList.get(position));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recycler_content;
        private LinearLayout recycler_ly;

        public ViewHolder(View itemView) {
            super(itemView);
            recycler_content = (TextView) itemView.findViewById(R.id.recycler_content);
            recycler_ly = (LinearLayout) itemView.findViewById(R.id.recycler_ly);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v,String note);
    }

    public interface OnLongClickListener {
        void onClick(View v,String note);
    }

}
