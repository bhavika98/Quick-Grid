package com.app.incroyable.quickgrid.ui.grid_3d;

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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.percentlayout.widget.PercentFrameLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.mylib.grid3d.PorterShapeImageView;
import com.app.mylib.grid3d.TouchImageView;
import com.app.mylib.stickerview.DrawableSticker;
import com.app.mylib.stickerview.Sticker;
import com.app.mylib.stickerview.StickerView;
import com.app.mylib.stickerview.TextSticker;
import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.Grid3DAdapter;
import com.app.incroyable.quickgrid.fragment.Fragment_Sticker;
import com.app.incroyable.quickgrid.fragment.Fragment_Text;
import com.app.incroyable.quickgrid.model.Grid3D;
import com.app.incroyable.quickgrid.model.StickerData;
import com.app.incroyable.quickgrid.ui.PhotoEditingActivity;
import com.app.incroyable.quickgrid.ui.grid.Fragment_Empty;
import com.app.incroyable.quickgrid.util.CompressImage;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.CustomViewPager;
import com.app.incroyable.quickgrid.util.MyDragShadowBuilder;
import com.michael.easydialog.EasyDialog;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
import static com.app.incroyable.quickgrid.util.DataBinder.fetchGrid3dData;
import static com.app.incroyable.quickgrid.util.DataBinder.setNotSelectedSticker;
import static com.app.incroyable.quickgrid.util.SaveImage.takeScreenshot;

public class GridEditActivity extends AppCompatActivity implements View.OnClickListener, Grid3DAdapter.Grid3DCallback{

    Activity activity = GridEditActivity.this;
    LinearLayout mainFrameLayout, topLayer;
    View layoutView;
    int currentTab;
    PercentFrameLayout pipImage;
    int top_width, top_height, cap_width, cap_height;
    LinearLayout.LayoutParams layoutParams;

    int length, currentLayout, currentGrid, picChangePosition, sizeGrid;
    ArrayList<String> listImages = new ArrayList<>();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Grid3DAdapter gridAdapter;
    ArrayList<Grid3D> grid3DArrayList = new ArrayList<>();

    LinearLayout captureLayout, templateLayoutMain;

    int[] resourceTouchImageView = {R.id.touchImageView1, R.id.touchImageView2, R.id.touchImageView3, R.id.touchImageView4, R.id.touchImageView5};
    PorterShapeImageView[] touchImageViewArray;
    int[] rotationUnits;

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
        setContentView(R.layout.activity_grid_edit);

        bindToolbar();
        bindControls();
        clickEvents();
        setupLayout();
        setGrid(length);

        // Sticker Layout
        openDialog();

        // Text Sticker Layout
        bindTextStickerView();

        bingPager();
    }

    private void bingPager() {

        ViewGroup tabBottom = (ViewGroup) findViewById(R.id.tabBottom);
        tabBottom.addView(LayoutInflater.from(this).inflate(R.layout.custom_tab_grid3d, tabBottom, false));

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
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.template_icon));
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.sticker_btn));
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.text_btn));
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

        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        viewPagerBottom.setAdapter(fragmentPagerItemAdapter);
        viewPagerBottomTab.setViewPager(viewPagerBottom);

        viewPagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(!(position == 1 || position == 2))
                    currentTab = position;

                switch (position) {
                    case 0:
                        templateLayoutMain.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        viewPagerBottom.setCurrentItem(currentTab, true);
                        stickerLayoutMethod();
                        break;

                    case 2:
                        viewPagerBottom.setCurrentItem(currentTab, true);
                        textLayout();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void bindTextStickerView() {

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        addTxtEditText = (EditText) findViewById(R.id.addTxtEditText);
        textFullLayout = (LinearLayout) findViewById(R.id.textFullLayout);

        ViewGroup tabText = (ViewGroup) findViewById(R.id.tabTextSticker);
        tabText.addView(LayoutInflater.from(this).inflate(R.layout.custom_tab_bottom, tabText, false));

        viewPagerText = (CustomViewPager) findViewById(R.id.viewPagerTextSticker);
        SmartTabLayout viewPagerTabText = (SmartTabLayout) findViewById(R.id.viewPagerBottomTab);
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

    private void clickEvents() {

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

    private void setupLayout() {

        layoutView = getLayoutInflater().inflate(currentLayout, mainFrameLayout, false);
        mainFrameLayout.addView((View) layoutView);
        pipImage = (PercentFrameLayout) layoutView.findViewById(R.id.pipImage);

        topLayer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                topLayer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                top_width = (int) (topLayer.getMeasuredWidth() / Resources.getSystem().getDisplayMetrics().density);
                top_height = (int) (topLayer.getMeasuredHeight() / Resources.getSystem().getDisplayMetrics().density);

                if(sizeGrid == 1)
                {
                    if(top_width < top_height)
                    {
                        cap_width = top_width;
                        cap_height = top_width;
                    }
                    else
                    {
                        cap_width = top_height;
                        cap_height = top_height;
                    }
                }
                else if(sizeGrid == 2)
                {
                    cap_width = (int) (top_height/1.5);
                    cap_height = top_height;
                }
                int pixels_w = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cap_width, activity.getResources().getDisplayMetrics()));
                int pixels_h = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cap_height, activity.getResources().getDisplayMetrics()));
                layoutParams = new LinearLayout.LayoutParams(pixels_w, pixels_h);
                captureLayout.setLayoutParams(layoutParams);
            }
        });

//        if(sizeGrid == 1)
//        {
//            int dp = 350;
//            int pixels = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics()));
//            layoutParams = new LinearLayout.LayoutParams(pixels, pixels);
//        }
//        else if(sizeGrid == 2)
//        {
//            int width = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, activity.getResources().getDisplayMetrics()));
//            layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
//        }

        rotationUnits = new int[length];

        for (int k = 0; k < length; k++) {
            rotationUnits[k] = 0;
        }

        touchImageViewArray = new PorterShapeImageView[length];

        for(int i = 0; i < length; i++)
        {
            touchImageViewArray[i] = (PorterShapeImageView) layoutView.findViewById(resourceTouchImageView[i]);
        }

        for(int i=0; i<length; i++)
        {
            Bitmap bitmap = Compressor.getDefault(activity).compressToBitmap(new File(listImages.get(i)));
            touchImageViewArray[i].setImageBitmap(bitmap);
            touchImageViewArray[i].setTag(listImages.get(i));
            touchImageViewArray[i].setOnClickListener(this);
        }

        if(length == 1)
        {
            if(currentGrid == 2 || currentGrid == 10 || currentGrid == 11 || currentGrid == 12)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(listImages.get(0), new BitmapFactory.Options());
                bitmap = CompressImage.cropCenter(bitmap);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                {
                    bitmap = CompressImage.blurImage(bitmap, activity);
                }
                else
                {
                    bitmap = CompressImage.fastblur(bitmap);
                }
                pipImage.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }
        }

        dragdrop();
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
                    TouchImageView from = (TouchImageView) event.getLocalState();
                    TouchImageView to = (TouchImageView) v;

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

                                    for (int i = 0; i < listImages.size(); i++) {
                                        if (listImages.get(i).equals(path) || listImages.get(i).equals(path1)) {
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
    public void Grid3DMethod(int position) {
        mainFrameLayout.removeAllViews();
        currentLayout = grid3DArrayList.get(position).getLayout();
        currentGrid = grid3DArrayList.get(position).getId();
        sizeGrid = grid3DArrayList.get(position).getSize();
        setupLayout();
    }

    public class swapImages extends AsyncTask<String,Void,Void> {

        String path, path1;
        TouchImageView from, to;
        Bitmap bitmap, bitmap1;
        ProgressDialog progressDialog;

        public swapImages(String path, String path1, TouchImageView from, TouchImageView to)
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

    private void bindControls() {
        mainFrameLayout = (LinearLayout) findViewById(R.id.mainFrameLayout);
        topLayer = (LinearLayout) findViewById(R.id.topLayer);

        captureLayout = (LinearLayout) findViewById(R.id.captureLayout);
        templateLayoutMain = (LinearLayout) findViewById(R.id.templateLayoutMain);
        captureLayout = (LinearLayout) findViewById(R.id.captureLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

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

        length = Integer.parseInt(getIntent().getExtras().getString(Constants.imgId));
        listImages = getIntent().getExtras().getStringArrayList(Constants.imgList);
        currentLayout = Integer.parseInt(getIntent().getExtras().getString(Constants.imgSource));
        currentGrid = getIntent().getExtras().getInt(Constants.currentGrid);
        sizeGrid = Integer.valueOf(getIntent().getExtras().getString(Constants.sizeGrid));
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.GridEditActivity_Title));
    }

    public void setGrid(int pos)
    {
        grid3DArrayList.clear();
        for(int i=0; i<fetchGrid3dData().size(); i++)
        {
            if(fetchGrid3dData().get(i).getImageNumber() == pos)
                grid3DArrayList.add(fetchGrid3dData().get(i));
        }

        Collections.sort(grid3DArrayList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Grid3D grid1 = (Grid3D) o1;
                Grid3D grid2 = (Grid3D) o2;
                return String.valueOf(grid2.getSize()).compareToIgnoreCase(String.valueOf(grid1.getSize()));
            }
        });

        gridAdapter = new Grid3DAdapter(activity, grid3DArrayList, currentGrid);
        recyclerView.setAdapter(gridAdapter);
    }

    public void stickerLayoutMethod()
    {

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

    public void textLayout()
    {
        textFullLayout.setVisibility(View.VISIBLE);

        if (!isStickerTouch) {
            setDefaultValues(addTxtEditText, activity);
            temp_sticker = null;
        }
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
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.touchImageView1) {
            openQuickAction(view, 0);
        } else if (id == R.id.touchImageView2) {
            openQuickAction(view, 1);
        } else if (id == R.id.touchImageView3) {
            openQuickAction(view, 2);
        } else if (id == R.id.touchImageView4) {
            openQuickAction(view, 3);
        } else if (id == R.id.touchImageView5) {
            openQuickAction(view, 4);
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
                listImages.set(picChangePosition, getPath(getApplicationContext(), data.getData()));
                Bitmap bitmap = Compressor.getDefault(activity).compressToBitmap(new File(listImages.get(picChangePosition)));
                touchImageViewArray[picChangePosition].setImageBitmap(bitmap);
                rotationUnits[picChangePosition] = 0;
                touchImageViewArray[picChangePosition].setTag(listImages.get(picChangePosition));
                break;

            case REQUEST_CODE_EDIT_IMG:
                if(editedImagePath != null)
                {
                    String newPath = editedImagePath;
                    listImages.set(picChangePosition, newPath);
                    Bitmap bitmap1 = Compressor.getDefault(activity).compressToBitmap(new File(listImages.get(picChangePosition)));
                    touchImageViewArray[picChangePosition].setImageBitmap(bitmap1);
                    rotationUnits[picChangePosition] = 0;
                    touchImageViewArray[picChangePosition].setTag(listImages.get(picChangePosition));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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

            takeScreenshot(getResources().getString(R.string.save_image_name_1), captureLayout, activity);

            if (stickerView.isLocked()) {
                stickerView.setLocked(false);
            }

            return true;
        }

        return false;
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
