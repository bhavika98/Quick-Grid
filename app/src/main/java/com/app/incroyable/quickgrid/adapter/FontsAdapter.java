package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mylib.stickerview.FontProvider;
import com.app.incroyable.quickgrid.R;

import java.util.List;

public class FontsAdapter extends RecyclerView.Adapter<FontsAdapter.ViewHolder> {

    LayoutInflater inflater;
    FontProvider fontProvider;
    List<String> fontNames;
    Activity activity;
    String Selection = "";
    int selectedColor = R.color.Button_Selected_Color, normalColor = R.color.white;

    public FontsAdapter(Activity activity, List<String> fontNames, FontProvider fontProvider) {
        this.inflater = LayoutInflater.from(activity);
        this.fontProvider = fontProvider;
        this.activity = activity;
        this.fontNames = fontNames;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adapter_font, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (Selection.equals("")) {
            holder.textView.setTextColor(ContextCompat.getColor(activity, normalColor));
        } else {
            if (position == Integer.parseInt(Selection))
                holder.textView.setTextColor(ContextCompat.getColor(activity, selectedColor));
            else
                holder.textView.setTextColor(ContextCompat.getColor(activity, normalColor));
        }

        holder.textView.setTypeface(fontProvider.getTypeface(fontNames.get(position)));
//        holder.textView.setText(fontNames.get(position));
    }

    @Override
    public int getItemCount() {
        return fontNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public void setSelection(int position)
    {
        this.Selection = String.valueOf(position);
        notifyDataSetChanged();
    }
}