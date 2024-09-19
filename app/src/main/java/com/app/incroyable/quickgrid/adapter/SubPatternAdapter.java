package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.incroyable.quickgrid.R;
import com.bumptech.glide.Glide;

public class SubPatternAdapter extends RecyclerView.Adapter<SubPatternAdapter.ViewHolder> {

    int[] list;
    Activity activity;
    String Selection = "";

    public SubPatternAdapter(Activity activity, int[] list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_sub_pattern, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (Selection.equals("")) {
            holder.border.setVisibility(View.INVISIBLE);
        } else {
            if (position == Integer.parseInt(Selection))
                holder.border.setVisibility(View.VISIBLE);
            else
                holder.border.setVisibility(View.INVISIBLE);
        }

        Glide.with(activity).load(list[position]).into(holder.imageView);
    }

    public void selection(String pos) {
        Selection = pos;
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        RelativeLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            border = (RelativeLayout) itemView.findViewById(R.id.border);
        }
    }
}