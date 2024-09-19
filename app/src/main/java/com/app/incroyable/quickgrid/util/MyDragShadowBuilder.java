package com.app.incroyable.quickgrid.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class MyDragShadowBuilder extends View.DragShadowBuilder {
    Paint mPaint;

    public MyDragShadowBuilder(View view) {
        super(view);
        // 描画準備
        mPaint = new Paint();
        // mPaint.setColor(0xFFDD0000); // 赤
        // mPaint.setColor(0xFF000000); // 黒
        mPaint.setColor(Color.TRANSPARENT); // 青緑
        //mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
        // TODO Auto-generated method stub
        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDrawShadow(canvas);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);
    }

}
