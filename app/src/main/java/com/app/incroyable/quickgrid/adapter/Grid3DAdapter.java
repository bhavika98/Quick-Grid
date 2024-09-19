package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Grid3D;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Grid3DAdapter extends RecyclerView.Adapter<Grid3DAdapter.ViewHolder> {

    ArrayList<Grid3D> list;
    Activity activity;
    int Selection = 0;
    Grid3DCallback grid3DCallback;

    public Grid3DAdapter(Activity activity, ArrayList<Grid3D> list, int currentGrid) {
        this.list = list;
        this.activity = activity;
        Selection = currentGrid;
        this.grid3DCallback = (Grid3DCallback) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_grid3d, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(Selection == list.get(position).getId())
            holder.border.setVisibility(View.VISIBLE);
        else
            holder.border.setVisibility(View.GONE);

        holder.setIsRecyclable(false);
        RelativeLayout.LayoutParams layoutParams;
        int dp = 70;
        int pixels = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics()));
        if(list.get(position).getSize() == 1)
        {
            layoutParams = new RelativeLayout.LayoutParams(pixels, pixels);
        }
        else
        {
            layoutParams = new RelativeLayout.LayoutParams(pixels, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        holder.imageView.setLayoutParams(layoutParams);
        holder.border.setLayoutParams(layoutParams);

        Glide.with(activity).load(list.get(position).getThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Selection = list.get(position).getId();
                notifyDataSetChanged();

                grid3DCallback.Grid3DMethod(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageView border;
        RelativeLayout mainLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            border = (ImageView) itemView.findViewById(R.id.border);
            mainLayout = (RelativeLayout) itemView.findViewById(R.id.mainLayout);
        }
    }

    public interface Grid3DCallback{
        void Grid3DMethod(int position);
    }
}