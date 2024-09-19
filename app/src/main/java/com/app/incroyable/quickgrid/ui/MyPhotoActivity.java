package com.app.incroyable.quickgrid.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.util.Constants;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyPhotoActivity extends AppCompatActivity {

    Activity activity = MyPhotoActivity.this;

    TextView no_data;
    GridView gridView;
    MyPhotosAdapter myPhotosAdapter;
    public static ArrayList<String> stringArrayList = new ArrayList<>();
    public static int currentposition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_photo);

        bindToolbar();
        bindControls();
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        String title = getIntent().getExtras().getString("Name");
        textView.setText(title);
    }

    private void bindControls() {

        gridView = (GridView) findViewById(R.id.gridView);
        no_data = (TextView) findViewById(R.id.no_data);

        stringArrayList = getIntent().getExtras().getStringArrayList(Constants.imgList);

        myPhotosAdapter = new MyPhotosAdapter(activity, stringArrayList);
        gridView.setAdapter(myPhotosAdapter);
        gridView.setEmptyView(no_data);
    }

    public class MyPhotosAdapter extends BaseAdapter {

        ArrayList<String> list;
        LayoutInflater mInflater;
        Activity activity;

        public MyPhotosAdapter(Activity activity, ArrayList<String> list) {
            this.activity = activity;
            this.list = list;
            mInflater = LayoutInflater.from(activity);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            ImageView imageView, imgDelete, imgShare;
        }

        @Override
        public View getView(final int position, View view, final ViewGroup viewGroup) {

            view = mInflater.inflate(R.layout.adapter_myphoto, null, false);

            Holder holder = new Holder();

            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.imgShare = (ImageView) view.findViewById(R.id.imgShare);

            Glide.with(activity)
                    .load(list.get(position))
                    .placeholder(R.drawable.placeholder).into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(activity, MyPhotoGalleryActivity.class);
                    currentposition = position;
                    activity.startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                }
            });

            holder.imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(""));
                    String shareBody = getResources().getString(R.string.share_msg);
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(list.get(position))));
                    activity.startActivity(intent);
                }
            });

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

                    alertDialogbuilder.setMessage("Are you sure want to delete this photo?");
                    alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            File file = new File(list.get(position));
                            if(file.exists())
                            {
                                deleteFileFromMediaStore(activity.getContentResolver() ,file);
                            }

                            list.remove(position);
                            notifyDataSetChanged();

                            if(list.size() == 0)
                            {
                                no_data.setVisibility(View.VISIBLE);
                            }

                            Snackbar.make(findViewById(android.R.id.content), activity.getString(R.string.photo_delete), Snackbar.LENGTH_SHORT).show();
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
            });

            return view;
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
    }

    @Override
    protected void onResume() {
        //clickEvent();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }

            default: {
                return false;
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
