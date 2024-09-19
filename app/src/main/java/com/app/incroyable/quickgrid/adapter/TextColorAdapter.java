package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.incroyable.quickgrid.R;

import java.util.ArrayList;

import static com.app.incroyable.quickgrid.R.id.border;

public class TextColorAdapter extends BaseAdapter{

    private Activity activity;
    private int itemBackground;
    ArrayList<Integer> integerArrayList;
    int resource;
    String Selection = "";

    public TextColorAdapter(Activity activity, ArrayList<Integer> integerArrayList, int resource)
    {
        this.activity = activity;
//        TypedArray a = activity.obtainStyledAttributes(R.styleable.MyGallery);
//        itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
//        a.recycle();
        this.integerArrayList = integerArrayList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return integerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        ImageView imageView, border;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_text_color, null, false);
        Holder holder = new Holder();
        holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
        holder.border = (ImageView) convertView.findViewById(border);

        if(Selection.equals(""))
        {
            holder.border.setVisibility(View.GONE);
        }
        else
        {
            if(Selection.equals(String.valueOf(position)))
                holder.border.setVisibility(View.VISIBLE);
            else
                holder.border.setVisibility(View.GONE);
        }

        if(resource == 1)
        {
            Drawable drawable = activity.getResources().getDrawable(R.drawable.stroke_rect);
            ColorFilter filter = new LightingColorFilter(integerArrayList.get(position), integerArrayList.get(position));
            drawable.setColorFilter(filter);
            holder.imageView.setImageDrawable(drawable);
        }
        else
        {
            holder.imageView.setImageResource(integerArrayList.get(position));
        }

        //holder.imageView.setBackgroundResource(itemBackground);
        //holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

        return convertView;
    }

    public void setSelection(int pos)
    {
        Selection = String.valueOf(pos);
        notifyDataSetChanged();
    }
}
