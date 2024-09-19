package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Frame;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.ViewHolder> {

    ArrayList<Frame> list;
    Activity activity;
    String Selection = "0";

    public FrameAdapter(Activity activity, ArrayList<Frame> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_frame, parent, false);
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

        holder.setIsRecyclable(false);
        if(position == 0)
        {
            holder.txtNone.setVisibility(View.VISIBLE);
            holder.imageView.setBackgroundColor(Color.WHITE);
        }
        else
        {
            holder.txtNone.setVisibility(View.GONE);
            Glide.with(activity).load(list.get(position).getIcon()).into(holder.imageView);
        }
    }

    public void selection(String pos) {
        Selection = pos;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNone;
        ImageView imageView;
        RelativeLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            border = (RelativeLayout) itemView.findViewById(R.id.border);
            txtNone = (TextView) itemView.findViewById(R.id.txtNone);
        }
    }
}