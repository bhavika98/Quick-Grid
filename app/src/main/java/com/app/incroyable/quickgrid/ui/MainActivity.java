package com.app.incroyable.quickgrid.ui;

import static com.app.incroyable.quickgrid.util.Constants.currentActivity;
import static com.app.incroyable.quickgrid.util.DataBinder.fetchStickers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.StickerData;
import com.app.incroyable.quickgrid.ui.camera.CameraActivity;
import com.app.incroyable.quickgrid.ui.grid.ImageSelectionActivity;
import com.app.incroyable.quickgrid.ui.grid_3d.GridSelectionActivity;
import com.app.incroyable.quickgrid.util.Constants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Activity activity = MainActivity.this;

    public static ArrayList<StickerData> stickerArrayList = new ArrayList<>();
    public static int stickerValue = 0;

    TextView txtQuick, txtGrid;
    LinearLayout drawerLayout;
    Animation slide_up, slide_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.Status_Home));
        }

        txtQuick = (TextView) findViewById(R.id.txtQuick);
        txtGrid = (TextView) findViewById(R.id.txtGrid);
        drawerLayout = (LinearLayout) findViewById(R.id.drawerLayout);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtQuick.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed.ttf"));
        txtGrid.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf"));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                stickerArrayList = fetchStickers(activity);
            }
        });

        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (drawerLayout.getVisibility() == View.VISIBLE) {
                    drawerLayout.startAnimation(slide_down);
                    drawerLayout.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    public void menuRate(View view) {

    }

    public void menuMore(View view) {

    }

    public void menuPolicy(View view) {

    }

    public void menuUpdate(View view) {

    }

    public void menuShare(View view) {

    }

    public void imgMenu(View view) {
        if (drawerLayout.getVisibility() == View.GONE) {
            drawerLayout.startAnimation(slide_up);
            drawerLayout.setVisibility(View.VISIBLE);
        } else if (drawerLayout.getVisibility() == View.VISIBLE) {
            drawerLayout.startAnimation(slide_down);
            drawerLayout.setVisibility(View.GONE);
        }
    }

    public void imgMore(View view) {

    }

    public void grid3d(View view) {
        checkPermissions(0);
    }

    public void grid(View view) {
        checkPermissions(1);
    }

    public void album(View view) {
        checkPermissions(2);
    }

    public void singlepic(View view) {
        checkPermissions(3);
    }

    public void camera(View view) {
        checkPermissions(4);
    }

    public List<String> getStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return Arrays.asList(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
            );
        } else {
            return Arrays.asList(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            );
        }
    }

    private void checkPermissions(int index) {
        checkStoragePermission(this, new Runnable() {
            @Override
            public void run() {
                executeCode(index);
            }
        });
    }

    private void executeCode(int index) {
        switch (index) {
            case 0: {
                currentActivity = 1;
                startActivity(new Intent(activity, GridSelectionActivity.class));
                break;
            }

            case 1: {
                Intent intent = new Intent(activity, ImageSelectionActivity.class);
                intent.putExtra(Constants.imgId, "9");
                startActivity(intent);
                break;
            }

            case 2: {
                startActivity(new Intent(activity, MyAlbumActivity.class));
                break;
            }

            case 3: {
                Intent intent = new Intent(activity, com.app.incroyable.quickgrid.ui.grid_3d.ImageSelectionActivity.class);
                currentActivity = 2;
                intent.putExtra(Constants.imgId, "1");
                startActivity(intent);
                break;
            }

            case 4: {
                startActivity(new Intent(activity, CameraActivity.class));
                break;
            }
        }
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }

    private void checkStoragePermission(Context context, Runnable onPermissionGranted) {
        List<String> permissionsToRequest = new ArrayList<>();
        for (String permission : getStoragePermissions()) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (!permissionsToRequest.isEmpty()) {
            askPermission(context, Arrays.asList(permissionsToRequest.toArray(new String[0])), onPermissionGranted);
        } else {
            onPermissionGranted.run();
        }
    }

    public void askPermission(
            Context context,
            List<String> mainPermissions,
            Runnable onPermissionGranted
    ) {
        Dexter.withContext(context)
                .withPermissions(mainPermissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(@NonNull MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            onPermissionGranted.run();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionRationale(context, null, true);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> list, PermissionToken permissionToken) {
                        showPermissionRationale(context, permissionToken, false);
                    }

                })
                .onSameThread()
                .check();
    }

    private void showPermissionRationale(Context context, PermissionToken token, boolean isPermanentlyDenied) {
        // Implement the method to show rationale
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                return true;
            }

            default: {
                return false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.getVisibility() == View.VISIBLE) {
            drawerLayout.startAnimation(slide_down);
            drawerLayout.setVisibility(View.GONE);
        } else {
            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

            alertDialogbuilder.setMessage("Are you sure want to exit?");
            alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
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
    }
}
