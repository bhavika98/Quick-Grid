package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Pattern;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PatternAdapter extends RecyclerView.Adapter<PatternAdapter.ViewHolder> {

    ArrayList<Pattern> list;
    Activity activity;
    String Selection = "";

    public PatternAdapter(Activity activity, ArrayList<Pattern> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_pattern, parent, false);
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

        holder.txtTitle.setText(list.get(position).getTitle());
        Glide.with(activity).load(list.get(position).getImage()).into(holder.imageView);
    }

    public void selection(String pos) {
        Selection = pos;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtTitle;
        RelativeLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            border = (RelativeLayout) itemView.findViewById(R.id.border);
        }
    }
}