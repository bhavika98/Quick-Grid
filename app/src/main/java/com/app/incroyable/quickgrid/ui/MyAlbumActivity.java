package com.app.incroyable.quickgrid.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.MyAlbum;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.SaveImage;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAlbumActivity extends AppCompatActivity {

    Activity activity = MyAlbumActivity.this;

    GridView gridView;
    MyAlbumAdapter myAlbumAdapter;
    ArrayList<MyAlbum> myAlbumArrayList = new ArrayList<>();
    ArrayList<MyAlbum> arrayList = new ArrayList<>();
    ArrayList<MyAlbum> tempList = new ArrayList<>();
    String[] FilePathStrings;
    String Tilte;

    TextView no_data;
    File file;
    int totalFolder;
    ArrayList<String> imgPathList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_album);

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
        textView.setText(getResources().getString(R.string.MyAlbumActivity_Title));
    }

    private void fetchData() {
        String folderName = getString(R.string.app_name); // Replace with your folder name
        List<Uri> imageUris = getImagesFromFolder(getApplicationContext(), folderName);

        arrayList.clear();
        for (Uri imageUri : imageUris) {
            arrayList.add(new MyAlbum("x", SaveImage.getPathFromUri(getApplicationContext(), imageUri), 0, 0));
        }

//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);
//
//            alertDialogbuilder.setMessage(getResources().getString(R.string.sdcard_error));
//            alertDialogbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//
//            alertDialogbuilder.setCancelable(false);
//            alertDialogbuilder.show();
//        } else {
//            myAlbumArrayList.clear();
//            tempList.clear();
//            arrayList.clear();
//
//            file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + getResources().getString(R.string.app_name));
//            if (file.isDirectory()) {
//                File[] listFile = file.listFiles();
//                FilePathStrings = new String[listFile.length];
//                for (int i = 0; i < listFile.length; i++) {
//                    FilePathStrings[i] = listFile[i].getAbsolutePath();
//                    String name = FilePathStrings[i].substring(FilePathStrings[i].lastIndexOf("/") + 1, FilePathStrings[i].length());
//                    String[] str = name.split("_");
//                    myAlbumArrayList.add(new MyAlbum(str[0], FilePathStrings[i], Integer.parseInt(str[1]), 0));
//                }
//            }
//        }
//
//        totalFolder = Integer.parseInt(getResources().getString(R.string.total_folder));
//        for (int j = 1; j <= totalFolder; j++) {
//            if (myAlbumArrayList.size() != 0) {
//                for (int i = 0; i < myAlbumArrayList.size(); i++) {
//                    if (myAlbumArrayList.get(i).getId() == j) {
//                        tempList.add(new MyAlbum(myAlbumArrayList.get(i).getName(), myAlbumArrayList.get(i).getPath(), myAlbumArrayList.get(i).getId(), 0));
//                    }
//                }
//                if (tempList.size() != 0) {
//                    int size = tempList.size() - 1;
//                    arrayList.add(new MyAlbum(tempList.get(size).getName(), tempList.get(size).getPath(), tempList.get(size).getId(), tempList.size()));
//                    tempList.clear();
//                }
//            }
//        }

        myAlbumAdapter = new MyAlbumAdapter(activity, arrayList);
        gridView.setAdapter(myAlbumAdapter);
        gridView.setEmptyView(no_data);
    }

    public static List<Uri> getImagesFromFolder(Context context, String folderName) {
        List<Uri> imageUris = new ArrayList<>();

        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
        };

        // Define the selection criteria to get images from the specific folder
        String selection = MediaStore.Images.Media.RELATIVE_PATH + " LIKE ?";
        String[] selectionArgs = new String[] {
                Environment.DIRECTORY_PICTURES + "/" + folderName + "%"
        };

        Uri collectionUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = context.getContentResolver().query(
                collectionUri,
                projection,
                selection,
                selectionArgs,
                null
        );

        if (cursor != null) {
            try {
                int idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);

                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumnIndex);
                    Uri contentUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));
                    imageUris.add(contentUri);
                }
            } finally {
                cursor.close();
            }
        }

        return imageUris;
    }

    private void clickEvent() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                imgPathList.clear();
//
//                for (int i = 0; i < myAlbumArrayList.size(); i++) {
//                    if (arrayList.get(position).getId() == myAlbumArrayList.get(i).getId()) {
//                        imgPathList.add(myAlbumArrayList.get(i).getPath());
//                    }
//                    Tilte = myAlbumArrayList.get(i).getName();
//                }
//
//                Intent intent = new Intent(getApplicationContext(), MyPhotoActivity.class);
//                intent.putStringArrayListExtra(Constants.imgList, imgPathList);
//                intent.putExtra("Name", Tilte);
//                startActivity(intent);
//                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });
    }

    private void bindControls() {
        gridView = (GridView) findViewById(R.id.gridView);
        no_data = (TextView) findViewById(R.id.no_data);
    }

    public class MyAlbumAdapter extends BaseAdapter {

        ArrayList<MyAlbum> list;
        LayoutInflater mInflater;
        Activity activity;

        public MyAlbumAdapter(Activity activity, ArrayList<MyAlbum> list) {
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

        public class Holder {
            ImageView imageView, imgDelete;
//            TextView albumTitle, totalImage;
        }

        @Override
        public View getView(final int position, View view, final ViewGroup viewGroup) {

            view = mInflater.inflate(R.layout.adapter_myalbum, null, false);

            Holder holder = new Holder();

            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
//            holder.albumTitle = (TextView) view.findViewById(R.id.albumTitle);
//            holder.totalImage = (TextView) view.findViewById(R.id.totalImage);

            Glide.with(activity)
                    .load(list.get(position).getPath())
                    .placeholder(R.drawable.placeholder).into(holder.imageView);

//            holder.albumTitle.setText(list.get(position).getName());
//            holder.totalImage.setText("(" + String.valueOf(list.get(position).getTotal()) + " photos)");

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(activity);

                    alertDialogbuilder.setMessage("Are you sure want to delete this album?");
                    alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 0; i < myAlbumArrayList.size(); i++) {
                                if (myAlbumArrayList.get(i).getId() == list.get(position).getId()) {
                                    File file = new File(myAlbumArrayList.get(i).getPath());
                                    if (file.exists()) {
                                        deleteFileFromMediaStore(activity.getContentResolver(), file);
                                    }
                                }
                            }

                            list.remove(position);
                            notifyDataSetChanged();

                            if (list.size() == 0) {
                                no_data.setVisibility(View.VISIBLE);
                            }

                            Snackbar.make(findViewById(android.R.id.content), activity.getString(R.string.album_delete), Snackbar.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        fetchData();
        super.onResume();
    }
}
