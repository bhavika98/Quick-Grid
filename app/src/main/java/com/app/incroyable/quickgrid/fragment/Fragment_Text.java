package com.app.incroyable.quickgrid.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.mylib.discreteslider.DiscreteSlider;
import com.app.mylib.discreteslider.DisplayUtility;
import com.app.mylib.toggle.ToggleButton;
import com.app.mylib.stickerview.FontProvider;
import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.FontsAdapter;
import com.app.incroyable.quickgrid.adapter.TextColorAdapter;
import com.app.incroyable.quickgrid.model.TextShadow;
import com.app.incroyable.quickgrid.util.RecyclerItemClickListener;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import static com.app.incroyable.quickgrid.R.id.addTxtEditText;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchTextPatternData;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchtextStickerColor;

public class Fragment_Text extends Fragment {

    int position;
    int layout = R.layout.fragment_empty;
    EditText editText;

    //fragment1
    RecyclerView recyclerViewFont;
    LinearLayoutManager linearLayoutManagerFont;
    public static FontsAdapter fontsAdapter;
    FontProvider fontProvider;
    List<String> fonts;
    public static Typeface fontTypeface;
    public static int opacityTxtProgress = 100;

    //fragment2
    public static int opacityBGProgress = 100;
    public static boolean bgStatus = false;
    public static int bgColor, currentBg = 1;
    public static int txtColor, currentColor = 1;

    //fragment3
    ArrayList<TextShadow> shadowArrayList = new ArrayList<>();
    public static int shadowRadius = 2, shadowX = 2, shadowY = 2, shadowColor = R.color.colorAccent;
    RelativeLayout tickMarkLabelsRelativeLayout;
    DiscreteSlider discreteSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        position = FragmentPagerItem.getPosition(getArguments());
        switch (position)
        {
            case 0:
                layout = R.layout.fragment_empty;
                break;

            case 1:
                layout = R.layout.fragment_text1;
                break;

            case 2:
                layout = R.layout.fragment_text2;
                break;

            case 3:
                layout = R.layout.fragment_text3;
                break;
        }
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View view1 = getActivity().findViewById(addTxtEditText);
        if( view1 instanceof EditText )
        {
            editText = (EditText) view1;
        }

        if (position == 0)
        {

        }
        if (position == 1)
        {
            fragment1(view);
        }
        if (position == 2)
        {
            fragment2(view);
        }
        if (position == 3)
        {
            fragment3(view);
        }
    }

    public void fragment1(View view)
    {
        fontTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Sail-Regular.otf");

        recyclerViewFont = (RecyclerView) view.findViewById(R.id.recyclerViewFont);
        linearLayoutManagerFont = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFont.setLayoutManager(linearLayoutManagerFont);

        fontProvider = new FontProvider(getActivity().getResources());
        fonts = fontProvider.getFontNames();
        fontsAdapter = new FontsAdapter(getActivity(), fonts, fontProvider);
        recyclerViewFont.setAdapter(fontsAdapter);
        recyclerViewFont.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Typeface typeface = fontProvider.getTypeface(fonts.get(position));
                fontsAdapter.setSelection(position);
                fontTypeface = typeface;
                editText.setTypeface(typeface);
            }
        }));
    }

    public void fragment2(View view)
    {
        ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        toggleButton.setToggleOff();
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                bgStatus = on;

                if(bgStatus == false)
                {
                    editText.setBackgroundColor(Color.TRANSPARENT);
                    editText.getBackground().setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                }
                else
                {
                    if(currentBg == 0)
                    {
                        editText.setBackgroundColor(bgColor);
                        editText.getBackground().setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    }
                    else
                    {
//                        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(bgColor)).getBitmap();
//                        Drawable bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 100, true));
                        Bitmap bmp = BitmapFactory.decodeResource(getResources(), bgColor);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
                        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                        editText.setBackgroundDrawable(bitmapDrawable);
                        editText.getBackground().setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    }
                }
            }
        });

        final TextView txtOpacity = (TextView) view.findViewById(R.id.txtOpacity);
        SeekBar textOpacitySeekbar = (SeekBar) view.findViewById(R.id.textOpacitySeekbar);
        textOpacitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtOpacity.setText(String.valueOf(progress));
                opacityTxtProgress = progress;
                editText.setTextColor(editText.getTextColors().withAlpha(Math.round((float) progress / 100 * 255)));
                editText.setHintTextColor(editText.getTextColors().withAlpha(Math.round((float) progress / 100 * 255)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView bgOpacity = (TextView) view.findViewById(R.id.bgOpacity);
        SeekBar textOpacityBGSeekbar = (SeekBar) view.findViewById(R.id.textOpacityBGSeekbar);
        textOpacityBGSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                opacityBGProgress = progress;
                bgOpacity.setText(String.valueOf(progress));
                editText.getBackground().setAlpha(Math.round((float) progress / 100 * 255));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextColorAdapter textColorAdapter1 = new TextColorAdapter(getActivity(), fetchtextStickerColor(), 1);
        Gallery recyclerViewTxtColor = (Gallery) view.findViewById(R.id.recyclerViewTxtColor);
        recyclerViewTxtColor.setAdapter(textColorAdapter1);
        recyclerViewTxtColor.setSelection(0);
        recyclerViewTxtColor.setSpacing(1);
        recyclerViewTxtColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentColor = 0;
                txtColor = fetchtextStickerColor().get(position);
                editText.setTextColor(fetchtextStickerColor().get(position));
                editText.setTextColor(editText.getTextColors().withAlpha(Math.round((float) opacityTxtProgress / 100 * 255)));
                editText.setHintTextColor(fetchtextStickerColor().get(position));
                editText.setHintTextColor(editText.getTextColors().withAlpha(Math.round((float) opacityTxtProgress / 100 * 255)));
                textColorAdapter1.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final TextColorAdapter textColorAdapter2 = new TextColorAdapter(getActivity(), fetchtextStickerColor(), 1);
        Gallery recyclerViewTxtBGColor = (Gallery) view.findViewById(R.id.recyclerViewTxtBGColor);
        recyclerViewTxtBGColor.setAdapter(textColorAdapter2);
        recyclerViewTxtBGColor.setSelection(2);
        recyclerViewTxtBGColor.setSpacing(1);
        recyclerViewTxtBGColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currentBg = 0;
                bgColor = fetchtextStickerColor().get(position);

                if(bgStatus == true)
                {
                    editText.setBackgroundColor(bgColor);
                    editText.getBackground().setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                }
                textColorAdapter2.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final TextColorAdapter textColorAdapter3 = new TextColorAdapter(getActivity(), fetchTextPatternData(getActivity()), 2);
        Gallery recyclerViewTxtPatternBGColor = (Gallery) view.findViewById(R.id.recyclerViewTxtPatternBGColor);
        recyclerViewTxtPatternBGColor.setAdapter(textColorAdapter3);
        recyclerViewTxtPatternBGColor.setSelection(3);
        recyclerViewTxtPatternBGColor.setSpacing(1);
        recyclerViewTxtPatternBGColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currentBg = 1;
                bgColor = fetchTextPatternData(getActivity()).get(position);

                if(bgStatus == true)
                {
//                    Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(bgColor)).getBitmap();
//                    Drawable bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 100, true));
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), bgColor);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
                    bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                    editText.setBackgroundDrawable(bitmapDrawable);
                    editText.getBackground().setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                }
                textColorAdapter3.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void fragment3(View view)
    {

        discreteSlider = (DiscreteSlider) view.findViewById(R.id.discreteSlider);
        tickMarkLabelsRelativeLayout = (RelativeLayout) view.findViewById(R.id.tickMarkLabelsRelativeLayout);

        shadowArrayList.add(new TextShadow(0, -20, -20));
        shadowArrayList.add(new TextShadow(8, 0, 0));
        shadowArrayList.add(new TextShadow(8, 0, -6));
        shadowArrayList.add(new TextShadow(8, 6, -6));
        shadowArrayList.add(new TextShadow(8, 6, 0));
        shadowArrayList.add(new TextShadow(8, 6, 6));
        shadowArrayList.add(new TextShadow(8, 0, 6));
        shadowArrayList.add(new TextShadow(8, -6, 6));
        shadowArrayList.add(new TextShadow(8, -6, 0));
        shadowArrayList.add(new TextShadow(8, -6, -6));

        discreteSlider.setPosition(4);
        discreteSlider.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                shadowRadius = shadowArrayList.get(position).getRadius();
                shadowX = shadowArrayList.get(position).getLeft();
                shadowY = shadowArrayList.get(position).getRight();

                editText.setShadowLayer(shadowRadius, shadowX, shadowY, shadowColor);

                int childCount = tickMarkLabelsRelativeLayout.getChildCount();
                for(int i= 0; i<childCount; i++)
                {
                    TextView tv = (TextView) tickMarkLabelsRelativeLayout.getChildAt(i);
                    if(i == position)
                        tv.setVisibility(View.VISIBLE);
                    else
                        tv.setVisibility(View.INVISIBLE);

                    tv.setShadowLayer(shadowRadius, shadowX, shadowY, Color.WHITE);
                }
            }
        });

        tickMarkLabelsRelativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tickMarkLabelsRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                addTickMarkTextLabels();
            }
        });

        final TextColorAdapter textColorAdapter = new TextColorAdapter(getActivity(), fetchtextStickerColor(), 1);
        final Gallery recyclerViewTxtShadow = (Gallery) view.findViewById(R.id.recyclerViewTxtShadow);
        recyclerViewTxtShadow.setAdapter(textColorAdapter);
        recyclerViewTxtShadow.setSelection(1);
        recyclerViewTxtShadow.setSpacing(1);
        recyclerViewTxtShadow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                shadowColor = fetchtextStickerColor().get(position);
                editText.setShadowLayer(shadowRadius, shadowX, shadowY, shadowColor);

                textColorAdapter.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addTickMarkTextLabels(){
        int tickMarkCount = discreteSlider.getTickMarkCount();
        float tickMarkRadius = discreteSlider.getTickMarkRadius();
        int width = tickMarkLabelsRelativeLayout.getMeasuredWidth();

        int discreteSliderBackdropLeftMargin = DisplayUtility.dp2px(getContext(), 32);
        int discreteSliderBackdropRightMargin = DisplayUtility.dp2px(getContext(), 32);
        float firstTickMarkRadius = tickMarkRadius;
        float lastTickMarkRadius = tickMarkRadius;
        int interval = (width - (discreteSliderBackdropLeftMargin+discreteSliderBackdropRightMargin) - ((int)(firstTickMarkRadius+lastTickMarkRadius)) )
                / (tickMarkCount-1);

        String[] tickMarkLabels = {"A", "A", "A", "A", "A", "A", "A", "A", "A", "A"};
        int tickMarkLabelWidth = DisplayUtility.dp2px(getContext(), 40);

        for(int i=0; i<tickMarkCount; i++) {
            TextView tv = new TextView(getContext());

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    tickMarkLabelWidth, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tv.setText(tickMarkLabels[i]);
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setTextSize(30f);
            tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/ebrima.ttf"));
            tv.setGravity(Gravity.CENTER);

            if(i == discreteSlider.getPosition())
                tv.setVisibility(View.VISIBLE);
            else
                tv.setVisibility(View.INVISIBLE);

            int left = discreteSliderBackdropLeftMargin + (int)firstTickMarkRadius + (i * interval) - (tickMarkLabelWidth/2);

            layoutParams.setMargins(left,
                    0,
                    0,
                    0);
            tv.setLayoutParams(layoutParams);

            tickMarkLabelsRelativeLayout.addView(tv);
        }
    }

    public static void dismissSoftKeyboard(InputMethodManager inputMethodManager, EditText addTxtEditText) {
        addTxtEditText.setFocusable(false);
        addTxtEditText.setEnabled(false);
        addTxtEditText.setFocusableInTouchMode(false);
        addTxtEditText.setClickable(false);
        inputMethodManager.hideSoftInputFromWindow(addTxtEditText.getWindowToken(), 0);
        addTxtEditText.clearFocus();
    }

    public static void showSoftKeyboard(InputMethodManager inputMethodManager, EditText addTxtEditText) {
        addTxtEditText.setFocusable(true);
        addTxtEditText.setEnabled(true);
        addTxtEditText.setFocusableInTouchMode(true);
        addTxtEditText.setClickable(true);
        inputMethodManager.toggleSoftInputFromWindow(addTxtEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        addTxtEditText.requestFocus();
    }

    public static void setDefaultValues(EditText addTxtEditText, Activity activity)
    {
        addTxtEditText.setHint(activity.getResources().getString(R.string.app_name));
        addTxtEditText.setHintTextColor(Color.WHITE);
        addTxtEditText.setShadowLayer(8, 6, 6, Color.BLACK);
        addTxtEditText.setSelection(addTxtEditText.length());
        addTxtEditText.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/Sail-Regular.otf"));
    }
}

