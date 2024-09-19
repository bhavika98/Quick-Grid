package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Grid;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.app.incroyable.quickgrid.util.Constants.selectedColor;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    ArrayList<Grid> list;
    Activity activity;
    String Selection = "0";
    GridCallback gridCallback;

    public GridAdapter(Activity activity, ArrayList<Grid> list) {
        this.list = list;
        this.activity = activity;
        this.gridCallback = (GridCallback) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (position == Integer.parseInt(Selection))
            holder.imageView.setColorFilter(ContextCompat.getColor(activity, selectedColor));
        else
            holder.imageView.setColorFilter(ContextCompat.getColor(activity, R.color.Grid_Normal_Color));

        Glide.with(activity).load(list.get(position).getThumb()).into(holder.imageView);

        holder.border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selection = String.valueOf(position);
                notifyDataSetChanged();
                gridCallback.GridMethod(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        LinearLayout border;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            border = (LinearLayout) itemView.findViewById(R.id.border);
        }
    }

    public interface GridCallback{
        void GridMethod(int position);
    }
}