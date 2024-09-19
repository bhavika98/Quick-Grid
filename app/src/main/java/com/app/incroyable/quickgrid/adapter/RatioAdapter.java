package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Ratio;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.app.incroyable.quickgrid.util.Constants.normalColor;
import static com.app.incroyable.quickgrid.util.Constants.selectedColor;

public class RatioAdapter extends RecyclerView.Adapter<RatioAdapter.ViewHolder> {

    ArrayList<Ratio> list;
    Activity activity;
    String Selection = "0";

    public RatioAdapter(Activity activity, ArrayList<Ratio> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_ratio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (Selection.equals(""))
        {
            holder.imageView.setColorFilter(ContextCompat.getColor(activity, normalColor));
            holder.textRatio.setTextColor(ContextCompat.getColor(activity, normalColor));
        }
        else
        {
            if (position == Integer.parseInt(Selection))
            {
                holder.imageView.setColorFilter(ContextCompat.getColor(activity, selectedColor));
                holder.textRatio.setTextColor(ContextCompat.getColor(activity, selectedColor));
            }
            else
            {
                holder.imageView.setColorFilter(ContextCompat.getColor(activity, normalColor));
                holder.textRatio.setTextColor(ContextCompat.getColor(activity, normalColor));
            }
        }

        holder.textRatio.setText(list.get(position).getTitle());
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

        TextView textRatio;
        ImageView imageView;
        LinearLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            textRatio = (TextView) itemView.findViewById(R.id.textRatio);
            border = (LinearLayout) itemView.findViewById(R.id.border);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}