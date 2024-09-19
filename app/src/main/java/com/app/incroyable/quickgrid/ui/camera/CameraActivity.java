package com.app.incroyable.quickgrid.ui.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Filter;
import com.app.incroyable.quickgrid.ui.ShareActivity;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.CountDownAnimation;
import com.app.incroyable.quickgrid.util.CustomAnimationDrawableNew;
import com.app.incroyable.quickgrid.util.GlowingText;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;

import static com.app.incroyable.quickgrid.util.Constants.normalColor;
import static com.app.incroyable.quickgrid.util.Constants.selectedColor;
import static com.app.incroyable.quickgrid.util.DataBinder.applyFilter;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchFilters;

public class CameraActivity extends AppCompatActivity implements CountDownAnimation.CountDownListener, View.OnTouchListener{

    private GPUImage mGPUImage;
    private CameraHelper mCameraHelper;
    private CameraLoader mCamera;
    Camera.Parameters parameters = null;
    Camera.Size adapterSize = null;
    static final double MAX_ASPECT_DISTORTION = 0.15;
    float pointX, pointY, dist;
    static final int FOCUS = 1;
    static final int ZOOM = 2;
    Handler handler = new Handler();
    int mode, curZoomValue = 0;

    ImageView starLayout;

    boolean canSwitch = false;
    OrientationEventListener myOrientationEventListener;
    int degreeImage = 90;
    Animation slide_up, slide_down;
    int checkOrientation = 0, countdown = 0;
    CountDownAnimation countDownAnimation;
    Bitmap result;

    AVLoadingIndicatorView loadingIndicatorView;
    GLSurfaceView surfaceView;
    ImageView flashBtn, flipBtn, countdownBtn, next, back, cancelSave, saveImage;
    ImageView surfaceImage;
    View focusIndex;
    Button takePicture;
    RelativeLayout panel_take_photo, panel_filter, bottomLayout, topLayout, captureLayout;
    TextView textView;
    float startGlowRadius = 35f, minGlowRadius = 3f, maxGlowRadius = 15f;
    GlowingText glowText;

    TextView textViewFilterTitle;
    RecyclerView recyclerViewFilter;
    LinearLayoutManager linearLayoutManager;
    FilterAdapter filterAdapter;
    ArrayList<Filter> cameraFiltersArrayList = new ArrayList<>();
    String selection = "0", isRotate = "", folder = "", finalPath = "", filename = "";
    Bitmap savedBitmap;

    public static int isSaved = 0;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    GestureDetector gestureDetector;

    RelativeLayout layZoom;
    TextView txtZoomLevel;
    ImageView imgZoomLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        bindControls();
        initView();
        clickEvents();
        bindFilter();
    }

    public void startposAnimate(int dir) {
        flashBtn.setRotation(dir);
        flipBtn.setRotation(dir);
        back.setRotation(dir);
        next.setRotation(dir);
        countdownBtn.setRotation(dir);
        textView.setRotation(dir);
    }

    private void bindFilter() {

        cameraFiltersArrayList.clear();
        cameraFiltersArrayList = fetchFilters();
        filterAdapter = new FilterAdapter(getApplicationContext(), cameraFiltersArrayList);
        recyclerViewFilter.setAdapter(filterAdapter);
    }

    private void clickEvents() {

        countdownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countdown == 0) {
                    countdown = 3;
                    countdownBtn.setImageResource(R.drawable.countdown_selected_3);
                } else if (countdown == 3) {
                    countdown = 5;
                    countdownBtn.setImageResource(R.drawable.countdown_selected_5);
                } else if (countdown == 5) {
                    countdown = 10;
                    countdownBtn.setImageResource(R.drawable.countdown_selected_10);
                } else if (countdown == 10) {
                    countdown = 0;
                    countdownBtn.setImageResource(R.drawable.cam_countdown_selector);
                }
            }
        });

        cancelSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCamera.mCameraInstance.startPreview();
                topLayout.setVisibility(View.VISIBLE);
                bottomLayout.setVisibility(View.GONE);
            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panel_filter.getVisibility() == View.GONE) {
                    panel_filter.startAnimation(slide_up);
                    panel_filter.setVisibility(View.VISIBLE);
                    next.setColorFilter(ContextCompat.getColor(getApplicationContext(), selectedColor));
                } else {
                    panel_filter.startAnimation(slide_down);
                    panel_filter.setVisibility(View.GONE);
                    next.setColorFilter(ContextCompat.getColor(getApplicationContext(), normalColor));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });

        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnLight(mCamera.mCameraInstance);
            }
        });

        try {
            canSwitch = mCameraHelper.hasFrontCamera() && mCameraHelper.hasBackCamera();
        } catch (Exception e) {
        }

        if (!canSwitch) {
            flipBtn.setVisibility(View.GONE);
        } else {
            flipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCamera.switchCamera();
                }
            });
        }

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSaved = 0;
                if (countdown == 0) {
                    setCam();
                } else {
                    Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                    AnimationSet animationSet = new AnimationSet(false);
                    animationSet.addAnimation(scaleAnimation);
                    animationSet.addAnimation(alphaAnimation);
                    countDownAnimation.setAnimation(animationSet);
                    countDownAnimation.setStartCount(countdown);
                    countDownAnimation.start();
                }
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                pointX = e.getX();
                pointY = e.getY();
                hideFilterPanel();
                setFocus();
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                hideFilterPanel();
                                leftToRight();
                            } else {
                                hideFilterPanel();
                                rightToLeft();
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                //onSwipeBottom();
                            } else {
                                //onSwipeTop();
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        });
        surfaceView.setOnTouchListener(this);
    }

    private void hideFilterPanel()
    {
        if (panel_filter.getVisibility() == View.VISIBLE) {
            panel_filter.startAnimation(slide_down);
            panel_filter.setVisibility(View.GONE);
            next.setColorFilter(ContextCompat.getColor(getApplicationContext(), normalColor));
        }
    }

    private void setFocus() {

        try {
            pointFocus((int) pointX, (int) pointY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(focusIndex.getLayoutParams());
        layout.setMargins((int) pointX - 50, (int) pointY - 50, 0, 0);
        focusIndex.setLayoutParams(layout);
        focusIndex.setVisibility(View.VISIBLE);
        ScaleAnimation sa = new ScaleAnimation(3f, 1f, 3f, 1f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(800);
        focusIndex.startAnimation(sa);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                focusIndex.setVisibility(View.INVISIBLE);
            }
        }, 800);
    }

    private void leftToRight() {

        int position = Integer.parseInt(selection);
        if (position > 0) {
            position--;
            animateText(position);
        }
    }

    private void rightToLeft() {

        int position = Integer.parseInt(selection);
        if (position < (cameraFiltersArrayList.size() - 1)) {
            position++;
            animateText(position);
        }
    }

    public void animateText(int position)
    {
        mGPUImage.setFilter(applyFilter(position, CameraActivity.this));
        selection = String.valueOf(position);
        filterAdapter.notifyDataSetChanged();
        textViewFilterTitle.setText(cameraFiltersArrayList.get(position).getTitle());
        textViewFilterTitle.setVisibility(View.VISIBLE);
        glowText = new GlowingText(
                CameraActivity.this,
                getBaseContext(),
                textViewFilterTitle,
                minGlowRadius,
                maxGlowRadius,
                startGlowRadius,
                Color.WHITE,
                5);
        textViewFilterTitle.setAlpha(1.0f);
        textViewFilterTitle.animate()
                .alpha(0.0f)
                .setDuration(800)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        textViewFilterTitle.setVisibility(View.GONE);
                    }
                });
    }

    private float spacing(MotionEvent event) {
        if (event == null) {
            return 0;
        }
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void addZoomIn(int delta) {

        try {
            Camera.Parameters params = mCamera.mCameraInstance.getParameters();
            Log.e("Camera", "Is support Zoom " + params.isZoomSupported());
            if (!params.isZoomSupported()) {
                return;
            }
            curZoomValue += delta;
            if (curZoomValue < 0) {
                curZoomValue = 0;
            } else if (curZoomValue > params.getMaxZoom()) {
                curZoomValue = params.getMaxZoom();
            }

            if (!params.isSmoothZoomSupported()) {
                params.setZoom(curZoomValue);
                mCamera.mCameraInstance.setParameters(params);
                return;
            } else {
                mCamera.mCameraInstance.startSmoothZoom(curZoomValue);
            }
            int currentZoom;

            if(params.getZoom() == 0)
                currentZoom = 1;
            else
                currentZoom = params.getZoom();

            txtZoomLevel.setText(String.valueOf(currentZoom)+".0x");

            layZoom.setVisibility(View.VISIBLE);
            layZoom.postDelayed(new Runnable() {
                public void run() {
                    layZoom.setVisibility(View.GONE);
                }
            }, 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pointFocus(int x, int y) {
        mCamera.mCameraInstance.cancelAutoFocus();
        parameters = mCamera.mCameraInstance.getParameters();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            showPoint(x, y);
        }
        mCamera.mCameraInstance.setParameters(parameters);
        autoFocus();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void showPoint(int x, int y) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> areas = new ArrayList<Camera.Area>();

            int rectY = -x * 200 / App.getApp().getScreenWidth() + 100;
            int rectX = y * 200 / App.getApp().getScreenHeight() - 100;

            int left = rectX < -90 ? -100 : rectX - 10;
            int top = rectY < -90 ? -100 : rectY - 10;
            int right = rectX > 90 ? 100 : rectX + 10;
            int bottom = rectY > 90 ? 100 : rectY + 10;

            Rect area1 = new Rect(left, top, right, bottom);
            areas.add(new Camera.Area(area1, 80));
            parameters.setMeteringAreas(areas);
        }

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
    }

    private void autoFocus() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mCamera.mCameraInstance == null) {
                    return;
                }
                mCamera.mCameraInstance.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            mCamera.init();
                        }
                    }
                });
            }
        };
    }

    private void bindControls() {

        mGPUImage = new GPUImage(this);
        mGPUImage.setGLSurfaceView((GLSurfaceView) findViewById(R.id.surfaceView));
        surfaceView = (GLSurfaceView) findViewById(R.id.surfaceView);

        layZoom = (RelativeLayout) findViewById(R.id.layZoom);
        txtZoomLevel = (TextView) findViewById(R.id.txtZoomLevel);
        imgZoomLevel = (ImageView) findViewById(R.id.imgZoomLevel);

        textViewFilterTitle = (TextView) findViewById(R.id.textViewFilterTitle);
        starLayout = (ImageView) findViewById(R.id.starLayout);
        loadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.loadingIndicatorView);
        flashBtn = (ImageView) findViewById(R.id.flashBtn);
        flipBtn = (ImageView) findViewById(R.id.flipBtn);
        focusIndex = (View) findViewById(R.id.focusIndex);
        takePicture = (Button) findViewById(R.id.takePicture);
        next = (ImageView) findViewById(R.id.next);
        back = (ImageView) findViewById(R.id.back);
        panel_take_photo = (RelativeLayout) findViewById(R.id.panel_take_photo);
        panel_filter = (RelativeLayout) findViewById(R.id.panel_filter);
        topLayout = (RelativeLayout) findViewById(R.id.topLayout);
        bottomLayout = (RelativeLayout) findViewById(R.id.bottomLayout);
        countdownBtn = (ImageView) findViewById(R.id.countdownBtn);
        textView = (TextView) findViewById(R.id.textView);

        recyclerViewFilter = (RecyclerView) findViewById(R.id.recyclerViewFilter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFilter.setLayoutManager(linearLayoutManager);

        captureLayout = (RelativeLayout) findViewById(R.id.captureLayout);
        cancelSave = (ImageView) findViewById(R.id.cancelSave);
        surfaceImage = (ImageView) findViewById(R.id.surfaceImage);
        saveImage = (ImageView) findViewById(R.id.saveImage);
    }

    private void initView() {
        mCameraHelper = new CameraHelper(this);
        mCamera = new CameraLoader();

        countDownAnimation = new CountDownAnimation(textView, 0, CameraActivity.this);
        countDownAnimation.setCountDownListener(this);
        glowText = new GlowingText(
                CameraActivity.this,
                getBaseContext(),
                textView,
                minGlowRadius,
                maxGlowRadius,
                startGlowRadius,
                Color.WHITE,
                5);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {

            final int ROTATION_O = 1;
            final int ROTATION_90 = 2;
            final int ROTATION_180 = 3;
            final int ROTATION_270 = 4;

            private int rotation = 0;
            int diraction, check_diraction = 0;

            @Override
            public void onOrientationChanged(int orientation) {
                if ((orientation < 35 || orientation > 325) && rotation != ROTATION_O) { // PORTRAIT
                    rotation = ROTATION_O;
                    degreeImage = 90;
                    diraction = 0;
                    checkOrientation = 0;

                    isRotate = "0";
                    filterAdapter.notifyDataSetChanged();

                    startposAnimate(0);
//                    if (check_diraction == 2) {
//                        startposAnimate();
//                        check_diraction = diraction;
//                    } else if (check_diraction == 3) {
//                        startnegAnimate();
//                        check_diraction = diraction;
//                    }
                } else if (orientation > 145 && orientation < 215 && rotation != ROTATION_180) { // REVERSE PORTRAIT
                    rotation = ROTATION_180;
                    degreeImage = 270;
                    diraction = 1;
                    checkOrientation = 0;

                    isRotate = "180";
                    filterAdapter.notifyDataSetChanged();

                    startposAnimate(180);
//                    if (check_diraction == 3) {
//                        startposAnimate();
//                        check_diraction = diraction;
//                    } else if (check_diraction == 2) {
//                        startnegAnimate();
//                        check_diraction = diraction;
//                    }
                } else if (orientation > 55 && orientation < 125 && rotation != ROTATION_270) { // REVERSE LANDSCAPE
                    rotation = ROTATION_270;
                    degreeImage = 180;
                    diraction = 2;
                    checkOrientation = 1;
                    isRotate = "270";
                    filterAdapter.notifyDataSetChanged();

                    startposAnimate(270);
//                    if (check_diraction == 1) {
//                        startposAnimate();
//                        check_diraction = diraction;
//                    } else if (check_diraction == 0) {
//                        startnegAnimate();
//                        check_diraction = diraction;
//                    }
                } else if (orientation > 235 && orientation < 305 && rotation != ROTATION_90) { //LANDSCAPE
                    rotation = ROTATION_90;
                    degreeImage = 0;
                    diraction = 3;
                    checkOrientation = 1;
                    isRotate = "90";
                    filterAdapter.notifyDataSetChanged();

                    startposAnimate(90);
//                    if (check_diraction == 0) {
//                        startposAnimate();
//                        check_diraction = diraction;
//                    } else if (check_diraction == 1) {
//                        startnegAnimate();
//                        check_diraction = diraction;
//                    }
                }
            }
        };

        if (myOrientationEventListener.canDetectOrientation()) {
            myOrientationEventListener.enable();
        }
    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {
        setCam();
    }

    private void takePicture() {

        Camera.Parameters params = mCamera.mCameraInstance.getParameters();
        //params.setPictureSize(1280, 960);
        params.setRotation(degreeImage);
        mCamera.mCameraInstance.setParameters(params);
        for (Camera.Size size : params.getSupportedPictureSizes()) {
            //Log.e("ASDF", "Supported: " + size.width + "x" + size.height);
        }

        Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
            @Override
            public void onShutter() {
                //MediaPlayer.create(CameraActivity.this, R.raw.tone_cuver_sample).start();
            }
        };

        mCamera.mCameraInstance.takePicture(shutterCallback, null,
                new Camera.PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] data, final Camera camera) {
                        File pictureFile = getOutputMediaFile();

                        try {
                            FileOutputStream fos = new FileOutputStream(pictureFile);
                            fos.write(data);
                            fos.close();
                        } catch (Exception e) {
                            //Log.d("ASDF", "File not found: " + e.getMessage());
                        }

                        data = null;
                        result = BitmapFactory.decodeFile(pictureFile.getAbsolutePath());
                        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

                        File file = null;
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            File directory = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().toString())).append(File.separator).append(getResources().getString(R.string.app_name)).toString());
                            if (!directory.exists())
                                directory.mkdirs();

                            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                            folder = directory.getAbsolutePath();
                            filename = new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date()) + ".jpg";

                            file = new File(path, folder + "/" + filename);
                            finalPath = String.valueOf(file);
                        }

                        mGPUImage.saveToPictures(result, folder, filename, new GPUImage.OnPictureSavedListener() {

                            @Override
                            public void onPictureSaved(Uri uri) {

                                File file = new File(getBaseContext().getCacheDir() + "/" + "temp.jpg");
                                if (file.exists())
                                    deleteFileFromMediaStore(getContentResolver(), file);

                                ImageLoader.getInstance().loadImage(String.valueOf(uri), new ImageLoadingListener() {
                                    @Override
                                    public void onLoadingStarted(String imageUri, View view) {
                                    }

                                    @Override
                                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                    }

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                                        surfaceImage.setImageBitmap(loadedImage);
                                        savedBitmap = loadedImage;

                                        loadingIndicatorView.setVisibility(View.GONE);
                                        loadingIndicatorView.hide();

                                        File temp = new File(finalPath);
                                        if (temp.exists())
                                            deleteFileFromMediaStore(getContentResolver(), temp);

                                        loadBrightness();
                                        camera.startPreview();
                                        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
                                    }

                                    @Override
                                    public void onLoadingCancelled(String imageUri, View view) {

                                    }
                                });
                            }
                        });

                        topLayout.setVisibility(View.GONE);
                        bottomLayout.setVisibility(View.VISIBLE);
                        surfaceImage.setImageBitmap(null);
                        loadingIndicatorView.show();
                        loadingIndicatorView.setVisibility(View.VISIBLE);

                        int height = 0;
                        if (checkOrientation == 0) {
                            height = ViewGroup.LayoutParams.MATCH_PARENT;
                        } else if (checkOrientation == 1) {
                            height = 600;
                        }
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                        captureLayout.setLayoutParams(layoutParams);
                        captureLayout.setGravity(Gravity.CENTER);

                    }
                });
    }

    private void loadBrightness() {

        final MediaPlayer mediaPlayer = MediaPlayer.create(CameraActivity.this, R.raw.bright);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                starLayout.setVisibility(View.VISIBLE);
                CustomAnimationDrawableNew cad = new CustomAnimationDrawableNew((AnimationDrawable) getResources().getDrawable(R.drawable.camera_picture_beauty)) {

                    @Override
                    protected void onAnimationFinish() {

//                        Filter filter = com.app.editor.filter.SampleFilters.getStarLitFilter();
//                        savedBitmap = filter.processFilter(CompressImage.originalBitmap(savedBitmap));
//                        surfaceImage.setImageBitmap(savedBitmap);

                        savedBitmap = changeBitmapContrastBrightness(savedBitmap, 1.2f, 1.1f);
                        surfaceImage.setImageBitmap(savedBitmap);

                        starLayout.setVisibility(View.GONE);
                        if (mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                    }
                };
                starLayout.setBackgroundDrawable(cad);
                cad.start();
                mediaPlayer.start();
            }
        };
        handler.postDelayed(runnable, 1100);
    }

    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast, float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return ret;
    }

    public void deleteFileFromMediaStore(final ContentResolver contentResolver, final File file) {
        String canonicalPath;
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            canonicalPath = file.getAbsolutePath();
        }
        final Uri uri = MediaStore.Files.getContentUri("external");
        final int result = contentResolver.delete(uri,
                MediaStore.Files.FileColumns.DATA + "=?", new String[]{canonicalPath});
        if (result == 0) {
            final String absolutePath = file.getAbsolutePath();
            if (!absolutePath.equals(canonicalPath)) {
                contentResolver.delete(uri,
                        MediaStore.Files.FileColumns.DATA + "=?", new String[]{absolutePath});
            }
        }
    }

    public void setCam() {
        if (mCamera.mCameraInstance.getParameters().getFocusMode().equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            takePicture();
        } else {
            mCamera.mCameraInstance.autoFocus(new Camera.AutoFocusCallback() {

                @Override
                public void onAutoFocus(final boolean success, final Camera camera) {
                    takePicture();
                }
            });
        }
    }

    private File getOutputMediaFile() {

        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File directory = getBaseContext().getCacheDir();
            if (!directory.exists())
                directory.mkdirs();

            file = new File(directory.getPath() + "/" + "temp.jpg");
        }
        return file;
    }

    private void turnLight(Camera mCamera) {
        if (mCamera == null || mCamera.getParameters() == null
                || mCamera.getParameters().getSupportedFlashModes() == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        String flashMode = mCamera.getParameters().getFlashMode();
        List<String> supportedModes = mCamera.getParameters().getSupportedFlashModes();
        if (Camera.Parameters.FLASH_MODE_OFF.equals(flashMode) && supportedModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            mCamera.setParameters(parameters);
            flashBtn.setImageResource(R.drawable.cam_flash_on_selector);
        } else if (Camera.Parameters.FLASH_MODE_ON.equals(flashMode)) {
            if (supportedModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                flashBtn.setImageResource(R.drawable.cam_flash_auto_selector);
                mCamera.setParameters(parameters);
            } else if (supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                flashBtn.setImageResource(R.drawable.cam_flash_off_selector);
                mCamera.setParameters(parameters);
            }
        } else if (Camera.Parameters.FLASH_MODE_AUTO.equals(flashMode) && supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            flashBtn.setImageResource(R.drawable.cam_flash_off_selector);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        pointX = event.getX();
                        pointY = event.getY();
                        mode = FOCUS;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        dist = spacing(event);
                        if (spacing(event) > 10f) {
                            mode = ZOOM;
                        }
                        break;

                    case MotionEvent.ACTION_UP:

                    case MotionEvent.ACTION_POINTER_UP:
                        mode = FOCUS;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mode == FOCUS) {
                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            if (newDist > 10f) {
                                float tScale = (newDist - dist) / dist;
                                if (tScale < 0) {
                                    tScale = tScale * 10;
                                }
                                addZoomIn((int) tScale);
                            }
                        }
                        break;
                }
        return gestureDetector.onTouchEvent(event);
    }

    public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

        ArrayList<Filter> list;
        Context context;

        public FilterAdapter(Context context, ArrayList<Filter> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.adapter_camera_filter, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.thumbnail.setImageResource(list.get(position).getThumb());
            holder.txtTitle.setText(list.get(position).getTitle());

            if (!isRotate.equals("")) {
                holder.mainView.setRotation(Float.parseFloat(isRotate));
            }

            if (selection.equals("")) {
                holder.border.setVisibility(View.INVISIBLE);
            } else {
                if (position == Integer.parseInt(selection))
                    holder.border.setVisibility(View.VISIBLE);
                else
                    holder.border.setVisibility(View.INVISIBLE);
            }

            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selection = String.valueOf(position);
                    notifyDataSetChanged();

                    mGPUImage.setFilter(applyFilter(position, CameraActivity.this));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView thumbnail;
            RelativeLayout border;
            LinearLayout mainView;
            TextView txtTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
                border = (RelativeLayout) itemView.findViewById(R.id.border);
                txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
                mainView = (LinearLayout) itemView.findViewById(R.id.mainView);
            }
        }
    }

    public void takeScreenshot() {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File directory = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().toString())).append(File.separator).append(getResources().getString(R.string.app_name)).toString());
            if (!directory.exists())
                directory.mkdirs();

            String path = directory.getAbsolutePath();
            file = new File(path + "/" + getResources().getString(R.string.save_image_name_3) + "_" + new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date()) + ".jpg");

            finalPath = String.valueOf(file);

//            AndroidBmpUtil bmpUtil = new AndroidBmpUtil();
//            bmpUtil.save(savedBitmap, finalPath);
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            savedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

        Toast.makeText(CameraActivity.this, getString(R.string.img_save), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CameraActivity.this, ShareActivity.class);
        intent.putExtra(Constants.imgSharePath, finalPath);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

    }

    @Override
    protected void onDestroy() {
        myOrientationEventListener.disable();
        glowText.stopGlowing();
        super.onDestroy();
    }

    private class CameraLoader {

        private int mCurrentCameraId = 0;
        private Camera mCameraInstance;

        public void onResume() {
            setUpCamera(mCurrentCameraId);
        }

        public void onPause() {
            releaseCamera();
        }

        public void switchCamera() {
            releaseCamera();
            mCurrentCameraId = (mCurrentCameraId + 1) % mCameraHelper.getNumberOfCameras();
            setUpCamera(mCurrentCameraId);
            if (mCurrentCameraId == 0) {
                flashBtn.setEnabled(true);
                flashBtn.setImageResource(R.drawable.cam_flash_off_selector);
                Camera.Parameters parameters = mCamera.mCameraInstance.getParameters();
                List<String> supportedModes = mCamera.mCameraInstance.getParameters().getSupportedFlashModes();
                if (supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.mCameraInstance.setParameters(parameters);
                }
            } else {
                flashBtn.setEnabled(false);
                flashBtn.setImageResource(R.drawable.cam_flash_disable);
            }
        }

        public void init() {
            parameters = mCamera.mCameraInstance.getParameters();
            parameters.setPictureFormat(PixelFormat.JPEG);

            setUpPicSize(parameters);

            if (adapterSize != null) {
                parameters.setPictureSize(adapterSize.width, adapterSize.height);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            setDispaly(parameters, mCamera.mCameraInstance);
            try {
                mCamera.mCameraInstance.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mCamera.mCameraInstance.startPreview();
            mCamera.mCameraInstance.cancelAutoFocus();
        }

        private void setUpCamera(final int id) {

            mCameraInstance = getCameraInstance(id);
            parameters = mCameraInstance.getParameters();

            parameters.setPictureFormat(PixelFormat.JPEG);

            setUpPicSize(parameters);

            if (adapterSize != null) {
                parameters.setPictureSize(adapterSize.width, adapterSize.height);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            setDispaly(parameters, mCamera.mCameraInstance);

            try {
                mCameraInstance.setParameters(parameters);
            } catch (Exception e) {
                Log.e("exception", e.getMessage());
            }

            int orientation = mCameraHelper.getCameraDisplayOrientation(CameraActivity.this, mCurrentCameraId);
            CameraHelper.CameraInfo2 cameraInfo = new CameraHelper.CameraInfo2();
            mCameraHelper.getCameraInfo(mCurrentCameraId, cameraInfo);
            boolean flipHorizontal = cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT;
            mGPUImage.setUpCamera(mCameraInstance, orientation, flipHorizontal, false);
        }

        /**
         * A safe way to get an instance of the Camera object.
         */
        private Camera getCameraInstance(final int id) {
            Camera c = null;
            try {
                c = mCameraHelper.openCamera(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }

        private void releaseCamera() {
            mCameraInstance.setPreviewCallback(null);
            mCameraInstance.release();
            mCameraInstance = null;
            adapterSize = null;
        }
    }

    private void setDispaly(Camera.Parameters parameters, Camera camera) {
        if (Build.VERSION.SDK_INT >= 8) {
            setDisplayOrientation(camera, 90);
        } else {
            parameters.setRotation(90);
        }
    }

    private void setDisplayOrientation(Camera camera, int angle) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[]{int.class});
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, new Object[]{angle});
            }
        } catch (Exception e) {
            Log.e("e", "" + e.getMessage());
        }
    }

    private void setUpPicSize(Camera.Parameters parameters) {

        if (adapterSize != null) {
            return;
        } else {
            adapterSize = findBestPictureResolution();
            return;
        }
    }

    private Camera.Size findBestPictureResolution() {
        Camera.Parameters cameraParameters = mCamera.mCameraInstance.getParameters();
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes();

        StringBuilder picResolutionSb = new StringBuilder();
        for (Camera.Size supportedPicResolution : supportedPicResolutions) {
            picResolutionSb.append(supportedPicResolution.width).append('x')
                    .append(supportedPicResolution.height).append(" ");
        }

        Camera.Size defaultPictureResolution = cameraParameters.getPictureSize();

        List<Camera.Size> sortedSupportedPicResolutions = new ArrayList<Camera.Size>(
                supportedPicResolutions);
        Collections.sort(sortedSupportedPicResolutions, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size a, Camera.Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });

        double screenAspectRatio = (double) App.getApp().getScreenWidth()
                / (double) App.getApp().getScreenHeight();
        Iterator<Camera.Size> it = sortedSupportedPicResolutions.iterator();
        while (it.hasNext()) {
            Camera.Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > MAX_ASPECT_DISTORTION) {
                it.remove();
                continue;
            }
        }

        if (!sortedSupportedPicResolutions.isEmpty()) {
            return sortedSupportedPicResolutions.get(0);
        }
        return defaultPictureResolution;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCamera.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        mCamera.onPause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (bottomLayout.getVisibility() == View.VISIBLE) {
            topLayout.setVisibility(View.VISIBLE);
            bottomLayout.setVisibility(View.GONE);
            //mCamera.mCameraInstance.startPreview();
        } else {
            if (panel_filter.getVisibility() == View.VISIBLE) {
                panel_filter.startAnimation(slide_down);
                panel_filter.setVisibility(View.GONE);
                next.setColorFilter(ContextCompat.getColor(getApplicationContext(), normalColor));
            } else {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        }
    }
}
