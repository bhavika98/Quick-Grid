package com.app.incroyable.quickgrid.ui.grid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Album;
import com.app.incroyable.quickgrid.model.Image;
import com.app.incroyable.quickgrid.util.Constants;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import static com.app.incroyable.quickgrid.util.Constants.imgId;

public class ImageSelectionActivity extends AppCompatActivity {

    Activity activity = ImageSelectionActivity.this;
    TextView textView;

    TextView txtTotalImg;
    int totalCount, currentCount = 0;

    GridView gridView;
    ImageSelectAdapter imageSelectAdapter;

    ListView listView;
    AlbumSelectAdapter albumSelectAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SelectedImageAdapter selectedImageAdapter;

    ArrayList<Image> imageArrayList = new ArrayList<>();
    ArrayList<Album> albumArrayList = new ArrayList<>();
    ArrayList<String> selectedImageList = new ArrayList<>();

    private final String[] projection = new String[]{
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATA };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);

        bindToolbar();
        bindControls();
        fetchAlbumData();
        clickEvent();
    }

    private void bindToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn_selector);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(getResources().getString(R.string.ImageSelectionActivity_Title));
    }

    private void fetchAlbumData() {

        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

        Cursor cursor = activity.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Media.DATE_ADDED);

        if(cursor == null)
        {
            AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(ImageSelectionActivity.this);

            alertDialogbuilder.setMessage(getResources().getString(R.string.sdcard_error));
            alertDialogbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertDialogbuilder.setCancelable(false);
            alertDialogbuilder.show();
        }
        else
        {
            ArrayList<Album> temp = new ArrayList<>(cursor.getCount());
            HashSet<Long> albumSet = new HashSet<>();
            File file;

            if (cursor.moveToLast()) {
                do {
                    if (Thread.interrupted()) {
                        return;
                    }

                    long albumId = cursor.getLong(cursor.getColumnIndex(projection[0]));
                    String album = cursor.getString(cursor.getColumnIndex(projection[1]));
                    String image = cursor.getString(cursor.getColumnIndex(projection[2]));

                    if (!albumSet.contains(albumId))
                    {
                        file = new File(image);
                        if (file.exists())
                        {
                            Cursor tempCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " =?", new String[]{ album }, MediaStore.Images.Media.DATE_ADDED);

                            temp.add(new Album(album, image, tempCursor.getCount()+""));
                            albumSet.add(albumId);
                            tempCursor.close();
                        }
                    }

                } while (cursor.moveToPrevious());
            }
            cursor.close();

            if (albumArrayList == null) {
                albumArrayList = new ArrayList<>();
            }
            albumArrayList.clear();
            albumArrayList.addAll(temp);

            albumSelectAdapter = new AlbumSelectAdapter(activity, albumArrayList);
            listView.setAdapter(albumSelectAdapter);
        }
    }

    private void fetchImageData(String album) {
        File file;
        HashSet<Long> selectedImages = new HashSet<>();
        if (imageArrayList != null) {
            Image image;
            for (int i = 0, l = imageArrayList.size(); i < l; i++) {
                image = imageArrayList.get(i);
                file = new File(image.path);
                if (file.exists() && image.isSelected) {
                    selectedImages.add(image.id);
                }
            }
        }

        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " =?", new String[]{ album }, MediaStore.Images.Media.DATE_ADDED);

        int tempCountSelected = 0;
        ArrayList<Image> temp = new ArrayList<>(cursor.getCount());
        if (cursor.moveToLast()) {
            do {
                if (Thread.interrupted()) {
                    return;
                }

                long id = cursor.getLong(cursor.getColumnIndex(projection[0]));
                String name = cursor.getString(cursor.getColumnIndex(projection[1]));
                String path = cursor.getString(cursor.getColumnIndex(projection[2]));
                boolean isSelected = selectedImages.contains(id);
                if (isSelected) {
                    tempCountSelected++;
                }

                file = new File(path);
                if (file.exists()) {
                    temp.add(new Image(id, name, path, isSelected));
                }

            } while (cursor.moveToPrevious());
        }
        cursor.close();

        if (imageArrayList == null) {
            imageArrayList = new ArrayList<>();
        }
        imageArrayList.clear();
        imageArrayList.addAll(temp);

        imageSelectAdapter = new ImageSelectAdapter(activity, imageArrayList);
        gridView.setAdapter(imageSelectAdapter);
    }

    public class ImageSelectAdapter extends BaseAdapter {

        ArrayList<Image> imageArrayList;
        Context context;

        public ImageSelectAdapter(Context context, ArrayList<Image> imageArrayList) {
            this.imageArrayList = imageArrayList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return imageArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public class Holder
        {
            ImageView imageView;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(context).inflate(R.layout.adapter_image_select, viewGroup, false);

            Holder holder = new Holder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);

            Glide.with(context)
                    .load(imageArrayList.get(position).path)
                    .placeholder(R.drawable.placeholder).into(holder.imageView);

            return view;
        }

    }

    public class SelectedImageAdapter extends RecyclerView.Adapter<SelectedImageAdapter.ViewHolder> {

        ArrayList<String> selectedImageList;
        Context context;

        public SelectedImageAdapter(Context context, ArrayList<String> selectedImageList) {
            this.selectedImageList = selectedImageList;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.adapter_selected_image, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            Glide.with(context)
                    .load(selectedImageList.get(position))
                    .placeholder(R.drawable.placeholder).into(holder.imageView);

            holder.delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedImageList.remove(position);
                    notifyDataSetChanged();

                    currentCount--;
                    txtTotalImg.setText(currentCount+"/"+totalCount);
                }
            });

        }

        @Override
        public int getItemCount() {
            return selectedImageList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            RelativeLayout delete_img;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                delete_img = (RelativeLayout) itemView.findViewById(R.id.delete_img);
            }
        }
    }

    public class AlbumSelectAdapter extends BaseAdapter {

        ArrayList<Album> albumArrayList;
        Context context;

        public AlbumSelectAdapter(Context context, ArrayList<Album> albumArrayList) {
            this.albumArrayList = albumArrayList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return albumArrayList.size();
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
            ImageView album_img;
            TextView album_name, album_total;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_album_select, parent, false);

            Holder holder = new Holder();

            holder.album_img = (ImageView) convertView.findViewById(R.id.album_img);
            holder.album_name = (TextView) convertView.findViewById(R.id.album_name);
            holder.album_total = (TextView) convertView.findViewById(R.id.album_total);

            holder.album_name.setText(albumArrayList.get(position).getName());

            Glide.with(context)
                    .load(albumArrayList.get(position).getCover())
                    .placeholder(R.drawable.placeholder).into(holder.album_img);

            holder.album_total.setText("("+albumArrayList.get(position).getCount()+")");

            return convertView;
        }
    }

    private void clickEvent() {

        txtTotalImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtTotalImg.getText().equals("0/9"))
                {
                    Intent intent = new Intent(activity, GridActivity.class);
                    intent.putStringArrayListExtra(Constants.imgList, selectedImageList);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                }
                else
                {
                    Toast.makeText(activity, "Please select image(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                textView.setText(albumArrayList.get(position).getName());
                fetchImageData(albumArrayList.get(position).getName());
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(!txtTotalImg.getText().equals(getResources().getString(R.string.done)))
                {
                    if(currentCount == (totalCount-1))
                    {
                        currentCount++;
                        txtTotalImg.setText(getResources().getString(R.string.done));
                    }
                    else
                    {
                        currentCount++;
                        txtTotalImg.setText(currentCount+"/"+totalCount);
                    }

                    selectedImageList.add(imageArrayList.get(position).path);
                    if(selectedImageList.size() == 1)
                    {
                        selectedImageAdapter = new SelectedImageAdapter(activity, selectedImageList);
                        recyclerView.setAdapter(selectedImageAdapter);
                    }
                    else
                        selectedImageAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(activity, "Can't select more than "+ totalCount +" image(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindControls() {

        totalCount = Integer.parseInt(getIntent().getExtras().getString(imgId));

        txtTotalImg = (TextView) findViewById(R.id.txtTotalImg);
        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        txtTotalImg.setText(currentCount+"/"+totalCount);

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
        if(!textView.getText().equals(getResources().getString(R.string.ImageSelectionActivity_Title)))
        {
            textView.setText(getResources().getString(R.string.ImageSelectionActivity_Title));
            listView.setVisibility(View.VISIBLE);
        }
        else
        {
            finish();
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            super.onBackPressed();
        }
    }
}
