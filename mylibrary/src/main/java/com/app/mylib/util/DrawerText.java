package com.app.mylib.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class DrawerText extends TextView {

    public DrawerText(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public DrawerText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public DrawerText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {

        Typeface customFont = FontCache.getTypeface("fonts/RobotoCondensed.ttf", context);
        setTypeface(customFont);
    }
}
