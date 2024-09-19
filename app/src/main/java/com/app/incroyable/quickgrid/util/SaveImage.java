package com.app.incroyable.quickgrid.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.ui.ShareActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveImage {

    public static void takeScreenshot(String storePath, View view, Activity activity) {

        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();

        saveImage(bitmap, activity);
//        File file = null;
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File directory = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().toString())).append(File.separator).append(activity.getResources().getString(R.string.app_name)).toString());
//            if (!directory.exists())
//                directory.mkdirs();
//
//            String path = directory.getAbsolutePath();
//            file = new File(path + "/" + storePath + "_" + new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date()) + ".png");
//            imgSharePath = String.valueOf(file);
//        }
//
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
//
//        Toast.makeText(activity, activity.getString(R.string.img_save), Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(activity, ShareActivity.class);
//        intent.putExtra(imgSharePath, imgSharePath);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }

    public static void saveImage(Bitmap bitmap, Context context) {
        String imageName = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // For Android 10 and above
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + context.getString(R.string.app_name));

            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try (OutputStream out = context.getContentResolver().openOutputStream(uri)) {
                if (out != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Notify the media scanner about the new file
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

            Toast.makeText(context, context.getString(R.string.img_save), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, ShareActivity.class);
            intent.putExtra("imgSharePath", getPathFromUri(context, uri));
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

        } else {
            // For Android versions before 10
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File directory = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File file = new File(directory, imageName);

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Notify the media scanner about the new file
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

                Toast.makeText(context, context.getString(R.string.img_save), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ShareActivity.class);
                intent.putExtra("imgSharePath", file.getAbsolutePath());
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        }
    }

    public static String getPathFromUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }

        if ("content".equals(uri.getScheme())) {
            // Handle content URIs
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null) {
                try {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (cursor.moveToFirst()) {
                        return cursor.getString(columnIndex);
                    }
                } finally {
                    cursor.close();
                }
            }
        } else if ("file".equals(uri.getScheme())) {
            // Handle file URIs
            return uri.getPath();
        }

        return null;
    }

    public static void deleteFileFromMediaStore(final ContentResolver contentResolver, final File file) {
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

    public static void copyDirectoryOneLocationToAnotherLocation(File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < sourceLocation.listFiles().length; i++) {

                copyDirectoryOneLocationToAnotherLocation(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);

            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }

    }
}
