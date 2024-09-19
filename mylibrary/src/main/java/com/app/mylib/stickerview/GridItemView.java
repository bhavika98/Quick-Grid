package com.app.mylib.stickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.mylib.R;
import com.bumptech.glide.Glide;

public class GridItemView extends FrameLayout {

    ImageView imageView;
    Context context;

    public GridItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.adapter_sticker, this);
        imageView = (ImageView) getRootView().findViewById(R.id.sticker_img);
        this.context = context;
    }

//    public void display(String text, boolean isSelected) {
//        Glide.with(context).load(getResources().getIdentifier(text,"drawable", context.getPackageName())).into(imageView);
//        display(isSelected);
//    }

    public void display(boolean isSelected) {
        imageView.setBackgroundResource(isSelected ? R.color.Sticker_Selected_Color : R.color.Sticker_BG_Color);
    }
}