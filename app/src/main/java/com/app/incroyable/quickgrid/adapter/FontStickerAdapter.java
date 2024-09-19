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

import java.util.ArrayList;

import static com.app.incroyable.quickgrid.util.Constants.normalColor;
import static com.app.incroyable.quickgrid.util.Constants.selectedColor;

public class FontStickerAdapter extends RecyclerView.Adapter<FontStickerAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Integer> list;
    Activity activity;
    String Selection = "";
    FontStickerCallBack fontStickerCallBack;

    public FontStickerAdapter(Activity activity, ArrayList<Integer> list) {
        this.inflater = LayoutInflater.from(activity);
        this.list = list;
        this.activity = activity;
        this.fontStickerCallBack = (FontStickerCallBack) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_font_sticker, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (Selection.equals("")) {
            holder.imageView.setColorFilter(ContextCompat.getColor(activity, normalColor));
        } else {
            if (position == Integer.parseInt(Selection))
                holder.imageView.setColorFilter(ContextCompat.getColor(activity, selectedColor));
            else
                holder.imageView.setColorFilter(ContextCompat.getColor(activity, normalColor));
        }

        holder.imageView.setImageResource(list.get(position));

        holder.border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selection = String.valueOf(position);
                notifyDataSetChanged();
                fontStickerCallBack.FontStickerMethod(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout border;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            border = (LinearLayout) itemView.findViewById(R.id.border);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public interface FontStickerCallBack
    {
        void FontStickerMethod(int position);
    }
}