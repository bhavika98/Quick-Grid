package com.app.incroyable.quickgrid.util;

import android.os.Environment;
import android.view.View;

import com.app.incroyable.quickgrid.R;

public class Constants {

    public static final String APP_DIR = Environment.getExternalStorageDirectory() + "/CustomCamera";
    public static final String APP_IMAGE = APP_DIR + "/image";
    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_EDIT_IMG = 0x6;

    public static String imgId = "imgId";
    public static String imgSource = "imgSource";
    public static String imgList = "imgList";
    public static String currentGrid = "currentGrid";
    public static String editedImg = "editedImg";
    public static String imgSharePath = "imgSharePath";
    public static String editedImagePath = "editedImagePath";
    public static int currentActivity = 0;
    public static String sizeGrid = "0";

    public static int normalColor = R.color.Button_Normal_Color;
    public static int selectedColor = R.color.Button_Selected_Color;

    public static int visibleLayout = View.VISIBLE;
    public static int goneLayout = View.GONE;
}
