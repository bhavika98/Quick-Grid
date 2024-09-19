package com.app.incroyable.quickgrid.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;

public class TabStripView extends FrameLayout {

    private ClickTabListener listener;
    private int selectedNumber;
    private TextView[] textViews;
    String normalColor = "#ffffff";
    String selectedColor = "#4285f4";

    public interface ClickTabListener {
        void onClick(int i);
    }

    class TabStrip implements OnClickListener {
        final int index;

        TabStrip(int i) {
            this.index = i;
        }

        public void onClick(View v) {
            if (TabStripView.this.listener != null) {
                TabStripView.this.selectedNumber = this.index - 1;
                TabStripView.this.listener.onClick(this.index);
                for (int i = 0; i < TabStripView.this.textViews.length; i++) {
                    TextView sTextView = TabStripView.this.textViews[i];
                    if (this.index - 1 == i) {
                        sTextView.setTextColor(Color.parseColor(selectedColor));
                    } else {
                        sTextView.setTextColor(Color.parseColor(normalColor));
                    }
                }
            }
        }
    }

    public TabStripView(Context context) {
        super(context);
        this.selectedNumber = -1;
        iniView();
    }

    public TabStripView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.selectedNumber = -1;
        iniView();
    }

    private void iniView() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_tab_strip, this, true);
        this.textViews = new TextView[]{(TextView) findViewById(R.id.tab_text_1), (TextView) findViewById(R.id.tab_text_2), (TextView) findViewById(R.id.tab_text_3), (TextView) findViewById(R.id.tab_text_4), (TextView) findViewById(R.id.tab_text_5)};
        for (int i = 0; i < this.textViews.length; i++) {
            this.textViews[i].setOnClickListener(new TabStrip(i + 1));
        }
        selectedNumber(1);
    }

    public void selectedNumber(int number) {
        number--;
        if (this.selectedNumber != number) {
            this.selectedNumber = number;
            for (int i = 0; i < this.textViews.length; i++) {
                TextView sTextView = this.textViews[i];
                if (this.selectedNumber == i) {
                    sTextView.setTextColor(Color.parseColor(selectedColor));
                } else {
                    sTextView.setTextColor(Color.parseColor(normalColor));
                }
            }
        }
    }

    public void setListener(ClickTabListener listener) {
        this.listener = listener;
    }
}
