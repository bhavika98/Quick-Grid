package com.app.incroyable.quickgrid.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.mylib.scrollgalleryview.MediaInfo;
import com.app.mylib.scrollgalleryview.MediaLoader;
import com.app.mylib.scrollgalleryview.ScrollGalleryView;
import com.app.incroyable.quickgrid.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.app.incroyable.quickgrid.util.CompressImage.getCompressedBitmap;

public class MyPhotoGalleryActivity extends AppCompatActivity {

    Activity activity = MyPhotoGalleryActivity.this;

    ArrayList<String> images = new ArrayList<>();
    ScrollGalleryView scrollGalleryView;
    List<MediaInfo> infos;
    LinearLayout content;
    TextView no_data;
    int currentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_photo_gallery);

        bindToolbar();
        bindControls();
        clickEvent();
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.MyPhotoGalleryActivity_Title));
    }

    private void clickEvent() {

        infos = new ArrayList<>(images.size());
        for (final String url : images)
        {
            infos.add(MediaInfo.mediaLoader(new MediaLoader() {
                @Override public boolean isImage() {
                    return true;
                }

                @Override public void loadMedia(Context context, ImageView imageView, SuccessCallback callback) {
                    imageView.setImageBitmap(getCompressedBitmap(url));
                    callback.onSuccess();
                }

                @Override public void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback) {
                    thumbnailView.setImageBitmap(getCompressedBitmap(url));
                    callback.onSuccess();
                }
            }));

        }

        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(infos);

        scrollGalleryView.setCurrentItem(MyPhotoActivity.currentposition);
    }

    private void bindControls() {

        images = MyPhotoActivity.stringArrayList;

        no_data = (TextView) findViewById(R.id.no_data);
        content = (LinearLayout) findViewById(R.id.content);
        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);

        if(images.size() == 0)
        {
            no_data.setVisibility(View.VISIBLE);
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (itemId == R.id.menu_share) {
            currentPosition = scrollGalleryView.getCurrentItem();

            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareBody = getResources().getString(R.string.share_msg);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(images.get(currentPosition))));
            startActivity(intent);

            return true;
        } else if (itemId == R.id.menu_delete) {
            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

            alertDialogbuilder.setMessage("Are you sure want to delete this image?");
            alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    currentPosition = scrollGalleryView.getCurrentItem();

                    File file = new File(images.get(currentPosition));
                    if (file.exists()) {
                        deleteFileFromMediaStore(getContentResolver(), file);
                    }

                    images.remove(currentPosition);
                    infos.remove(currentPosition);

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
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

            return true;
        } else {
            return false;
        }
    }

    public  void deleteFileFromMediaStore(final ContentResolver contentResolver, final File file) {
        String canonicalPath;
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            canonicalPath = file.getAbsolutePath();
        }
        final Uri uri = MediaStore.Files.getContentUri("external");
        final int result = contentResolver.delete(uri,
                MediaStore.Files.FileColumns.DATA + "=?", new String[] {canonicalPath});
        if (result == 0) {
            final String absolutePath = file.getAbsolutePath();
            if (!absolutePath.equals(canonicalPath)) {
                contentResolver.delete(uri,
                        MediaStore.Files.FileColumns.DATA + "=?", new String[]{absolutePath});
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        super.onBackPressed();
    }
}
