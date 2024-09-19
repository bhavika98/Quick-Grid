package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.app.incroyable.quickgrid.R;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Integer> list;
    Activity activity;
    String Selection = "";

    public ColorAdapter(Activity activity, ArrayList<Integer> list) {
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_color, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (Selection.equals("")) {
            holder.border.setVisibility(View.GONE);
        } else {
            if (position == Integer.parseInt(Selection))
                holder.border.setVisibility(View.VISIBLE);
            else
                holder.border.setVisibility(View.GONE);
        }

        holder.colorLayout.setBackgroundColor(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout colorLayout;
        RelativeLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            colorLayout = (LinearLayout) itemView.findViewById(R.id.colorLayout);
            border = (RelativeLayout) itemView.findViewById(R.id.border);
        }
    }

    public void setSelection(int position)
    {
        this.Selection = String.valueOf(position);
        notifyDataSetChanged();
    }
}
