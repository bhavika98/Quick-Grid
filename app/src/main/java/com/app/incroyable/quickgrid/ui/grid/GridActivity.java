package com.app.incroyable.quickgrid.ui.grid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.mylib.grid3d.PorterShapeImageView;
import com.app.mylib.stickerview.DrawableSticker;
import com.app.mylib.stickerview.Sticker;
import com.app.mylib.stickerview.StickerView;
import com.app.mylib.stickerview.TextSticker;
import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.ColorAdapter;
import com.app.incroyable.quickgrid.adapter.FrameAdapter;
import com.app.incroyable.quickgrid.adapter.GridAdapter;
import com.app.incroyable.quickgrid.adapter.PatternAdapter;
import com.app.incroyable.quickgrid.adapter.RatioAdapter;
import com.app.incroyable.quickgrid.adapter.SubPatternAdapter;
import com.app.incroyable.quickgrid.fragment.Fragment_Sticker;
import com.app.incroyable.quickgrid.fragment.Fragment_Text;
import com.app.incroyable.quickgrid.model.Frame;
import com.app.incroyable.quickgrid.model.Grid;
import com.app.incroyable.quickgrid.model.Margin;
import com.app.incroyable.quickgrid.model.Ratio;
import com.app.incroyable.quickgrid.model.StickerData;
import com.app.incroyable.quickgrid.ui.PhotoEditingActivity;
import com.app.incroyable.quickgrid.util.CompressImage;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.CustomViewPager;
import com.app.incroyable.quickgrid.util.MyDragShadowBuilder;
import com.app.incroyable.quickgrid.util.RecyclerItemClickListener;
import com.michael.easydialog.EasyDialog;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import carbon.widget.FrameLayout;
import id.zelory.compressor.Compressor;

import static com.app.mylib.stickerview.StickerView.isStickerTouch;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.bgColor;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.bgStatus;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.currentBg;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.currentColor;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.dismissSoftKeyboard;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.fontTypeface;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.opacityBGProgress;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.opacityTxtProgress;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.setDefaultValues;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.shadowColor;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.shadowRadius;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.shadowX;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.shadowY;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.showSoftKeyboard;
import static com.app.incroyable.quickgrid.fragment.Fragment_Text.txtColor;
import static com.app.incroyable.quickgrid.ui.MainActivity.stickerArrayList;
import static com.app.incroyable.quickgrid.ui.MainActivity.stickerValue;
import static com.app.incroyable.quickgrid.util.CompressImage.getPath;
import static com.app.incroyable.quickgrid.util.Constants.REQUEST_CODE_EDIT_IMG;
import static com.app.incroyable.quickgrid.util.Constants.REQUEST_CODE_GALLERY;
import static com.app.incroyable.quickgrid.util.Constants.editedImagePath;
import static com.app.incroyable.quickgrid.util.Constants.goneLayout;
import static com.app.incroyable.quickgrid.util.Constants.visibleLayout;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchFrame;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchGridData;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchMainPattern;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchMargins;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchPrimaryColor;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchRatio;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchSecondaryColor;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchSubPattern;
import static com.app.incroyable.quickgrid.util.DataBinder.setNotSelectedSticker;
import static com.app.incroyable.quickgrid.util.SaveImage.takeScreenshot;

public class GridActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener, View.OnClickListener, GridAdapter.GridCallback{

    Activity activity = GridActivity.this;
    int currentTab;

    ArrayList<String> stringArrayList = new ArrayList<>();
    int mPicFrameCount;
    int currentLayout, marginLayout, gridType, fullMargin = 6;
    LinearLayout mainFrameLayout, captureLayout;
    LinearLayout mainFrame;
    View layoutView;
    LinearLayout.LayoutParams mMainviewParams;
    int mHeight, mWidth, picChangePosition;

    RecyclerView recyclerViewGrid;
    LinearLayoutManager linearLayoutManagerGrid;
    GridAdapter gridAdapter;
    ArrayList<Grid> gridArrayList = new ArrayList<>();

    int[] resourceFrameLayout = {R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4, R.id.frame5, R.id.frame6, R.id.frame7, R.id.frame8, R.id.frame9};
    FrameLayout[] frameLayoutArray;

    int[] resourceRelativeLayout = {R.id.rel1, R.id.rel2, R.id.rel3, R.id.rel4, R.id.rel5, R.id.rel6, R.id.rel7, R.id.rel8, R.id.rel9};
    RelativeLayout[] relativeLayoutArray;

    int[] resourceTouchImageView = {R.id.image_add1, R.id.image_add2, R.id.image_add3, R.id.image_add4, R.id.image_add5, R.id.image_add6, R.id.image_add7, R.id.image_add8, R.id.image_add9};
    PorterShapeImageView[] touchImageViewArray;
    int[] rotationUnits;
    ArrayList<Margin> marginArrayList = new ArrayList<>();

    LinearLayout.LayoutParams[] liLayoutParamses;

    LinearLayout layout_grid_main, layout_pattern_main, layout_color_main,  layout_border_main, layout_ratio_main, layout_frameborder_main;

    // Border
    SeekBar mBorderSeekBar, mCornerSeekbar, mSpaceSeekbar;
    int cornerRadius = 10, borderWidth = 6, space = 6;

    // Color
    RecyclerView grid_color1;
    LinearLayoutManager linearLayoutManager_grid_color1;
    ColorAdapter gridcolorAdapter1;
    RecyclerView grid_color;
    LinearLayoutManager linearLayoutManager_grid_color;
    ColorAdapter gridcolorAdapter;
    ArrayList<Integer> colors11 = new ArrayList<>();
    ArrayList<Integer> colors33 = new ArrayList<>();

    // Ratio
    RecyclerView recyclerViewRatio;
    LinearLayoutManager linearLayoutManagerRatio;
    RatioAdapter ratioAdapter;
    ArrayList<Ratio> ratioArrayList = new ArrayList<>();

    // Pattern
    int patternImage = Color.parseColor("#ffffff");
    int backgroundPattern = 0;
    RecyclerView recyclerViewPattern;
    LinearLayoutManager linearLayoutManager;
    PatternAdapter patternAdapter;
    RecyclerView recyclerViewPatternSub;
    LinearLayoutManager linearLayoutManagerSub;
    SubPatternAdapter subPatternAdapter;
    LinearLayout subPattern, goBackFromPattern;
    int positionMainPattern;

    // Frame
    RecyclerView recyclerViewFrame;
    LinearLayoutManager linearLayoutManagerFrame;
    FrameAdapter frameAdapter;
    ArrayList<Frame> frameArrayList = new ArrayList<>();
    RelativeLayout borderRelative;

    // Sticker Layout
    ViewPager viewPager;
    SmartTabLayout viewPagerTab;
    StickerView stickerView;
    LinearLayout stickerFullLayout, mainLayout, stickerCheck, stickerCross;
    TextView stickerCount;

    // Text Sticker Layout
    TextSticker sticker, temp_sticker = null;
    InputMethodManager inputMethodManager;
    CustomViewPager viewPagerText;
    LinearLayout textFullLayout;
    EditText addTxtEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        bindToolbar();
        bingPager();
        bindContols();
        addBottomLayoutData();
        clickEvents();
        addGrid();
        setupLayout();

        // Sticker Layout
        openDialog();

        // Text Sticker Layout
        bindTextStickerView();
    }

    private void bindTextStickerView() {

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        addTxtEditText = (EditText) findViewById(R.id.addTxtEditText);
        textFullLayout = (LinearLayout) findViewById(R.id.textFullLayout);

        ViewGroup tabText = (ViewGroup) findViewById(R.id.tabTextSticker);
        tabText.addView(LayoutInflater.from(this).inflate(R.layout.viewpager_text_sticker, tabText, false));

        viewPagerText = (CustomViewPager) findViewById(R.id.viewPagerTextSticker);
        SmartTabLayout viewPagerTabText = (SmartTabLayout) findViewById(R.id.viewpagertextsticker);
        viewPagerText.setPagingEnabled(false);

        final LayoutInflater inflater = LayoutInflater.from(viewPagerTabText.getContext());
        viewPagerTabText.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_text_sticker_layout, container, false);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.keyboard_btn));
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.font_style_btn));
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.font_color_btn));
                        break;
                    case 3:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.adjust_btn));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });

        FragmentPagerItems pages = new FragmentPagerItems(activity);
        pages.add(FragmentPagerItem.of("Fragment_Text", Fragment_Text.class));
        pages.add(FragmentPagerItem.of("Fragment_Text", Fragment_Text.class));
        pages.add(FragmentPagerItem.of("Fragment_Text", Fragment_Text.class));
        pages.add(FragmentPagerItem.of("Fragment_Text", Fragment_Text.class));

        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        viewPagerText.setAdapter(fragmentPagerItemAdapter);
        viewPagerTabText.setViewPager(viewPagerText);
        viewPagerText.setCurrentItem(1, true);

        viewPagerText.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    showSoftKeyboard(inputMethodManager, addTxtEditText);
                } else {
                    dismissSoftKeyboard(inputMethodManager, addTxtEditText);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerClicked(Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    stickerView.replace(sticker);
                    stickerView.invalidate();
                }
            }

            @Override
            public void onStickerDeleted(Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    temp_sticker = null;
                    stickerView.invalidate();
                }
            }

            @Override
            public void onStickerDragFinished(Sticker sticker) {

            }

            @Override
            public void onStickerZoomFinished(Sticker sticker) {

            }

            @Override
            public void onStickerFlipped(Sticker sticker) {

            }

            @Override
            public void onStickerDoubleTapped(Sticker sticker) {
                if (sticker instanceof TextSticker)
                {
                    temp_sticker = ((TextSticker) sticker);
                    textFullLayout.setVisibility(View.VISIBLE);
                    addTxtEditText.setText(temp_sticker.getText().toString());
                    addTxtEditText.setTypeface(temp_sticker.getTypeface());
                    addTxtEditText.setTextColor(temp_sticker.getTextColor());
                    addTxtEditText.getPaint().setAlpha(temp_sticker.getAlpha());
                    viewPagerText.setCurrentItem(1, true);
                }
            }
        });
    }

    private void bingPager() {

        ViewGroup tabBottom = (ViewGroup) findViewById(R.id.tabBottom);
        tabBottom.addView(LayoutInflater.from(this).inflate(R.layout.custom_tab_bottom, tabBottom, false));

        final CustomViewPager viewPagerBottom = (CustomViewPager) findViewById(R.id.viewPagerBottom);
        SmartTabLayout viewPagerBottomTab = (SmartTabLayout) findViewById(R.id.viewPagerBottomTab);
        viewPagerBottom.setPagingEnabled(false);

        final LayoutInflater inflater = LayoutInflater.from(viewPagerBottomTab.getContext());
        viewPagerBottomTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_bottom_single_item, container, false);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.grid_btn));
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.adjust_grid_btn));
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.bg_btn));
                        break;
                    case 3:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.bg_color_btn));
                        break;
                    case 4:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.frame_btn));
                        break;
                    case 5:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.sticker_btn));
                        break;
                    case 6:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.text_btn));
                        break;
                    case 7:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.ratio_btn));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });

        FragmentPagerItems pages = new FragmentPagerItems(activity);
        pages.add(FragmentPagerItem.of("Fragment_Grid", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Adjust", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_BG", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Color", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Frame", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Text", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Ratio", Fragment_Empty.class));

        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        viewPagerBottom.setAdapter(fragmentPagerItemAdapter);
        viewPagerBottomTab.setViewPager(viewPagerBottom);

        viewPagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(!(position == 5 || position == 6))
                    currentTab = position;

                switch (position) {
                    case 0:
                        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 1:
                        changeVisibility(goneLayout, visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 2:
                        changeVisibility(goneLayout, goneLayout, visibleLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 3:
                        changeVisibility(goneLayout, goneLayout, goneLayout, goneLayout, goneLayout, visibleLayout);
                        break;

                    case 4:
                        changeVisibility(goneLayout, goneLayout, goneLayout, visibleLayout, goneLayout, goneLayout);
                        break;

                    case 5:
                        fillStickerData();
                        viewPagerBottom.setCurrentItem(currentTab, true);
                        break;

                    case 6:
                        textFullLayout.setVisibility(View.VISIBLE);

                        if (!isStickerTouch) {
                            setDefaultValues(addTxtEditText, activity);
                            temp_sticker = null;
                        }
                        viewPagerBottom.setCurrentItem(currentTab, true);
                        break;

                    case 7:
                        changeVisibility(goneLayout, goneLayout, goneLayout, goneLayout, visibleLayout, goneLayout);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void addGrid()
    {
        stringArrayList = getIntent().getExtras().getStringArrayList(Constants.imgList);
        mPicFrameCount = stringArrayList.size();
        gridArrayList.clear();
        gridArrayList = fetchGridData(mPicFrameCount);
        gridAdapter = new GridAdapter(activity, gridArrayList);
        recyclerViewGrid.setAdapter(gridAdapter);

        currentLayout = gridArrayList.get(0).getLayout();
        marginLayout = gridArrayList.get(0).getTitle();
        gridType = gridArrayList.get(0).getType();
    }

    private void addBottomLayoutData() {

        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout, goneLayout);

        // Color
        gridcolorAdapter = new ColorAdapter(activity, fetchPrimaryColor());
        gridcolorAdapter.setSelection(19);
        grid_color.setAdapter(gridcolorAdapter);
        gridcolorAdapter1 = new ColorAdapter(activity, fetchSecondaryColor(0));
        grid_color1.setAdapter(gridcolorAdapter1);

        // Pattern
        patternAdapter = new PatternAdapter(activity, fetchMainPattern());
        recyclerViewPattern.setAdapter(patternAdapter);

        // Frame
        frameArrayList.clear();
        frameArrayList = fetchFrame();
        frameAdapter = new FrameAdapter(activity, frameArrayList);
        recyclerViewFrame.setAdapter(frameAdapter);

        // Ratio
        ratioArrayList.clear();
        ratioArrayList = fetchRatio();
        ratioAdapter = new RatioAdapter(activity, ratioArrayList);
        recyclerViewRatio.setAdapter(ratioAdapter);
    }

    private void setupLayout() {

        layoutView = getLayoutInflater().inflate(currentLayout, mainFrameLayout, false);
        mainFrameLayout.addView((View) layoutView);
        mainFrame = (LinearLayout) layoutView.findViewById(R.id.mainFrame);

        rotationUnits = new int[mPicFrameCount];
        touchImageViewArray = new PorterShapeImageView[mPicFrameCount];
        frameLayoutArray = new FrameLayout[mPicFrameCount];

        for(int i = 0; i < mPicFrameCount; i++)
        {
            rotationUnits[i] = 0;
            touchImageViewArray[i] = (PorterShapeImageView) layoutView.findViewById(resourceTouchImageView[i]);
            frameLayoutArray[i] = (FrameLayout) layoutView.findViewById(resourceFrameLayout[i]);
            frameLayoutArray[i].setCornerRadius(cornerRadius);
        }

        for(int i=0; i<mPicFrameCount; i++)
        {
            Bitmap bitmap = Compressor.getDefault(activity).compressToBitmap(new File(stringArrayList.get(i)));
            touchImageViewArray[i].setImageBitmap(bitmap);
            touchImageViewArray[i].setTag(stringArrayList.get(i));
            touchImageViewArray[i].setOnClickListener(this);
        }

        if(gridType == 1)
        {
            liLayoutParamses = new LinearLayout.LayoutParams[mPicFrameCount];

            for(int i=0; i<mPicFrameCount; i++)
                liLayoutParamses[i] = (LinearLayout.LayoutParams) frameLayoutArray[i].getLayoutParams();
        }
        else
        {
            relativeLayoutArray = new RelativeLayout[mPicFrameCount];

            for(int i=0; i<mPicFrameCount; i++)
            {
                relativeLayoutArray[i] = (RelativeLayout) layoutView.findViewById(resourceRelativeLayout[i]);
            }
        }

        dragdrop();

        addMargins(marginLayout);
        setSpace(space);
    }

    private void addMargins(int position) {

        marginArrayList.clear();
        marginArrayList = fetchMargins(position);

        if(gridType == 1)
        {
            for(int i=0; i<mPicFrameCount; i++)
            {
                liLayoutParamses[i].setMargins(marginArrayList.get(i).getLeft(), marginArrayList.get(i).getTop(), marginArrayList.get(i).getRight(), marginArrayList.get(i).getBottom());
                frameLayoutArray[i].setLayoutParams(liLayoutParamses[i]);
            }
        }
        else
        {
            for(int i=0; i<mPicFrameCount; i++)
            {
                relativeLayoutArray[i].setPadding(fullMargin, fullMargin, fullMargin, fullMargin);
            }
        }
    }

    public void setBorder(int paramInt) {

        borderWidth = paramInt;
        int left, top, right, bottom;
        for(int i=0; i<mPicFrameCount; i++)
        {
            if(marginArrayList.get(i).getLeft() == 6)
                left = 1;
            else
                left = 2;

            if(marginArrayList.get(i).getTop() == 6)
                top = 1;
            else
                top = 2;

            if(marginArrayList.get(i).getRight() == 6)
                right = 1;
            else
                right = 2;

            if(marginArrayList.get(i).getBottom() == 6)
                bottom = 1;
            else
                bottom = 2;

            float f_left = (float) paramInt/left;
            float f_top = (float) paramInt/top;
            float f_right = (float) paramInt/right;
            float f_bottom = (float) paramInt/bottom;

            if(gridType == 1)
            {
                liLayoutParamses[i].setMargins(paramInt/left, paramInt/top, paramInt/right, paramInt/bottom);
                frameLayoutArray[i].setLayoutParams(liLayoutParamses[i]);
            }
            else
            {
                relativeLayoutArray[i].setPadding(paramInt, paramInt, paramInt, paramInt);
            }
        }
    }

    public void dragdrop() {

        for (int i = 0; i < touchImageViewArray.length; i++) {
            touchImageViewArray[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText("test", "drag:");
                    v.startDrag(data, new MyDragShadowBuilder(v), v, 0);
                    return true;
                }
            });

            touchImageViewArray[i].setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    PorterShapeImageView from = (PorterShapeImageView) event.getLocalState();
                    PorterShapeImageView to = (PorterShapeImageView) v;

                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:

                            if (from == to) { return false; }
                            //from.setImageResource(R.color.background);
                            return true;

                        case DragEvent.ACTION_DRAG_ENDED:

                            if (event.getResult()) {}
                            else {
                                String path = from.getTag().toString();

                                Bitmap bitmap = Compressor.getDefault(activity).compressToBitmap(new File(path));
                                from.setImageBitmap(bitmap);
                            }
                            return true;

                        case DragEvent.ACTION_DRAG_LOCATION:
                            return true;

                        case DragEvent.ACTION_DROP:

                            final String path = to.getTag().toString();
                            final String path1 = from.getTag().toString();

                            to.setTag(path1);
                            from.setTag(path);

                            new swapImages(path, path1, from, to).execute();

                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {

                                    for (int i = 0; i < stringArrayList.size(); i++) {
                                        if (stringArrayList.get(i).equals(path) || stringArrayList.get(i).equals(path1)) {
                                            rotationUnits[i] = 0;
                                        }
                                    }
                                }
                            });

                            return true;

                        case DragEvent.ACTION_DRAG_ENTERED:
                            return true;

                        case DragEvent.ACTION_DRAG_EXITED:
                            return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void GridMethod(int position) {
        mainFrameLayout.removeAllViews();
        currentLayout = gridArrayList.get(position).getLayout();
        marginLayout = gridArrayList.get(position).getTitle();
        gridType = gridArrayList.get(position).getType();
        setupLayout();

        if (backgroundPattern == 1) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), patternImage);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);

            if(positionMainPattern != 0)
                bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

            mainFrame.setBackgroundDrawable(bitmapDrawable);
        }
        else
        {
            mainFrame.setBackgroundColor(patternImage);
        }

        for(int i=0; i<mPicFrameCount; i++){
            frameLayoutArray[i].setCornerRadius(cornerRadius);
            frameLayoutArray[i].invalidate();
        }

        setBorder(borderWidth);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.mBorderSeekBar)
        {
            borderWidth = progress;
            setBorder(borderWidth);
        }
        else if (seekBar.getId() == R.id.mCornerSeekbar) {
            cornerRadius = progress;
            for(int i=0; i<mPicFrameCount; i++){
                frameLayoutArray[i].setCornerRadius(cornerRadius);
                frameLayoutArray[i].invalidate();
            }
        }
        else if(seekBar.getId() == R.id.mSpaceSeekbar){
            space = progress;
            setSpace(space);
        }
    }

    public void setSpace(int progress)
    {
        space = progress;
        mainFrame.setPadding(progress, progress, progress, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public class swapImages extends AsyncTask<String,Void,Void> {

        String path, path1;
        PorterShapeImageView from, to;
        Bitmap bitmap, bitmap1;
        ProgressDialog progressDialog;

        public swapImages(String path, String path1, PorterShapeImageView from, PorterShapeImageView to)
        {
            this.path = path;
            this.path1 = path1;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Void doInBackground(String... params) {
            try
            {
                bitmap = Compressor.getDefault(activity).compressToBitmap(new File(path));
                bitmap1 = Compressor.getDefault(activity).compressToBitmap(new File(path1));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            from.setImageBitmap(bitmap);
            to.setImageBitmap(bitmap1);
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }

    }

    private void bindContols() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mHeight = displayMetrics.heightPixels;
        mWidth = displayMetrics.widthPixels;

        mainFrameLayout = (LinearLayout) findViewById(R.id.mainFrameLayout);
        captureLayout = (LinearLayout) findViewById(R.id.captureLayout);
        mMainviewParams = ((LinearLayout.LayoutParams) captureLayout.getLayoutParams());
        mMainviewParams.height = mWidth;
        mMainviewParams.width = mWidth;

        recyclerViewGrid = (RecyclerView) findViewById(R.id.recyclerViewGrid);
        linearLayoutManagerGrid = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGrid.setLayoutManager(linearLayoutManagerGrid);

        layout_grid_main = (LinearLayout) findViewById(R.id.layout_grid_main);
        layout_pattern_main = (LinearLayout) findViewById(R.id.layout_pattern_main);
        layout_color_main = (LinearLayout) findViewById(R.id.layout_color_main);
        layout_border_main = (LinearLayout) findViewById(R.id.layout_border_main);
        layout_ratio_main = (LinearLayout) findViewById(R.id.layout_ratio_main);
        layout_frameborder_main = (LinearLayout) findViewById(R.id.layout_frameborder_main);

        // Border
        mBorderSeekBar = (SeekBar) findViewById(R.id.mBorderSeekBar);
        mBorderSeekBar.setOnSeekBarChangeListener(this);
        mCornerSeekbar = (SeekBar) findViewById(R.id.mCornerSeekbar);
        mCornerSeekbar.setOnSeekBarChangeListener(this);
        mSpaceSeekbar = (SeekBar) findViewById(R.id.mSpaceSeekbar);
        mSpaceSeekbar.setOnSeekBarChangeListener(this);

        // Color
        grid_color = (RecyclerView) findViewById(R.id.grid_color);
        linearLayoutManager_grid_color = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        grid_color.setLayoutManager(linearLayoutManager_grid_color);
        grid_color1 = (RecyclerView) findViewById(R.id.grid_color1);
        linearLayoutManager_grid_color1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        grid_color1.setLayoutManager(linearLayoutManager_grid_color1);

        // Ratio
        recyclerViewRatio = (RecyclerView) findViewById(R.id.recyclerViewRatio);
        linearLayoutManagerRatio = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRatio.setLayoutManager(linearLayoutManagerRatio);

        // Pattern
        recyclerViewPattern = (RecyclerView) findViewById(R.id.recyclerViewPattern);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPattern.setLayoutManager(linearLayoutManager);
        recyclerViewPatternSub = (RecyclerView) findViewById(R.id.recyclerViewPatternSub);
        linearLayoutManagerSub = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPatternSub.setLayoutManager(linearLayoutManagerSub);
        subPattern = (LinearLayout) findViewById(R.id.subPattern);
        goBackFromPattern = (LinearLayout) findViewById(R.id.goBackFromPattern);

        // Frame
        recyclerViewFrame = (RecyclerView) findViewById(R.id.recyclerViewFrame);
        linearLayoutManagerFrame = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFrame.setLayoutManager(linearLayoutManagerFrame);
        borderRelative = (RelativeLayout) findViewById(R.id.borderRelative);

        // Sticker Layout
        stickerCross = (LinearLayout) findViewById(R.id.stickerCross);
        stickerCheck = (LinearLayout) findViewById(R.id.stickerCheck);
        stickerCount = (TextView) findViewById(R.id.stickerCount);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setVisibility(View.VISIBLE);
        stickerFullLayout = (LinearLayout) findViewById(R.id.stickerFullLayout);
        stickerFullLayout.setVisibility(View.GONE);
        stickerView = (StickerView) findViewById(R.id.sticker_view);
        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);
        stickerView.setConstrained(true);
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.GridActivity_Title));
    }

    private void clickEvents() {

        // Color

        colors11 = fetchPrimaryColor();
        colors33 = fetchSecondaryColor(0);
        grid_color1.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                backgroundPattern = 0;
                patternImage = colors33.get(position);
                mainFrame.setBackgroundColor(colors33.get(position));
                gridcolorAdapter1.setSelection(position);
            }
        }));

        grid_color.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                mainFrame.setBackgroundColor(colors11.get(position));
                gridcolorAdapter.setSelection(position);
                backgroundPattern = 0;
                patternImage = colors11.get(position);

                if(colors11.get(position) != Color.parseColor("#ffffff") && colors11.get(position) != Color.parseColor("#000000"))
                {
                    colors33 = fetchSecondaryColor(position);
                    gridcolorAdapter1 = new ColorAdapter(activity, colors33);
                    grid_color1.setAdapter(gridcolorAdapter1);
                }
            }
        }));

        // Ratio
        recyclerViewRatio.addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setRatio(position);
                ratioAdapter.selection(String.valueOf(position));
                ratioAdapter.notifyDataSetChanged();
            }
        }));

        // Pattern

        goBackFromPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subPattern.setVisibility(View.GONE);
                recyclerViewPattern.setVisibility(View.VISIBLE);
            }
        });

        recyclerViewPattern.addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                subPatternAdapter = new SubPatternAdapter(activity, fetchSubPattern(position));
                recyclerViewPatternSub.setAdapter(subPatternAdapter);
                subPattern.setVisibility(View.VISIBLE);
                recyclerViewPattern.setVisibility(View.GONE);

                positionMainPattern = position;
                patternAdapter.selection(String.valueOf(position));
                patternAdapter.notifyDataSetChanged();
            }
        }));

        recyclerViewPatternSub.addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                int[] subPattern = fetchSubPattern(positionMainPattern);
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), subPattern[position]);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);

                if(positionMainPattern != 0)
                    bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

                mainFrame.setBackgroundDrawable(bitmapDrawable);
                backgroundPattern = 1;
                patternImage = subPattern[position];

                subPatternAdapter.selection(String.valueOf(position));
                subPatternAdapter.notifyDataSetChanged();
            }
        }));

        // Frame
        recyclerViewFrame.addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (position == 0)
                    borderRelative.setBackgroundResource(R.drawable.transparent_background);
                else
                    borderRelative.setBackgroundResource(frameArrayList.get(position).getImage());
                frameAdapter.selection(String.valueOf(position));
                frameAdapter.notifyDataSetChanged();
            }
        }));

        // Sticker
        stickerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(StickerData stickerData : stickerArrayList)
                {
                    if(stickerData.isSelected())
                    {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(stickerData.getName()));
                            Drawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                            stickerView.addSticker(new DrawableSticker(bitmapDrawable));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                stickerLayout();
            }
        });

        stickerCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stickerLayout();
            }
        });
    }

    public void setRatio(int pos) {
        switch (pos) {
            case 0:
                mMainviewParams.width = mWidth;
                mMainviewParams.height = mWidth;
                return;

            case 1:
                mMainviewParams.width = mWidth;
                mMainviewParams.height = (mWidth * 2 / 3);
                return;

            case 2:
                mMainviewParams.width = (mWidth * 2 / 3);
                mMainviewParams.height = mWidth;
                return;

            case 3:
                mMainviewParams.width = mWidth;
                mMainviewParams.height = (mWidth * 3 / 4);
                return;

            case 4:
                mMainviewParams.width = (mWidth * 3 / 4);
                mMainviewParams.height = mWidth;
                return;

            case 5:
                mMainviewParams.width = mWidth;
                mMainviewParams.height = (mWidth * 1 / 2);
                return;

            case 6:
                mMainviewParams.width = (mWidth * 1 / 2);
                mMainviewParams.height = mWidth;
                return;
        }
        captureLayout.setLayoutParams(mMainviewParams);
        captureLayout.invalidate();
    }

    public void fillStickerData() {
        stickerFullLayout.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);

        FragmentPagerItems pages = new FragmentPagerItems(activity);
        pages.add(FragmentPagerItem.of("Fragment_Sticker1", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker2", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker3", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker4", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker5", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker6", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker7", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker8", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker9", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker10", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker11", Fragment_Sticker.class));
        pages.add(FragmentPagerItem.of("Fragment_Sticker12", Fragment_Sticker.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

    private void openDialog() {

        setNotSelectedSticker();
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_icons, tab, false));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        final LayoutInflater inflater = LayoutInflater.from(viewPagerTab.getContext());
        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View tab = inflater.inflate(R.layout.custom_tab_icon, container, false);
                ImageView icon = (ImageView) tab.findViewById(R.id.imageView);
                TextView text = (TextView) tab.findViewById(R.id.textView);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_1));
                        text.setText("emoji");
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_2));
                        text.setText("cartoon");
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_3));
                        text.setText("christmas");
                        break;
                    case 3:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_4));
                        text.setText("cute");
                        break;
                    case 4:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_5));
                        text.setText("fresh");
                        break;
                    case 5:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_6));
                        text.setText("funny");
                        break;
                    case 6:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_7));
                        text.setText("love");
                        break;
                    case 7:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_8));
                        text.setText("makeup");
                        break;
                    case 8:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_9));
                        text.setText("newyear");
                        break;
                    case 9:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_10));
                        text.setText("snap");
                        break;
                    case 10:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_11));
                        text.setText("summer");
                        break;
                    case 11:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.s_icon_12));
                        text.setText("writing");
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return tab;
            }
        });
    }

    public void stickerCounting(String count)
    {
        stickerCount.setText(count);
    }

    public void stickerLayout()
    {
        stickerFullLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
        stickerCount.setText("0");
        stickerValue = 0;
        setNotSelectedSticker();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.image_add1) {
            openQuickAction(view, 0);
        } else if (id == R.id.image_add2) {
            openQuickAction(view, 1);
        } else if (id == R.id.image_add3) {
            openQuickAction(view, 2);
        } else if (id == R.id.image_add4) {
            openQuickAction(view, 3);
        } else if (id == R.id.image_add5) {
            openQuickAction(view, 4);
        } else if (id == R.id.image_add6) {
            openQuickAction(view, 5);
        } else if (id == R.id.image_add7) {
            openQuickAction(view, 6);
        } else if (id == R.id.image_add8) {
            openQuickAction(view, 7);
        } else if (id == R.id.image_add9) {
            openQuickAction(view, 8);
        }
    }

    public void openQuickAction(View view, int position)
    {
        picChangePosition = position;

        final EasyDialog easyDialog = new EasyDialog(activity);
        easyDialog.setLayoutResourceId(R.layout.quickpopup)
                .setBackgroundColor(getResources().getColor(R.color.Popup_Color))
                .setLocationByAttachedView(view)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_Y, 1000, -800, 100, -50, 50, 0)
                .setAnimationAlphaDismiss(400, 1.0f, 0.0f)
                .setGravity(EasyDialog.GRAVITY_TOP)
                .setTouchOutsideDismiss(true)
                .setMatchParent(false)
                .setMarginLeftAndRight(24, 24)
                .setOutsideColor(Color.parseColor("#66444444"));

        int screenHeight = getScreenHeight(activity);
        int statusBarHeight = getStatusBarHeight(getApplicationContext());
        int dialogHeight = screenHeight - statusBarHeight;

        if (easyDialog.getDialog() != null)
        {
            WindowManager.LayoutParams params = easyDialog.getDialog().getWindow().getAttributes();

            params.gravity = Gravity.TOP;
            params.y = statusBarHeight;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = dialogHeight;

            easyDialog.getDialog().getWindow().setAttributes(params);
        }

        easyDialog.show();

        if(easyDialog.getDialog() != null)
        {
            LinearLayout btnPicChange = (LinearLayout) easyDialog.getDialog().findViewById(R.id.btnPicChange);
            LinearLayout btnClose = (LinearLayout) easyDialog.getDialog().findViewById(R.id.btnClose);
            LinearLayout btnEdit = (LinearLayout) easyDialog.getDialog().findViewById(R.id.btnEdit);
            LinearLayout btnRotate = (LinearLayout) easyDialog.getDialog().findViewById(R.id.btnRotate);

            btnRotate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeFile(touchImageViewArray[picChangePosition].getTag().toString());
                    int unit = rotationUnits[picChangePosition] + 90;
                    rotationUnits[picChangePosition] = unit;
                    bitmap = CompressImage.rotateBitmap(bitmap, unit);
                    touchImageViewArray[picChangePosition].setImageBitmap(bitmap);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PhotoEditingActivity.class);
                    intent.putExtra(Constants.editedImg, touchImageViewArray[picChangePosition].getTag().toString());
                    startActivityForResult(intent, REQUEST_CODE_EDIT_IMG);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    if(easyDialog.getDialog().isShowing())
                    {
                        easyDialog.dismiss();
                    }
                }
            });

            btnPicChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
                    if(easyDialog.getDialog().isShowing())
                    {
                        easyDialog.dismiss();
                    }
                }
            });

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    easyDialog.dismiss();
                }
            });
        }
    }

    private static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case REQUEST_CODE_GALLERY:
                stringArrayList.set(picChangePosition, getPath(getApplicationContext(), data.getData()));
                Bitmap bitmap = Compressor.getDefault(activity).compressToBitmap(new File(stringArrayList.get(picChangePosition)));
                touchImageViewArray[picChangePosition].setImageBitmap(bitmap);
                rotationUnits[picChangePosition] = 0;
                touchImageViewArray[picChangePosition].setTag(stringArrayList.get(picChangePosition));
                break;

            case REQUEST_CODE_EDIT_IMG:
                if(editedImagePath != null)
                {
                    String newPath = editedImagePath;
                    stringArrayList.set(picChangePosition, newPath);
                    Bitmap bitmap1 = Compressor.getDefault(activity).compressToBitmap(new File(stringArrayList.get(picChangePosition)));
                    touchImageViewArray[picChangePosition].setImageBitmap(bitmap1);
                    rotationUnits[picChangePosition] = 0;
                    touchImageViewArray[picChangePosition].setTag(stringArrayList.get(picChangePosition));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void changeVisibility(int grid, int adjust, int background, int frame, int ratio, int color) {
        layout_grid_main.setVisibility(grid);
        layout_pattern_main.setVisibility(background);
        layout_color_main.setVisibility(color);
        layout_border_main.setVisibility(adjust);
        layout_ratio_main.setVisibility(ratio);
        layout_frameborder_main.setVisibility(frame);
    }

    public void addTextSticker(View view)
    {
        if (textFullLayout.getVisibility() == View.VISIBLE)
        {
            textFullLayout.setVisibility(View.GONE);
            if (viewPagerText.getCurrentItem() == 0)
                dismissSoftKeyboard(inputMethodManager, addTxtEditText);
            viewPagerText.setCurrentItem(1);
        }
        String text = addTxtEditText.getText().toString();
        if (!addTxtEditText.getText().toString().equals(""))
        {
            if (temp_sticker != null)
            {
                temp_sticker.setText(text);
                temp_sticker.setTypeface(addTxtEditText.getTypeface());
                temp_sticker.setTextColor(addTxtEditText.getCurrentTextColor());
                Drawable bubble = ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_background);
                if (bgStatus == true) {
                    if (currentBg == 0) {
                        bubble = activity.getResources().getDrawable(R.drawable.stroke_rect);
                        ColorFilter filter = new LightingColorFilter(bgColor, bgColor);
                        bubble.setColorFilter(filter);
                        bubble.setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    } else {
                        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(bgColor)).getBitmap();
                        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 800, 400, true));
                        bubble = drawable;
                        bubble.setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    }
                }
                temp_sticker.setDrawable(bubble);
                temp_sticker.setTextShadowColor(shadowRadius, shadowX, shadowY, shadowColor);
                temp_sticker.setAlpha(addTxtEditText.getPaint().getAlpha());
                temp_sticker.resizeText();
                stickerView.invalidate();
            }
            else
            {
                Drawable bubble = ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_background);

                if (bgStatus == true) {
                    if (currentBg == 0) {
                        bubble = activity.getResources().getDrawable(R.drawable.stroke_rect);
                        ColorFilter filter = new LightingColorFilter(bgColor, bgColor);
                        bubble.setColorFilter(filter);
                        bubble.setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    } else {
                        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(bgColor)).getBitmap();
                        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 800, 400, true));
                        bubble = drawable;
                        bubble.setAlpha(Math.round((float) opacityBGProgress / 100 * 255));
                    }
                }

                sticker = new TextSticker(getApplicationContext());
                sticker.setDrawable(bubble);
                sticker.setText(text);
                sticker.resizeText();
                stickerView.addSticker(sticker);
                temp_sticker = sticker;

                if (fontTypeface != null)
                    temp_sticker.setTypeface(fontTypeface);

                temp_sticker.setTextShadowColor(shadowRadius, shadowX, shadowY, shadowColor);

                if (currentColor == 0) {
                    temp_sticker.setTextColor(txtColor);
                } else {
                    temp_sticker.setShader(activity, BitmapFactory.decodeResource(getResources(), txtColor));
                }

                temp_sticker.setAlpha(Math.round((float) opacityTxtProgress / 100 * 255));

                stickerView.invalidate();
            }
            isStickerTouch = true;
        }
    }

    @Override
    protected void onPause() {
        if (textFullLayout.getVisibility() == View.VISIBLE)
            if (viewPagerText.getCurrentItem() == 0)
                dismissSoftKeyboard(inputMethodManager, addTxtEditText);
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (textFullLayout.getVisibility() == View.VISIBLE)
            if (viewPagerText.getCurrentItem() == 0)
                showSoftKeyboard(inputMethodManager, addTxtEditText);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.menu_done) {
            if (!stickerView.isLocked()) {
                stickerView.setLocked(true);
            }

            takeScreenshot(getResources().getString(R.string.save_image_name_2), captureLayout, activity);

            if (stickerView.isLocked()) {
                stickerView.setLocked(false);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(stickerFullLayout.getVisibility() == View.GONE && textFullLayout.getVisibility() == View.GONE)
        {
            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

            alertDialogbuilder.setMessage(getResources().getString(R.string.alert_save_image));
            alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }
            });
            alertDialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialogbuilder.setCancelable(false);
            alertDialogbuilder.show();
        }
        else if(stickerFullLayout.getVisibility() == View.VISIBLE)
        {
            stickerLayout();
        }
        else if (textFullLayout.getVisibility() == View.VISIBLE)
        {
            if (viewPagerText.getCurrentItem() == 0)
            {
                dismissSoftKeyboard(inputMethodManager, addTxtEditText);
            }
            textFullLayout.setVisibility(View.GONE);
            viewPagerText.setCurrentItem(1, true);
        }
    }
}
