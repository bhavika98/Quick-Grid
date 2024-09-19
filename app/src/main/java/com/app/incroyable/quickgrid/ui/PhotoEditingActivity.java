package com.app.incroyable.quickgrid.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.FilterAdapter;
import com.app.incroyable.quickgrid.model.Filter;
import com.app.incroyable.quickgrid.ui.grid.Fragment_Empty;
import com.app.incroyable.quickgrid.util.CompressImage;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.CustomViewPager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

import static com.app.incroyable.quickgrid.util.Constants.editedImagePath;
import static com.app.incroyable.quickgrid.util.Constants.goneLayout;
import static com.app.incroyable.quickgrid.util.Constants.visibleLayout;
import static com.app.incroyable.quickgrid.util.DataBinder.applyFilter;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchFilters;
import static com.app.incroyable.quickgrid.util.SaveImage.copyDirectoryOneLocationToAnotherLocation;
import static com.app.incroyable.quickgrid.util.SaveImage.deleteFileFromMediaStore;

public class PhotoEditingActivity extends AppCompatActivity implements FilterAdapter.FilterCallback, SeekBar.OnSeekBarChangeListener, GPUImageView.OnPictureSavedListener {

    Activity activity = PhotoEditingActivity.this;

    RecyclerView recyclerViewFilter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Filter> filterArrayList = new ArrayList<>();
    FilterAdapter filterAdapter;

    GPUImageView imageView;
    LinearLayout filterLayout, brightnessLayout, contrastLayout, vignetteLayout, saturationLayout, sharpLayout;
    TextView txtBrightness, txtContrast, txtVignette, txtSaturation, txtSharp;
    SeekBar brightnessSeekbar, contrastSeekbar, vignetteSeekbar, saturationSeekbar, sharpSeekbar;

    float start, end, value;
    String folder = "", finalPath = "", filename = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editing);

        bindToolbar();
        bingPager();
        bindControls();
        initView();
        fetchFilterData();
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
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.cam_filter));
                        break;
                    case 1:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.lightness_btn));
                        break;
                    case 2:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.contrast_btn));
                        break;
                    case 3:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.blur_btn));
                        break;
                    case 4:
                        icon.setImageDrawable(getResources().getDrawable(R.drawable.saturation_btn));
                        break;
                    case 5:
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
                        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 1:
                        changeVisibility(goneLayout, visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 2:
                        changeVisibility(goneLayout, goneLayout, visibleLayout, goneLayout, goneLayout, goneLayout);
                        break;

                    case 3:
                        changeVisibility(goneLayout, goneLayout, goneLayout, visibleLayout, goneLayout, goneLayout);
                        break;

                    case 4:
                        changeVisibility(goneLayout, goneLayout, goneLayout, goneLayout, visibleLayout, goneLayout);
                        break;

                    case 5:
                        changeVisibility(goneLayout, goneLayout, goneLayout, goneLayout, goneLayout, visibleLayout);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initView() {
        editedImagePath = getIntent().getExtras().getString(Constants.editedImg);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(editedImagePath, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        if(imageHeight > 1000 && imageWidth > 1000 && imageHeight < 2000 && imageWidth < 2000)
        {
            imageHeight = imageHeight/2;
            imageWidth = imageWidth/2;
        }
        else if(imageHeight > 2000 || imageWidth > 2000)
        {
            if(imageHeight < 3000 || imageWidth < 3000)
            {
                imageHeight = imageHeight/3;
                imageWidth = imageWidth/3;
            }
            else if(imageHeight > 3000 || imageWidth > 3000)
            {
                if(imageHeight < 4000 || imageWidth < 4000)
                {
                    imageHeight = imageHeight/4;
                    imageWidth = imageWidth/4;
                }
                else if(imageHeight > 4000 || imageWidth > 4000)
                {
                    imageHeight = imageHeight/5;
                    imageWidth = imageWidth/5;
                }
            }
        }
        else if(imageHeight > 1000 || imageWidth > 1000)
        {
            imageHeight = (int) (imageHeight/1.6);
            imageWidth = (int) (imageWidth/1.6);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        layoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(layoutParams);

        Bitmap bitmap = CompressImage.originalBitmap(BitmapFactory.decodeFile(editedImagePath));
        imageView.setImage(bitmap);

        changeVisibility(visibleLayout, goneLayout, goneLayout, goneLayout, goneLayout, goneLayout);
    }

    private void fetchFilterData() {
        filterArrayList.clear();
        filterArrayList = fetchFilters();
        filterAdapter = new FilterAdapter(activity, filterArrayList);
        recyclerViewFilter.setAdapter(filterAdapter);
    }

    private void bindControls() {
        recyclerViewFilter = (RecyclerView) findViewById(R.id.recyclerViewFilter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFilter.setLayoutManager(linearLayoutManager);

        imageView = (GPUImageView) findViewById(R.id.imageView);
        imageView.setScaleType(GPUImage.ScaleType.CENTER_CROP);
        imageView.setBackgroundColor(245f, 245f, 245f);

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
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.PhotoEditingActivity_Title));
    }

    public void changeVisibility(int filter, int brightness, int contrast, int vignette, int saturation, int sharp) {
        filterLayout.setVisibility(filter);
        brightnessLayout.setVisibility(brightness);
        contrastLayout.setVisibility(contrast);
        vignetteLayout.setVisibility(vignette);
        saturationLayout.setVisibility(saturation);
        sharpLayout.setVisibility(sharp);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        float start, end, value;

        if (id == R.id.brightnessSeekbar) {
            start = -0.5f;
            end = 0.5f;
            value = (end - start) * progress / 100.0f + start;
            imageView.setFilter(new GPUImageBrightnessFilter(value));
            txtBrightness.setText(String.valueOf(progress));
        } else if (id == R.id.contrastSeekbar) {
            start = 0.4f;
            end = 2.0f;
            value = (end - start) * progress / 100.0f + start;
            imageView.setFilter(new GPUImageContrastFilter(value));
            txtContrast.setText(String.valueOf(progress));
        } else if (id == R.id.vignetteSeekbar) {
            start = 0.0f;
            end = 1.0f;
            value = (end - start) * progress / 100.0f + start;
            imageView.setFilter(new GPUImageGaussianBlurFilter(value));
            txtVignette.setText(String.valueOf(progress));
        } else if (id == R.id.saturationSeekbar) {
            start = 0.0f;
            end = 2.0f;
            value = (end - start) * progress / 100.0f + start;
            imageView.setFilter(new GPUImageSaturationFilter(value));
            txtSaturation.setText(String.valueOf(progress));
        } else if (id == R.id.sharpSeekbar) {
            start = 0.0f;
            end = 360.0f;
            value = (end - start) * progress / 100.0f + start;
            imageView.setFilter(new GPUImageHueFilter(value));
            txtSharp.setText(String.valueOf(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void FilterMethod(int position) {
        imageView.setFilter(applyFilter(position, activity));
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
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String folder = "temp";
            String filename = System.currentTimeMillis() + ".jpg";
            File file = new File(path, folder + "/" + filename);
            finalPath = file.toString();
            imageView.saveToPictures(folder, filename, this);
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        super.onBackPressed();
    }

    @Override
    public void onPictureSaved(Uri uri) {

        File from = new File(finalPath);

        File to = new File(getBaseContext().getCacheDir(), "temp" + "/" +System.currentTimeMillis() + ".jpg");
        to.getParentFile().mkdirs();

        try {
            copyDirectoryOneLocationToAnotherLocation(from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(from.exists())
            deleteFileFromMediaStore(getContentResolver(), from);

        editedImagePath = String.valueOf(to);
        setResult(RESULT_OK);
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }
}
