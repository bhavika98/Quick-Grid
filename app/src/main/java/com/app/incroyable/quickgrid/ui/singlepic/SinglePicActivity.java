package com.app.incroyable.quickgrid.ui.singlepic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.mylib.navigation.SpaceItem;
import com.app.mylib.navigation.SpaceNavigationView;
import com.app.mylib.navigation.SpaceOnClickListener;
import com.app.mylib.singlepic.TouchImageView;
import com.app.mylib.stickerview.DrawableSticker;
import com.app.mylib.stickerview.Sticker;
import com.app.mylib.stickerview.StickerView;
import com.app.mylib.stickerview.TextSticker;
import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.FilterAdapter;
import com.app.incroyable.quickgrid.fragment.Fragment_Sticker;
import com.app.incroyable.quickgrid.fragment.Fragment_Text;
import com.app.incroyable.quickgrid.model.Crop;
import com.app.incroyable.quickgrid.model.Filter;
import com.app.incroyable.quickgrid.model.StickerData;
import com.app.incroyable.quickgrid.ui.grid.Fragment_Empty;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.CustomViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import id.zelory.compressor.Compressor;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

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
import static com.app.incroyable.quickgrid.util.Constants.goneLayout;
import static com.app.incroyable.quickgrid.util.Constants.normalColor;
import static com.app.incroyable.quickgrid.util.Constants.selectedColor;
import static com.app.incroyable.quickgrid.util.Constants.visibleLayout;
import static com.app.incroyable.quickgrid.util.DataBinder.applyFilter;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchCrop;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchFilters;
import static com.app.incroyable.quickgrid.util.DataBinder.setNotSelectedSticker;
import static com.app.incroyable.quickgrid.util.SaveImage.takeScreenshot;

public class SinglePicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, FilterAdapter.FilterCallback, CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener{

    Activity activity = SinglePicActivity.this;

    String imagePath;
    TouchImageView imageView;
    LinearLayout captureLayout;
    SpaceNavigationView navigationView;
    Bitmap originalBitmap, bitmap;

    // Adjust Layout
    LinearLayout adjustLayout;
    LinearLayout brightnessLayout, contrastLayout, vignetteLayout, saturationLayout, sharpLayout;
    TextView txtBrightness, txtContrast, txtVignette, txtSaturation, txtSharp;
    SeekBar brightnessSeekbar, contrastSeekbar, vignetteSeekbar, saturationSeekbar, sharpSeekbar;
    float start, end, value;
    GPUImageView gpuImageViewAdjust;
    ImageView imageViewAdjust;

    // Filter Layout
    LinearLayout filterLayout;
    RecyclerView recyclerViewFilter;
    LinearLayoutManager linearLayoutManagerFilter;
    ArrayList<Filter> filterArrayList = new ArrayList<>();
    FilterAdapter filterAdapter;
    GPUImageView gpuImageView;
    ImageView imageViewFilter;
    ProgressBar progressBar;

    // Crop Layout
    LinearLayout cropLayout;
    CropImageView cropImageView;
    String selection = "0";
    RecyclerView recyclerViewCrop;
    LinearLayoutManager linearLayoutManager;
    CropAdapter cropAdapter;
    ArrayList<Crop> cropArrayList = new ArrayList<>();

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
        setContentView(R.layout.activity_single_pic);
        
        bindToolbar();
        bindControls();
        clickEvents();
        bingPager();
        fetchData();

        imagePath = getIntent().getStringArrayListExtra(Constants.imgList).get(0);
        originalBitmap = Compressor.getDefault(activity).compressToBitmap(new File(imagePath));
        bitmap = originalBitmap;
        imageView.setImageBitmap(originalBitmap);

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
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.lightness_btn));
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.contrast_btn));
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.blur_btn));
                        break;
                    case 3:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.saturation_btn));
                        break;
                    case 4:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.warmth_btn));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });

        FragmentPagerItems pages = new FragmentPagerItems(activity);
        pages.add(FragmentPagerItem.of("Fragment_Empty", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Empty", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Empty", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Empty", Fragment_Empty.class));
        pages.add(FragmentPagerItem.of("Fragment_Empty", Fragment_Empty.class));

        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        viewPagerBottom.setAdapter(fragmentPagerItemAdapter);
        viewPagerBottomTab.setViewPager(viewPagerBottom);

        viewPagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 1:
                        changeVisibility(goneLayout, visibleLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 2:
                        changeVisibility(goneLayout, goneLayout, visibleLayout, goneLayout, goneLayout);
                        break;

                    case 3:
                        changeVisibility(goneLayout, goneLayout, goneLayout, visibleLayout, goneLayout);
                        break;

                    case 4:
                        changeVisibility(goneLayout, goneLayout, goneLayout, goneLayout, visibleLayout);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void fetchData() {
        filterArrayList.clear();
        filterArrayList = fetchFilters();
        filterAdapter = new FilterAdapter(activity, filterArrayList);
        recyclerViewFilter.setAdapter(filterAdapter);
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.SinglePicActivity_Title));
    }

    private void clickEvents() {

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                fillStickerData();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                itemClick(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                itemClick(itemIndex);
            }
        });

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

    public void itemClick(int itemIndex)
    {
        if(itemIndex == 0)
        {
            gpuImageView.setImage(originalBitmap);
            imageViewFilter.setImageBitmap(originalBitmap);
            filterAdapter.setSelection(0);
            filterLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);
        }
        if(itemIndex == 1)
        {
            loadData();
            cropLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);
        }
        if(itemIndex == 2)
        {
            textFullLayout.setVisibility(View.VISIBLE);

            if (!isStickerTouch) {
                setDefaultValues(addTxtEditText, activity);
                temp_sticker = null;
            }
        }
        if(itemIndex == 3)
        {
            gpuImageViewAdjust.setImage(originalBitmap);
            imageViewAdjust.setImageBitmap(originalBitmap);
            adjustLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);
        }
    }

    private void bindControls() {
        captureLayout = (LinearLayout) findViewById(R.id.captureLayout);
        imageView = (TouchImageView) findViewById(R.id.imageView);
        navigationView = (SpaceNavigationView) findViewById(R.id.navigationView);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.cam_filter));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.crop_btn));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.text_btn));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.adjust_btn));

        // Adjust Layout
        adjustLayout = (LinearLayout) findViewById(R.id.adjustLayout);
        filterLayout = (LinearLayout) findViewById(R.id.filterLayout);
        brightnessLayout = (LinearLayout) findViewById(R.id.brightnessLayout);
        contrastLayout = (LinearLayout) findViewById(R.id.contrastLayout);
        vignetteLayout = (LinearLayout) findViewById(R.id.vignetteLayout);
        saturationLayout = (LinearLayout) findViewById(R.id.saturationLayout);
        sharpLayout = (LinearLayout) findViewById(R.id.sharpLayout);
        txtBrightness = (TextView) findViewById(R.id.txtBrightness);
        txtContrast = (TextView) findViewById(R.id.txtContrast);
        txtVignette = (TextView) findViewById(R.id.txtVignette);
        txtSaturation = (TextView) findViewById(R.id.txtSaturation);
        txtSharp = (TextView) findViewById(R.id.txtSharp);
        brightnessSeekbar = (SeekBar) findViewById(R.id.brightnessSeekbar);
        contrastSeekbar = (SeekBar) findViewById(R.id.contrastSeekbar);
        vignetteSeekbar = (SeekBar) findViewById(R.id.vignetteSeekbar);
        saturationSeekbar = (SeekBar) findViewById(R.id.saturationSeekbar);
        sharpSeekbar = (SeekBar) findViewById(R.id.sharpSeekbar);
        brightnessSeekbar.setOnSeekBarChangeListener(this);
        contrastSeekbar.setOnSeekBarChangeListener(this);
        vignetteSeekbar.setOnSeekBarChangeListener(this);
        saturationSeekbar.setOnSeekBarChangeListener(this);
        sharpSeekbar.setOnSeekBarChangeListener(this);
        gpuImageViewAdjust = (GPUImageView) findViewById(R.id.gpuImageViewAdjust);
        imageViewAdjust = (ImageView) findViewById(R.id.imageViewAdjust);
        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout);

        // Filter Layout
        filterLayout = (LinearLayout) findViewById(R.id.filterLayout);
        recyclerViewFilter = (RecyclerView) findViewById(R.id.recyclerViewFilter);
        linearLayoutManagerFilter = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFilter.setLayoutManager(linearLayoutManagerFilter);
        gpuImageView = (GPUImageView) findViewById(R.id.gpuImageView);
        imageViewFilter = (ImageView) findViewById(R.id.imageViewFilter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Crop Layout
        cropLayout = (LinearLayout) findViewById(R.id.cropLayout);
        recyclerViewCrop = (RecyclerView) findViewById(R.id.recyclerViewCrop);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCrop.setLayoutManager(linearLayoutManager);
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        cropImageView.setFixedAspectRatio(false);
        cropImageView.setShowCropOverlay(true);
        cropImageView.setGuidelines(CropImageView.Guidelines.ON_TOUCH);
        cropImageView.setCropShape(CropImageView.CropShape.RECTANGLE);
        cropImageView.setOnSetImageUriCompleteListener(this);
        cropImageView.setOnCropImageCompleteListener(this);

        // Sticker Layout
        stickerFullLayout = (LinearLayout) findViewById(R.id.stickerFullLayout);
        stickerCross = (LinearLayout) findViewById(R.id.stickerCross);
        stickerCheck = (LinearLayout) findViewById(R.id.stickerCheck);
        stickerCount = (TextView) findViewById(R.id.stickerCount);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setVisibility(View.VISIBLE);
        stickerFullLayout.setVisibility(View.GONE);
        stickerView = (StickerView) findViewById(R.id.sticker_view);
        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);
        stickerView.setConstrained(true);
    }

    private void openDialog() {

        setNotSelectedSticker();
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_icons, tab, false));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        final LayoutInflater inflater = LayoutInflater.from(viewPagerTab.getContext());
        viewPagerTab.setCustomTabView(
                new SmartTabLayout.TabProvider() {
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

    public void adjustCross(View view)
    {
        adjustLayout();
    }

    public void adjustCheck(View view)
    {
        bitmap = gpuImageViewAdjust.getGPUImage().getBitmapWithFilterApplied();
        imageView.setImageBitmap(bitmap);
        adjustLayout();
    }

    public void adjustLayout()
    {
        adjustLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void cropLayout()
    {
        cropLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void filterLayout()
    {
        filterLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void filterCross(View view)
    {
        filterLayout();
    }

    public void filterCheck(View view)
    {
        bitmap = gpuImageView.getGPUImage().getBitmapWithFilterApplied();
        imageView.setImageBitmap(bitmap);
        filterLayout();
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

    @Override
    public void FilterMethod(final int position) {

        //progressBar.setVisibility(View.VISIBLE);
        gpuImageView.setFilter(applyFilter(position, activity));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bitmap = gpuImageView.getGPUImage().getBitmapWithFilterApplied();
                imageViewFilter.setImageBitmap(bitmap);
                //progressBar.setVisibility(View.GONE);
            }
        }, 100);
    }

    public void cropCross(View view) {
        cropLayout();
    }

    public void cropCheck(View view){
        cropImageView.getCroppedImageAsync();
        cropLayout();
    }

    public void cropLeft(View view){
        cropImageView.rotateImage(-90);
    }

    public void cropRight(View view){
        cropImageView.rotateImage(90);
    }

    @Override
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        if (result.getError() == null)
        {
            originalBitmap = cropImageView.getCropShape() == CropImageView.CropShape.OVAL
                    ? CropImage.toOvalBitmap(result.getBitmap())
                    : result.getBitmap();
            imageView.setImageBitmap(originalBitmap);
            bitmap = originalBitmap;
        }
        else
        {
            Toast.makeText(this, "Image crop failed.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null)
        {}
        else
        {
            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

            alertDialogbuilder.setMessage(getResources().getString(R.string.sdcard_error));
            alertDialogbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }
            });

            alertDialogbuilder.setCancelable(false);
            alertDialogbuilder.show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();

        if (id == R.id.brightnessSeekbar) {
            start = -0.5f;
            end = 0.5f;
            value = (end - start) * progress / 100.0f + start;
            gpuImageViewAdjust.setFilter(new GPUImageBrightnessFilter(value));
            txtBrightness.setText(String.valueOf(progress));
        } else if (id == R.id.contrastSeekbar) {
            start = 0.4f;
            end = 2.0f;
            value = (end - start) * progress / 100.0f + start;
            gpuImageViewAdjust.setFilter(new GPUImageContrastFilter(value));
            txtContrast.setText(String.valueOf(progress));
        } else if (id == R.id.vignetteSeekbar) {
            start = 0.0f;
            end = 1.0f;
            value = (end - start) * progress / 100.0f + start;
            gpuImageViewAdjust.setFilter(new GPUImageGaussianBlurFilter(value));
            txtVignette.setText(String.valueOf(progress));
        } else if (id == R.id.saturationSeekbar) {
            start = 0.0f;
            end = 2.0f;
            value = (end - start) * progress / 100.0f + start;
            gpuImageViewAdjust.setFilter(new GPUImageSaturationFilter(value));
            txtSaturation.setText(String.valueOf(progress));
        } else if (id == R.id.sharpSeekbar) {
            start = 0.0f;
            end = 360.0f;
            value = (end - start) * progress / 100.0f + start;
            gpuImageViewAdjust.setFilter(new GPUImageHueFilter(value));
            txtSharp.setText(String.valueOf(progress));
        }

        bitmap = gpuImageViewAdjust.getGPUImage().getBitmapWithFilterApplied();
        imageViewAdjust.setImageBitmap(bitmap);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void changeVisibility(int brightness, int contrast, int vignette, int saturation, int sharp) {
        brightnessLayout.setVisibility(brightness);
        contrastLayout.setVisibility(contrast);
        vignetteLayout.setVisibility(vignette);
        saturationLayout.setVisibility(saturation);
        sharpLayout.setVisibility(sharp);
    }

    public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ViewHolder> {

        ArrayList<Crop> list;
        Context context;

        public CropAdapter(Context context, ArrayList<Crop> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.adapter_crop, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


            if(selection.equals(""))
            {
                holder.cropImage.setColorFilter(ContextCompat.getColor(getApplicationContext(), normalColor));
                holder.cropText.setTextColor(ContextCompat.getColor(getApplicationContext(), normalColor));
            }
            else
            {
                if(position == Integer.parseInt(selection))
                {
                    holder.cropImage.setColorFilter(ContextCompat.getColor(getApplicationContext(), selectedColor));
                    holder.cropText.setTextColor(ContextCompat.getColor(getApplicationContext(), selectedColor));
                }
                else
                {
                    holder.cropImage.setColorFilter(ContextCompat.getColor(getApplicationContext(), normalColor));
                    holder.cropText.setTextColor(ContextCompat.getColor(getApplicationContext(), normalColor));
                }
            }

            holder.cropImage.setImageResource(list.get(position).getImage());
            holder.cropText.setText(list.get(position).getText());

            holder.border.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selection = String.valueOf(position);
                    notifyDataSetChanged();

                    if(position == 0)
                    {
                        cropImageView.setFixedAspectRatio(false);
                    }
                    else
                    {
                        cropImageView.setFixedAspectRatio(true);
                        cropImageView.setAspectRatio(list.get(position).getLeft(), list.get(position).getRight());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView cropImage;
            TextView cropText;
            LinearLayout border;

            public ViewHolder(View itemView) {
                super(itemView);
                cropImage = (ImageView) itemView.findViewById(R.id.cropImage);
                cropText = (TextView) itemView.findViewById(R.id.cropText);
                border = (LinearLayout) itemView.findViewById(R.id.border);
            }
        }
    }

    private void loadData() {

        cropImageView.setImageBitmap(originalBitmap);
        cropArrayList.clear();
        cropArrayList = fetchCrop();
        cropAdapter = new CropAdapter(getApplicationContext(), cropArrayList);
        recyclerViewCrop.setAdapter(cropAdapter);
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
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (itemId == R.id.menu_done) {
            if (!stickerView.isLocked()) {
                stickerView.setLocked(true);
            }

            takeScreenshot(getResources().getString(R.string.save_image_name_2), captureLayout, activity);

            if (stickerView.isLocked()) {
                stickerView.setLocked(false);
            }

            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if(stickerFullLayout.getVisibility() == View.GONE && cropLayout.getVisibility() == View.GONE && filterLayout.getVisibility() == View.GONE && adjustLayout.getVisibility() == View.GONE && textFullLayout.getVisibility() == View.GONE)
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
        else if(cropLayout.getVisibility() == View.VISIBLE)
        {
            cropLayout();
        }
        else if(filterLayout.getVisibility() == View.VISIBLE)
        {
            filterLayout();
        }
        else if(adjustLayout.getVisibility() == View.VISIBLE)
        {
            adjustLayout();
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
