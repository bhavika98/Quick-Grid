package com.app.incroyable.quickgrid.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.app.mylib.filters.IF1977Filter;
import com.app.mylib.filters.IFBrannanFilter;
import com.app.mylib.filters.IFEarlybirdFilter;
import com.app.mylib.filters.IFInkwellFilter;
import com.app.mylib.filters.IFSierraFilter;
import com.app.mylib.filters.IFToasterFilter;
import com.app.mylib.filters.IFXprollFilter;
import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Crop;
import com.app.incroyable.quickgrid.model.Filter;
import com.app.incroyable.quickgrid.model.Frame;
import com.app.incroyable.quickgrid.model.Grid;
import com.app.incroyable.quickgrid.model.Grid3D;
import com.app.incroyable.quickgrid.model.Margin;
import com.app.incroyable.quickgrid.model.Pattern;
import com.app.incroyable.quickgrid.model.Ratio;
import com.app.incroyable.quickgrid.model.StickerData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.app.incroyable.quickgrid.ui.MainActivity.stickerArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;

public class DataBinder{

    public static ArrayList<Grid3D> fetchGrid3dData()
    {
        ArrayList<Grid3D> grid3DArrayList = new ArrayList<>();

        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_1, R.layout.frame_1_1, 1, 1, 1));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_2, R.layout.frame_1_3, 1, 1, 2));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_3, R.layout.frame_1_4, 1, 1, 3));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_12, R.layout.frame_1_17, 1, 2, 4));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_4, R.layout.frame_1_5, 1, 1, 5));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_5, R.layout.frame_1_6, 1, 1, 6));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_6, R.layout.frame_1_7, 1, 1, 7));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_7, R.layout.frame_1_9, 1, 1, 8));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_8, R.layout.frame_1_11, 1, 1, 9));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_9, R.layout.frame_1_14, 1, 1, 10));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_10, R.layout.frame_1_15, 1, 1, 11));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_1_11, R.layout.frame_1_16, 1, 1, 12));

        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_1, R.layout.frame_2_1, 2, 1, 13));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_6, R.layout.frame_2_7, 2, 2, 14));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_2, R.layout.frame_2_2, 2, 1, 15));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_3, R.layout.frame_2_4, 2, 1, 16));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_7, R.layout.frame_2_8, 2, 2, 17));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_4, R.layout.frame_2_5, 2, 1, 18));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_2_5, R.layout.frame_2_6, 2, 1, 19));

        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_1, R.layout.frame_3_1, 3, 1, 20));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_2, R.layout.frame_3_2, 3, 1, 21));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_3, R.layout.frame_3_3, 3, 1, 22));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_12, R.layout.frame_3_12, 3, 2, 23));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_4, R.layout.frame_3_4, 3, 1, 24));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_5, R.layout.frame_3_5, 3, 1, 25));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_13, R.layout.frame_3_13, 3, 2, 26));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_6, R.layout.frame_3_6, 3, 1, 27));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_7, R.layout.frame_3_7, 3, 1, 28));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_8, R.layout.frame_3_8, 3, 1, 29));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_9, R.layout.frame_3_9, 3, 1, 30));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_10, R.layout.frame_3_10, 3, 1, 31));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_3_11, R.layout.frame_3_11, 3, 1, 32));

        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_1, R.layout.frame_4_1, 4, 1, 33));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_2, R.layout.frame_4_2, 4, 1, 34));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_3, R.layout.frame_4_3, 4, 1, 35));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_4, R.layout.frame_4_4, 4, 1, 36));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_5, R.layout.frame_4_5, 4, 1, 37));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_6, R.layout.frame_4_6, 4, 1, 38));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_7, R.layout.frame_4_7, 4, 1, 39));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_4_8, R.layout.frame_4_8, 4, 1, 40));

        grid3DArrayList.add(new Grid3D(R.drawable.thumb_5_1, R.layout.frame_5_1, 5, 1, 41));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_5_5, R.layout.frame_5_5, 5, 2, 42));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_5_2, R.layout.frame_5_2, 5, 1, 43));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_5_3, R.layout.frame_5_3, 5, 1, 44));
        grid3DArrayList.add(new Grid3D(R.drawable.thumb_5_4, R.layout.frame_5_4, 5, 1, 45));

        return grid3DArrayList;
    }

    public static ArrayList<Integer> fetchTextPatternData(Activity activity)
    {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.p19);
        list.add(R.drawable.p22);
        list.add(R.drawable.p26);
        list.add(R.drawable.p27);
        list.add(R.drawable.p29);
        list.add(R.drawable.p37);
        list.add(R.drawable.p39);
        list.add(R.drawable.p41);
        list.add(R.drawable.p42);
        list.add(R.drawable.p45);
        list.add(R.drawable.p47);
        list.add(R.drawable.p51);
        list.add(R.drawable.p63);
        list.add(R.drawable.p70);
        list.add(R.drawable.p72);
        list.add(R.drawable.p73);
        list.add(R.drawable.p75);
        list.add(R.drawable.p77);
        list.add(R.drawable.p79);
        list.add(R.drawable.p80);
        list.add(R.drawable.p81);
        list.add(R.drawable.p85);
        list.add(R.drawable.p87);
        list.add(R.drawable.p93);
        list.add(R.drawable.p95);
        list.add(R.drawable.p100);
        list.add(R.drawable.p105);
        list.add(R.drawable.p110);
        list.add(R.drawable.p121);
        list.add(R.drawable.p129);
        list.add(R.drawable.p132);
        list.add(R.drawable.p138);
        list.add(R.drawable.p139);
        list.add(R.drawable.p140);
        list.add(R.drawable.p141);
        list.add(R.drawable.p143);
        list.add(R.drawable.p145);
        list.add(R.drawable.p147);

        return list;
    }

    public static ArrayList<Integer> fetchPrimaryColor()
    {
        ArrayList<Integer> list = new ArrayList<>();

        // https://material.io/guidelines/style/color.html#color-color-palette
        list.add(Color.parseColor("#f44336")); // Red - 0
        list.add(Color.parseColor("#e91e63")); // Pink - 1
        list.add(Color.parseColor("#9c27b0")); // Purple - 2
        list.add(Color.parseColor("#673ab7")); // Deep Purple - 3
        list.add(Color.parseColor("#3f51b5")); // Indigo - 4
        list.add(Color.parseColor("#2196f3")); // Blue - 5
        list.add(Color.parseColor("#03a9f4")); // Light Blue - 6
        list.add(Color.parseColor("#00bcd4")); // Cyan - 7
        list.add(Color.parseColor("#009688")); // Teal - 8
        list.add(Color.parseColor("#4caf50")); // Green - 9
        list.add(Color.parseColor("#8bc34a")); // Light Green - 10
        list.add(Color.parseColor("#cddc39")); // Lime - 11
        list.add(Color.parseColor("#ffeb3b")); // Yellow - 12
        list.add(Color.parseColor("#ffc107")); // Amber - 13
        list.add(Color.parseColor("#ff9800")); // Orange - 14
        list.add(Color.parseColor("#ff5722")); // Deep Orange - 15
        list.add(Color.parseColor("#795548")); // Brown - 16
        list.add(Color.parseColor("#9e9e9e")); // Grey - 17
        list.add(Color.parseColor("#607d8b")); // Blue Grey - 18
        list.add(Color.parseColor("#ffffff")); // White - 19
        list.add(Color.parseColor("#000000")); // Black - 20

        return list;
    }

    public static ArrayList<Integer> fetchtextStickerColor()
    {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(Color.parseColor("#ffffff"));
        list.add(Color.parseColor("#000000"));
        list.add(Color.parseColor("#ffebee"));
        list.add(Color.parseColor("#ffcdd2"));
        list.add(Color.parseColor("#ef9a9a"));
        list.add(Color.parseColor("#e57373"));
        list.add(Color.parseColor("#ef5350"));
        list.add(Color.parseColor("#f44336"));
        list.add(Color.parseColor("#e53935"));
        list.add(Color.parseColor("#d32f2f"));
        list.add(Color.parseColor("#c62828"));
        list.add(Color.parseColor("#b71c1c"));
        list.add(Color.parseColor("#ff8a80"));
        list.add(Color.parseColor("#ff5252"));
        list.add(Color.parseColor("#ff1744"));
        list.add(Color.parseColor("#d50000"));

        list.add(Color.parseColor("#fce4ec"));
        list.add(Color.parseColor("#f8bbd0"));
        list.add(Color.parseColor("#f48fb1"));
        list.add(Color.parseColor("#f06292"));
        list.add(Color.parseColor("#ec407a"));
        list.add(Color.parseColor("#e91e63"));
        list.add(Color.parseColor("#d81b60"));
        list.add(Color.parseColor("#c2185b"));
        list.add(Color.parseColor("#ad1457"));
        list.add(Color.parseColor("#880e4f"));
        list.add(Color.parseColor("#ff80ab"));
        list.add(Color.parseColor("#ff4081"));
        list.add(Color.parseColor("#f50057"));
        list.add(Color.parseColor("#c51162"));

        list.add(Color.parseColor("#f3e5f5"));
        list.add(Color.parseColor("#e1bee7"));
        list.add(Color.parseColor("#ce93d8"));
        list.add(Color.parseColor("#ba68c8"));
        list.add(Color.parseColor("#ab47bc"));
        list.add(Color.parseColor("#9c27b0"));
        list.add(Color.parseColor("#8e24aa"));
        list.add(Color.parseColor("#7b1fa2"));
        list.add(Color.parseColor("#6a1b9a"));
        list.add(Color.parseColor("#4a148c"));
        list.add(Color.parseColor("#ea80fc"));
        list.add(Color.parseColor("#e040fb"));
        list.add(Color.parseColor("#d500f9"));
        list.add(Color.parseColor("#aa00ff"));

        list.add(Color.parseColor("#ede7f6"));
        list.add(Color.parseColor("#d1c4e9"));
        list.add(Color.parseColor("#b39ddb"));
        list.add(Color.parseColor("#9575cd"));
        list.add(Color.parseColor("#7e57c2"));
        list.add(Color.parseColor("#673ab7"));
        list.add(Color.parseColor("#5e35b1"));
        list.add(Color.parseColor("#512da8"));
        list.add(Color.parseColor("#4527a0"));
        list.add(Color.parseColor("#311b92"));
        list.add(Color.parseColor("#b388ff"));
        list.add(Color.parseColor("#7c4dff"));
        list.add(Color.parseColor("#651fff"));
        list.add(Color.parseColor("#6200ea"));

        list.add(Color.parseColor("#e8eaf6"));
        list.add(Color.parseColor("#c5cae9"));
        list.add(Color.parseColor("#9fa8da"));
        list.add(Color.parseColor("#7986cb"));
        list.add(Color.parseColor("#5c6bc0"));
        list.add(Color.parseColor("#3f51b5"));
        list.add(Color.parseColor("#3949ab"));
        list.add(Color.parseColor("#303f9f"));
        list.add(Color.parseColor("#283593"));
        list.add(Color.parseColor("#1a237e"));
        list.add(Color.parseColor("#8c9eff"));
        list.add(Color.parseColor("#536dfe"));
        list.add(Color.parseColor("#3d5afe"));
        list.add(Color.parseColor("#304ffe"));

        list.add(Color.parseColor("#e3f2fd"));
        list.add(Color.parseColor("#bbdefb"));
        list.add(Color.parseColor("#90caf9"));
        list.add(Color.parseColor("#64b5f6"));
        list.add(Color.parseColor("#42a5f5"));
        list.add(Color.parseColor("#2196f3"));
        list.add(Color.parseColor("#1e88e5"));
        list.add(Color.parseColor("#1976d2"));
        list.add(Color.parseColor("#1565c0"));
        list.add(Color.parseColor("#0d47a1"));
        list.add(Color.parseColor("#82b1ff"));
        list.add(Color.parseColor("#448aff"));
        list.add(Color.parseColor("#2979ff"));
        list.add(Color.parseColor("#2962ff"));

        list.add(Color.parseColor("#e1f5fe"));
        list.add(Color.parseColor("#b3e5fc"));
        list.add(Color.parseColor("#81d4fa"));
        list.add(Color.parseColor("#4fc3f7"));
        list.add(Color.parseColor("#29b6f6"));
        list.add(Color.parseColor("#03a9f4"));
        list.add(Color.parseColor("#039be5"));
        list.add(Color.parseColor("#0288d1"));
        list.add(Color.parseColor("#0277bd"));
        list.add(Color.parseColor("#01579b"));
        list.add(Color.parseColor("#80d8ff"));
        list.add(Color.parseColor("#40c4ff"));
        list.add(Color.parseColor("#00b0ff"));
        list.add(Color.parseColor("#0091ea"));

        list.add(Color.parseColor("#e0f7fa"));
        list.add(Color.parseColor("#b2ebf2"));
        list.add(Color.parseColor("#80deea"));
        list.add(Color.parseColor("#4dd0e1"));
        list.add(Color.parseColor("#26c6da"));
        list.add(Color.parseColor("#00bcd4"));
        list.add(Color.parseColor("#00acc1"));
        list.add(Color.parseColor("#0097a7"));
        list.add(Color.parseColor("#00838f"));
        list.add(Color.parseColor("#006064"));
        list.add(Color.parseColor("#84ffff"));
        list.add(Color.parseColor("#18ffff"));
        list.add(Color.parseColor("#00e5ff"));
        list.add(Color.parseColor("#00b8d4"));

        list.add(Color.parseColor("#e0f2f1"));
        list.add(Color.parseColor("#b2dfdb"));
        list.add(Color.parseColor("#80cbc4"));
        list.add(Color.parseColor("#4db6ac"));
        list.add(Color.parseColor("#26a69a"));
        list.add(Color.parseColor("#009688"));
        list.add(Color.parseColor("#00897b"));
        list.add(Color.parseColor("#00796b"));
        list.add(Color.parseColor("#00695c"));
        list.add(Color.parseColor("#004d40"));
        list.add(Color.parseColor("#a7ffeb"));
        list.add(Color.parseColor("#64ffda"));
        list.add(Color.parseColor("#1de9b6"));
        list.add(Color.parseColor("#00bfa5"));

        list.add(Color.parseColor("#e8f5e9"));
        list.add(Color.parseColor("#c8e6c9"));
        list.add(Color.parseColor("#a5d6a7"));
        list.add(Color.parseColor("#81c784"));
        list.add(Color.parseColor("#66bb6a"));
        list.add(Color.parseColor("#4caf50"));
        list.add(Color.parseColor("#43a047"));
        list.add(Color.parseColor("#388e3c"));
        list.add(Color.parseColor("#2e7d32"));
        list.add(Color.parseColor("#1b5e20"));
        list.add(Color.parseColor("#b9f6ca"));
        list.add(Color.parseColor("#69f0ae"));
        list.add(Color.parseColor("#00e676"));
        list.add(Color.parseColor("#00c853"));

        list.add(Color.parseColor("#f1f8e9"));
        list.add(Color.parseColor("#dcedc8"));
        list.add(Color.parseColor("#c5e1a5"));
        list.add(Color.parseColor("#aed581"));
        list.add(Color.parseColor("#9ccc65"));
        list.add(Color.parseColor("#8bc34a"));
        list.add(Color.parseColor("#7cb342"));
        list.add(Color.parseColor("#689f38"));
        list.add(Color.parseColor("#558b2f"));
        list.add(Color.parseColor("#33691e"));
        list.add(Color.parseColor("#ccff90"));
        list.add(Color.parseColor("#b2ff59"));
        list.add(Color.parseColor("#76ff03"));
        list.add(Color.parseColor("#64dd17"));

        list.add(Color.parseColor("#f9fbe7"));
        list.add(Color.parseColor("#f0f4c3"));
        list.add(Color.parseColor("#e6ee9c"));
        list.add(Color.parseColor("#dce775"));
        list.add(Color.parseColor("#d4e157"));
        list.add(Color.parseColor("#cddc39"));
        list.add(Color.parseColor("#c0ca33"));
        list.add(Color.parseColor("#afb42b"));
        list.add(Color.parseColor("#9e9d24"));
        list.add(Color.parseColor("#827717"));
        list.add(Color.parseColor("#f4ff81"));
        list.add(Color.parseColor("#eeff41"));
        list.add(Color.parseColor("#c6ff00"));
        list.add(Color.parseColor("#aeea00"));

        list.add(Color.parseColor("#fffde7"));
        list.add(Color.parseColor("#fff9c4"));
        list.add(Color.parseColor("#fff59d"));
        list.add(Color.parseColor("#fff176"));
        list.add(Color.parseColor("#ffee58"));
        list.add(Color.parseColor("#ffeb3b"));
        list.add(Color.parseColor("#fdd835"));
        list.add(Color.parseColor("#fbc02d"));
        list.add(Color.parseColor("#f9a825"));
        list.add(Color.parseColor("#f57f17"));
        list.add(Color.parseColor("#ffff8d"));
        list.add(Color.parseColor("#ffff00"));
        list.add(Color.parseColor("#ffea00"));
        list.add(Color.parseColor("#ffd600"));

        list.add(Color.parseColor("#fff8e1"));
        list.add(Color.parseColor("#ffecb3"));
        list.add(Color.parseColor("#ffe082"));
        list.add(Color.parseColor("#ffd54f"));
        list.add(Color.parseColor("#ffca28"));
        list.add(Color.parseColor("#ffc107"));
        list.add(Color.parseColor("#ffb300"));
        list.add(Color.parseColor("#ffa000"));
        list.add(Color.parseColor("#ff8f00"));
        list.add(Color.parseColor("#ff6f00"));
        list.add(Color.parseColor("#ffe57f"));
        list.add(Color.parseColor("#ffd740"));
        list.add(Color.parseColor("#ffc400"));
        list.add(Color.parseColor("#ffab00"));

        list.add(Color.parseColor("#fff3e0"));
        list.add(Color.parseColor("#ffe0b2"));
        list.add(Color.parseColor("#ffcc80"));
        list.add(Color.parseColor("#ffb74d"));
        list.add(Color.parseColor("#ffa726"));
        list.add(Color.parseColor("#ff9800"));
        list.add(Color.parseColor("#fb8c00"));
        list.add(Color.parseColor("#f57c00"));
        list.add(Color.parseColor("#ef6c00"));
        list.add(Color.parseColor("#e65100"));
        list.add(Color.parseColor("#ffd180"));
        list.add(Color.parseColor("#ffab40"));
        list.add(Color.parseColor("#ff9100"));
        list.add(Color.parseColor("#ff6d00"));

        list.add(Color.parseColor("#fbe9e7"));
        list.add(Color.parseColor("#ffccbc"));
        list.add(Color.parseColor("#ffab91"));
        list.add(Color.parseColor("#ff8a65"));
        list.add(Color.parseColor("#ff7043"));
        list.add(Color.parseColor("#ff5722"));
        list.add(Color.parseColor("#f4511e"));
        list.add(Color.parseColor("#e64a19"));
        list.add(Color.parseColor("#d84315"));
        list.add(Color.parseColor("#bf360c"));
        list.add(Color.parseColor("#ff9e80"));
        list.add(Color.parseColor("#ff6e40"));
        list.add(Color.parseColor("#ff3d00"));
        list.add(Color.parseColor("#dd2c00"));

        list.add(Color.parseColor("#efebe9"));
        list.add(Color.parseColor("#d7ccc8"));
        list.add(Color.parseColor("#bcaaa4"));
        list.add(Color.parseColor("#a1887f"));
        list.add(Color.parseColor("#8d6e63"));
        list.add(Color.parseColor("#795548"));
        list.add(Color.parseColor("#6d4c41"));
        list.add(Color.parseColor("#5d4037"));
        list.add(Color.parseColor("#4e342e"));
        list.add(Color.parseColor("#3e2723"));

        list.add(Color.parseColor("#fafafa"));
        list.add(Color.parseColor("#f5f5f5"));
        list.add(Color.parseColor("#eeeeee"));
        list.add(Color.parseColor("#e0e0e0"));
        list.add(Color.parseColor("#bdbdbd"));
        list.add(Color.parseColor("#9e9e9e"));
        list.add(Color.parseColor("#757575"));
        list.add(Color.parseColor("#616161"));
        list.add(Color.parseColor("#424242"));
        list.add(Color.parseColor("#212121"));

        list.add(Color.parseColor("#eceff1"));
        list.add(Color.parseColor("#cfd8dc"));
        list.add(Color.parseColor("#b0bec5"));
        list.add(Color.parseColor("#90a4ae"));
        list.add(Color.parseColor("#78909c"));
        list.add(Color.parseColor("#607d8b"));
        list.add(Color.parseColor("#546e7a"));
        list.add(Color.parseColor("#455a64"));
        list.add(Color.parseColor("#37474f"));
        list.add(Color.parseColor("#263238"));

        return list;
    }

    public static ArrayList<Integer> fetchSecondaryColor(int position)
    {
        ArrayList<Integer> list = new ArrayList<>();
        switch (position)
        {
            case 0:
                list.add(Color.parseColor("#ffebee"));
                list.add(Color.parseColor("#ffcdd2"));
                list.add(Color.parseColor("#ef9a9a"));
                list.add(Color.parseColor("#e57373"));
                list.add(Color.parseColor("#ef5350"));
                list.add(Color.parseColor("#f44336"));
                list.add(Color.parseColor("#e53935"));
                list.add(Color.parseColor("#d32f2f"));
                list.add(Color.parseColor("#c62828"));
                list.add(Color.parseColor("#b71c1c"));

                list.add(Color.parseColor("#ff8a80"));
                list.add(Color.parseColor("#ff5252"));
                list.add(Color.parseColor("#ff1744"));
                list.add(Color.parseColor("#d50000"));
                break;

            case 1:
                list.add(Color.parseColor("#fce4ec"));
                list.add(Color.parseColor("#f8bbd0"));
                list.add(Color.parseColor("#f48fb1"));
                list.add(Color.parseColor("#f06292"));
                list.add(Color.parseColor("#ec407a"));
                list.add(Color.parseColor("#e91e63"));
                list.add(Color.parseColor("#d81b60"));
                list.add(Color.parseColor("#c2185b"));
                list.add(Color.parseColor("#ad1457"));
                list.add(Color.parseColor("#880e4f"));

                list.add(Color.parseColor("#ff80ab"));
                list.add(Color.parseColor("#ff4081"));
                list.add(Color.parseColor("#f50057"));
                list.add(Color.parseColor("#c51162"));
                break;

            case 2:
                list.add(Color.parseColor("#f3e5f5"));
                list.add(Color.parseColor("#e1bee7"));
                list.add(Color.parseColor("#ce93d8"));
                list.add(Color.parseColor("#ba68c8"));
                list.add(Color.parseColor("#ab47bc"));
                list.add(Color.parseColor("#9c27b0"));
                list.add(Color.parseColor("#8e24aa"));
                list.add(Color.parseColor("#7b1fa2"));
                list.add(Color.parseColor("#6a1b9a"));
                list.add(Color.parseColor("#4a148c"));

                list.add(Color.parseColor("#ea80fc"));
                list.add(Color.parseColor("#e040fb"));
                list.add(Color.parseColor("#d500f9"));
                list.add(Color.parseColor("#aa00ff"));
                break;

            case 3:
                list.add(Color.parseColor("#ede7f6"));
                list.add(Color.parseColor("#d1c4e9"));
                list.add(Color.parseColor("#b39ddb"));
                list.add(Color.parseColor("#9575cd"));
                list.add(Color.parseColor("#7e57c2"));
                list.add(Color.parseColor("#673ab7"));
                list.add(Color.parseColor("#5e35b1"));
                list.add(Color.parseColor("#512da8"));
                list.add(Color.parseColor("#4527a0"));
                list.add(Color.parseColor("#311b92"));

                list.add(Color.parseColor("#b388ff"));
                list.add(Color.parseColor("#7c4dff"));
                list.add(Color.parseColor("#651fff"));
                list.add(Color.parseColor("#6200ea"));
                break;

            case 4:
                list.add(Color.parseColor("#e8eaf6"));
                list.add(Color.parseColor("#c5cae9"));
                list.add(Color.parseColor("#9fa8da"));
                list.add(Color.parseColor("#7986cb"));
                list.add(Color.parseColor("#5c6bc0"));
                list.add(Color.parseColor("#3f51b5"));
                list.add(Color.parseColor("#3949ab"));
                list.add(Color.parseColor("#303f9f"));
                list.add(Color.parseColor("#283593"));
                list.add(Color.parseColor("#1a237e"));

                list.add(Color.parseColor("#8c9eff"));
                list.add(Color.parseColor("#536dfe"));
                list.add(Color.parseColor("#3d5afe"));
                list.add(Color.parseColor("#304ffe"));
                break;

            case 5:
                list.add(Color.parseColor("#e3f2fd"));
                list.add(Color.parseColor("#bbdefb"));
                list.add(Color.parseColor("#90caf9"));
                list.add(Color.parseColor("#64b5f6"));
                list.add(Color.parseColor("#42a5f5"));
                list.add(Color.parseColor("#2196f3"));
                list.add(Color.parseColor("#1e88e5"));
                list.add(Color.parseColor("#1976d2"));
                list.add(Color.parseColor("#1565c0"));
                list.add(Color.parseColor("#0d47a1"));

                list.add(Color.parseColor("#82b1ff"));
                list.add(Color.parseColor("#448aff"));
                list.add(Color.parseColor("#2979ff"));
                list.add(Color.parseColor("#2962ff"));
                break;

            case 6:
                list.add(Color.parseColor("#e1f5fe"));
                list.add(Color.parseColor("#b3e5fc"));
                list.add(Color.parseColor("#81d4fa"));
                list.add(Color.parseColor("#4fc3f7"));
                list.add(Color.parseColor("#29b6f6"));
                list.add(Color.parseColor("#03a9f4"));
                list.add(Color.parseColor("#039be5"));
                list.add(Color.parseColor("#0288d1"));
                list.add(Color.parseColor("#0277bd"));
                list.add(Color.parseColor("#01579b"));

                list.add(Color.parseColor("#80d8ff"));
                list.add(Color.parseColor("#40c4ff"));
                list.add(Color.parseColor("#00b0ff"));
                list.add(Color.parseColor("#0091ea"));
                break;

            case 7:
                list.add(Color.parseColor("#e0f7fa"));
                list.add(Color.parseColor("#b2ebf2"));
                list.add(Color.parseColor("#80deea"));
                list.add(Color.parseColor("#4dd0e1"));
                list.add(Color.parseColor("#26c6da"));
                list.add(Color.parseColor("#00bcd4"));
                list.add(Color.parseColor("#00acc1"));
                list.add(Color.parseColor("#0097a7"));
                list.add(Color.parseColor("#00838f"));
                list.add(Color.parseColor("#006064"));

                list.add(Color.parseColor("#84ffff"));
                list.add(Color.parseColor("#18ffff"));
                list.add(Color.parseColor("#00e5ff"));
                list.add(Color.parseColor("#00b8d4"));
                break;

            case 8:
                list.add(Color.parseColor("#e0f2f1"));
                list.add(Color.parseColor("#b2dfdb"));
                list.add(Color.parseColor("#80cbc4"));
                list.add(Color.parseColor("#4db6ac"));
                list.add(Color.parseColor("#26a69a"));
                list.add(Color.parseColor("#009688"));
                list.add(Color.parseColor("#00897b"));
                list.add(Color.parseColor("#00796b"));
                list.add(Color.parseColor("#00695c"));
                list.add(Color.parseColor("#004d40"));

                list.add(Color.parseColor("#a7ffeb"));
                list.add(Color.parseColor("#64ffda"));
                list.add(Color.parseColor("#1de9b6"));
                list.add(Color.parseColor("#00bfa5"));
                break;

            case 9:
                list.add(Color.parseColor("#e8f5e9"));
                list.add(Color.parseColor("#c8e6c9"));
                list.add(Color.parseColor("#a5d6a7"));
                list.add(Color.parseColor("#81c784"));
                list.add(Color.parseColor("#66bb6a"));
                list.add(Color.parseColor("#4caf50"));
                list.add(Color.parseColor("#43a047"));
                list.add(Color.parseColor("#388e3c"));
                list.add(Color.parseColor("#2e7d32"));
                list.add(Color.parseColor("#1b5e20"));

                list.add(Color.parseColor("#b9f6ca"));
                list.add(Color.parseColor("#69f0ae"));
                list.add(Color.parseColor("#00e676"));
                list.add(Color.parseColor("#00c853"));
                break;

            case 10:
                list.add(Color.parseColor("#f1f8e9"));
                list.add(Color.parseColor("#dcedc8"));
                list.add(Color.parseColor("#c5e1a5"));
                list.add(Color.parseColor("#aed581"));
                list.add(Color.parseColor("#9ccc65"));
                list.add(Color.parseColor("#8bc34a"));
                list.add(Color.parseColor("#7cb342"));
                list.add(Color.parseColor("#689f38"));
                list.add(Color.parseColor("#558b2f"));
                list.add(Color.parseColor("#33691e"));

                list.add(Color.parseColor("#ccff90"));
                list.add(Color.parseColor("#b2ff59"));
                list.add(Color.parseColor("#76ff03"));
                list.add(Color.parseColor("#64dd17"));
                break;

            case 11:
                list.add(Color.parseColor("#f9fbe7"));
                list.add(Color.parseColor("#f0f4c3"));
                list.add(Color.parseColor("#e6ee9c"));
                list.add(Color.parseColor("#dce775"));
                list.add(Color.parseColor("#d4e157"));
                list.add(Color.parseColor("#cddc39"));
                list.add(Color.parseColor("#c0ca33"));
                list.add(Color.parseColor("#afb42b"));
                list.add(Color.parseColor("#9e9d24"));
                list.add(Color.parseColor("#827717"));

                list.add(Color.parseColor("#f4ff81"));
                list.add(Color.parseColor("#eeff41"));
                list.add(Color.parseColor("#c6ff00"));
                list.add(Color.parseColor("#aeea00"));
                break;

            case 12:
                list.add(Color.parseColor("#fffde7"));
                list.add(Color.parseColor("#fff9c4"));
                list.add(Color.parseColor("#fff59d"));
                list.add(Color.parseColor("#fff176"));
                list.add(Color.parseColor("#ffee58"));
                list.add(Color.parseColor("#ffeb3b"));
                list.add(Color.parseColor("#fdd835"));
                list.add(Color.parseColor("#fbc02d"));
                list.add(Color.parseColor("#f9a825"));
                list.add(Color.parseColor("#f57f17"));

                list.add(Color.parseColor("#ffff8d"));
                list.add(Color.parseColor("#ffff00"));
                list.add(Color.parseColor("#ffea00"));
                list.add(Color.parseColor("#ffd600"));
                break;

            case 13:
                list.add(Color.parseColor("#fff8e1"));
                list.add(Color.parseColor("#ffecb3"));
                list.add(Color.parseColor("#ffe082"));
                list.add(Color.parseColor("#ffd54f"));
                list.add(Color.parseColor("#ffca28"));
                list.add(Color.parseColor("#ffc107"));
                list.add(Color.parseColor("#ffb300"));
                list.add(Color.parseColor("#ffa000"));
                list.add(Color.parseColor("#ff8f00"));
                list.add(Color.parseColor("#ff6f00"));

                list.add(Color.parseColor("#ffe57f"));
                list.add(Color.parseColor("#ffd740"));
                list.add(Color.parseColor("#ffc400"));
                list.add(Color.parseColor("#ffab00"));
                break;

            case 14:
                list.add(Color.parseColor("#fff3e0"));
                list.add(Color.parseColor("#ffe0b2"));
                list.add(Color.parseColor("#ffcc80"));
                list.add(Color.parseColor("#ffb74d"));
                list.add(Color.parseColor("#ffa726"));
                list.add(Color.parseColor("#ff9800"));
                list.add(Color.parseColor("#fb8c00"));
                list.add(Color.parseColor("#f57c00"));
                list.add(Color.parseColor("#ef6c00"));
                list.add(Color.parseColor("#e65100"));

                list.add(Color.parseColor("#ffd180"));
                list.add(Color.parseColor("#ffab40"));
                list.add(Color.parseColor("#ff9100"));
                list.add(Color.parseColor("#ff6d00"));
                break;

            case 15:
                list.add(Color.parseColor("#fbe9e7"));
                list.add(Color.parseColor("#ffccbc"));
                list.add(Color.parseColor("#ffab91"));
                list.add(Color.parseColor("#ff8a65"));
                list.add(Color.parseColor("#ff7043"));
                list.add(Color.parseColor("#ff5722"));
                list.add(Color.parseColor("#f4511e"));
                list.add(Color.parseColor("#e64a19"));
                list.add(Color.parseColor("#d84315"));
                list.add(Color.parseColor("#bf360c"));

                list.add(Color.parseColor("#ff9e80"));
                list.add(Color.parseColor("#ff6e40"));
                list.add(Color.parseColor("#ff3d00"));
                list.add(Color.parseColor("#dd2c00"));
                break;

            case 16:
                list.add(Color.parseColor("#efebe9"));
                list.add(Color.parseColor("#d7ccc8"));
                list.add(Color.parseColor("#bcaaa4"));
                list.add(Color.parseColor("#a1887f"));
                list.add(Color.parseColor("#8d6e63"));
                list.add(Color.parseColor("#795548"));
                list.add(Color.parseColor("#6d4c41"));
                list.add(Color.parseColor("#5d4037"));
                list.add(Color.parseColor("#4e342e"));
                list.add(Color.parseColor("#3e2723"));
                break;

            case 17:
                list.add(Color.parseColor("#fafafa"));
                list.add(Color.parseColor("#f5f5f5"));
                list.add(Color.parseColor("#eeeeee"));
                list.add(Color.parseColor("#e0e0e0"));
                list.add(Color.parseColor("#bdbdbd"));
                list.add(Color.parseColor("#9e9e9e"));
                list.add(Color.parseColor("#757575"));
                list.add(Color.parseColor("#616161"));
                list.add(Color.parseColor("#424242"));
                list.add(Color.parseColor("#212121"));
                break;

            case 18:
                list.add(Color.parseColor("#eceff1"));
                list.add(Color.parseColor("#cfd8dc"));
                list.add(Color.parseColor("#b0bec5"));
                list.add(Color.parseColor("#90a4ae"));
                list.add(Color.parseColor("#78909c"));
                list.add(Color.parseColor("#607d8b"));
                list.add(Color.parseColor("#546e7a"));
                list.add(Color.parseColor("#455a64"));
                list.add(Color.parseColor("#37474f"));
                list.add(Color.parseColor("#263238"));
                break;
        }
        return list;
    }

    public static ArrayList<Filter> fetchFilters()
    {
        ArrayList<Filter> filterArrayList = new ArrayList<>();

        filterArrayList.add(new Filter(R.drawable.filter_1, "Original"));
        filterArrayList.add(new Filter(R.drawable.filter_2, "Tropic"));
        filterArrayList.add(new Filter(R.drawable.filter_3, "Valencia"));
        filterArrayList.add(new Filter(R.drawable.filter_4, "Nashville"));
        filterArrayList.add(new Filter(R.drawable.filter_5, "B&W"));
        filterArrayList.add(new Filter(R.drawable.filter_6, "Lomo"));
        filterArrayList.add(new Filter(R.drawable.filter_7, "Autumn"));
        filterArrayList.add(new Filter(R.drawable.filter_8, "Fresh"));
        filterArrayList.add(new Filter(R.drawable.filter_9, "Elegance"));
        filterArrayList.add(new Filter(R.drawable.filter_10, "Mellow"));
        filterArrayList.add(new Filter(R.drawable.filter_11, "Time"));
        filterArrayList.add(new Filter(R.drawable.filter_12, "Earlybird"));
        filterArrayList.add(new Filter(R.drawable.filter_13, "Dark"));
        filterArrayList.add(new Filter(R.drawable.filter_14, "Retro"));
        filterArrayList.add(new Filter(R.drawable.filter_15, "Twilight"));
        filterArrayList.add(new Filter(R.drawable.filter_16, "Inkwell"));
        filterArrayList.add(new Filter(R.drawable.filter_17, "Rise"));
        filterArrayList.add(new Filter(R.drawable.filter_18, "Myth"));
        filterArrayList.add(new Filter(R.drawable.filter_19, "Soft"));
        filterArrayList.add(new Filter(R.drawable.filter_20, "Sweet"));
        filterArrayList.add(new Filter(R.drawable.filter_21, "Forest"));

        return filterArrayList;
    }

    public static GPUImageFilter applyFilter(int position, Activity activity)
    {
        GPUImageFilter gpuImageFilter = null;
        GPUImageLookupFilter gpuImageLookupFilter = new GPUImageLookupFilter();;

        switch (position) {
            case 0:
                gpuImageFilter = new GPUImageFilter();
                break;

            case 1:
                gpuImageFilter = new IF1977Filter(activity);
                break;

            case 2:
                gpuImageFilter = new IFBrannanFilter(activity);
                break;

            case 3:
                gpuImageFilter = new IFEarlybirdFilter(activity);
                break;

            case 4:
                gpuImageFilter = new IFInkwellFilter(activity);
                break;

            case 5:
                gpuImageFilter = new IFSierraFilter(activity);
                break;

            case 6:
                gpuImageFilter = new IFToasterFilter(activity);
                break;

            case 7:
                gpuImageFilter = new IFXprollFilter(activity);
                break;

            case 8:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf2));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 9:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf3));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 10:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf6));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 11:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf8));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 12:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf10));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 13:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf11));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 14:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf12));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 15:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf14));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 16:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf17));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 17:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf18));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 18:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf24));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 19:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf28));
                gpuImageFilter = gpuImageLookupFilter;
                break;

            case 20:
                gpuImageLookupFilter.setBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pf32));
                gpuImageFilter = gpuImageLookupFilter;
                break;
        }
        return gpuImageFilter;
    }

    public static ArrayList<Grid> fetchGridData(int position) {
        ArrayList<Grid> gridArrayList = new ArrayList<>();

        switch (position)
        {
            case 1:
                gridArrayList.add(new Grid(R.drawable.box1, R.layout.grid_1_1, 11, 1));
                gridArrayList.add(new Grid(R.drawable.box2, R.layout.grid_1_9, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box3, R.layout.grid_1_2, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box4, R.layout.grid_1_8, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box5, R.layout.grid_1_5, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box6, R.layout.grid_1_6, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box7, R.layout.grid_1_7, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box8, R.layout.grid_1_10, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box9, R.layout.grid_1_11, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box10, R.layout.grid_1_4, 12, 1));
                gridArrayList.add(new Grid(R.drawable.box11, R.layout.grid_1_3, 12, 1));
                break;

            case 2:
                gridArrayList.add(new Grid(R.drawable.box12, R.layout.grid_2_1, 21, 1));
                gridArrayList.add(new Grid(R.drawable.box13, R.layout.grid_2_2, 22, 1));
                gridArrayList.add(new Grid(R.drawable.box14, R.layout.grid_2_3, 23, 1));
                gridArrayList.add(new Grid(R.drawable.box15, R.layout.grid_2_4, 23, 1));

                gridArrayList.add(new Grid(R.drawable.box16, R.layout.grid_2_5, 23, 1));
                gridArrayList.add(new Grid(R.drawable.box17, R.layout.grid_2_6, 23, 1));
                gridArrayList.add(new Grid(R.drawable.box18, R.layout.grid_2_7, 23, 2));
                gridArrayList.add(new Grid(R.drawable.box19, R.layout.grid_2_8, 23, 2));
                gridArrayList.add(new Grid(R.drawable.box20, R.layout.grid_2_9, 23, 2));
                break;

            case 3:
                gridArrayList.add(new Grid(R.drawable.box21, R.layout.grid_3_1, 31, 1));
                gridArrayList.add(new Grid(R.drawable.box22, R.layout.grid_3_6, 36, 1));
                gridArrayList.add(new Grid(R.drawable.box23, R.layout.grid_3_2, 32, 1));
                gridArrayList.add(new Grid(R.drawable.box24, R.layout.grid_3_3, 33, 1));
                gridArrayList.add(new Grid(R.drawable.box25, R.layout.grid_3_4, 34, 1));
                gridArrayList.add(new Grid(R.drawable.box26, R.layout.grid_3_5, 35, 1));

                gridArrayList.add(new Grid(R.drawable.box27, R.layout.grid_3_7, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box28, R.layout.grid_3_8, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box29, R.layout.grid_3_9, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box30, R.layout.grid_3_10, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box31, R.layout.grid_3_11, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box32, R.layout.grid_3_12, 35, 1));
                gridArrayList.add(new Grid(R.drawable.box33, R.layout.grid_3_13, 35, 2));
                gridArrayList.add(new Grid(R.drawable.box34, R.layout.grid_3_14, 35, 2));
                break;

            case 4:
                gridArrayList.add(new Grid(R.drawable.box35, R.layout.grid_4_1, 41, 1));
                gridArrayList.add(new Grid(R.drawable.box36, R.layout.grid_4_2, 42, 1));
                gridArrayList.add(new Grid(R.drawable.box37, R.layout.grid_4_15, 415, 1));
                gridArrayList.add(new Grid(R.drawable.box38, R.layout.grid_4_14, 414, 1));
                gridArrayList.add(new Grid(R.drawable.box39, R.layout.grid_4_5, 45, 1));
                gridArrayList.add(new Grid(R.drawable.box40, R.layout.grid_4_3, 43, 1));
                gridArrayList.add(new Grid(R.drawable.box41, R.layout.grid_4_4, 44, 1));
                gridArrayList.add(new Grid(R.drawable.box42, R.layout.grid_4_6, 46, 1));
                gridArrayList.add(new Grid(R.drawable.box43, R.layout.grid_4_7, 47, 1));
                gridArrayList.add(new Grid(R.drawable.box44, R.layout.grid_4_8, 48, 1));
                gridArrayList.add(new Grid(R.drawable.box45, R.layout.grid_4_9, 49, 1));
                gridArrayList.add(new Grid(R.drawable.box46, R.layout.grid_4_10, 410, 1));
                gridArrayList.add(new Grid(R.drawable.box47, R.layout.grid_4_11, 411, 1));
                gridArrayList.add(new Grid(R.drawable.box48, R.layout.grid_4_12, 412, 1));
                gridArrayList.add(new Grid(R.drawable.box49, R.layout.grid_4_13, 413, 1));

                gridArrayList.add(new Grid(R.drawable.box50, R.layout.grid_4_16, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box51, R.layout.grid_4_17, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box52, R.layout.grid_4_18, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box53, R.layout.grid_4_19, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box54, R.layout.grid_4_20, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box55, R.layout.grid_4_21, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box56, R.layout.grid_4_22, 413, 1));
                gridArrayList.add(new Grid(R.drawable.box57, R.layout.grid_4_23, 413, 2));
                break;

            case 5:
                gridArrayList.add(new Grid(R.drawable.box58, R.layout.grid_5_1, 51, 1));
                gridArrayList.add(new Grid(R.drawable.box59, R.layout.grid_5_2, 52, 1));
                gridArrayList.add(new Grid(R.drawable.box60, R.layout.grid_5_24, 523, 2));
                gridArrayList.add(new Grid(R.drawable.box61, R.layout.grid_5_7, 57, 1));
                gridArrayList.add(new Grid(R.drawable.box62, R.layout.grid_5_6, 56, 1));
                gridArrayList.add(new Grid(R.drawable.box63, R.layout.grid_5_8, 58, 1));
                gridArrayList.add(new Grid(R.drawable.box64, R.layout.grid_5_9, 59, 1));
                gridArrayList.add(new Grid(R.drawable.box65, R.layout.grid_5_4, 54, 1));
                gridArrayList.add(new Grid(R.drawable.box66, R.layout.grid_5_5, 55, 1));
                gridArrayList.add(new Grid(R.drawable.box67, R.layout.grid_5_11, 511, 1));
                gridArrayList.add(new Grid(R.drawable.box68, R.layout.grid_5_3, 53, 1));
//                gridArrayList.add(new Grid(R.drawable.box46, R.layout.grid_5_10, 510, 1));
                gridArrayList.add(new Grid(R.drawable.box69, R.layout.grid_5_12, 512, 1));
                gridArrayList.add(new Grid(R.drawable.box70, R.layout.grid_5_13, 513, 1));
                gridArrayList.add(new Grid(R.drawable.box71, R.layout.grid_5_15, 515, 1));
                gridArrayList.add(new Grid(R.drawable.box72, R.layout.grid_5_14, 514, 1));
                gridArrayList.add(new Grid(R.drawable.box73, R.layout.grid_5_16, 516, 1));
                gridArrayList.add(new Grid(R.drawable.box74, R.layout.grid_5_17, 517, 1));
                gridArrayList.add(new Grid(R.drawable.box75, R.layout.grid_5_18, 518, 1));
                gridArrayList.add(new Grid(R.drawable.box76, R.layout.grid_5_19, 519, 1));
                gridArrayList.add(new Grid(R.drawable.box77, R.layout.grid_5_21, 521, 1));
                gridArrayList.add(new Grid(R.drawable.box78, R.layout.grid_5_20, 520, 1));
                gridArrayList.add(new Grid(R.drawable.box79, R.layout.grid_5_22, 522, 1));
                gridArrayList.add(new Grid(R.drawable.box80, R.layout.grid_5_23, 523, 1));

                gridArrayList.add(new Grid(R.drawable.box81, R.layout.grid_5_25, 523, 1));
                gridArrayList.add(new Grid(R.drawable.box82, R.layout.grid_5_26, 523, 1));
                gridArrayList.add(new Grid(R.drawable.box83, R.layout.grid_5_27, 523, 1));
                gridArrayList.add(new Grid(R.drawable.box84, R.layout.grid_5_28, 523, 1));
                gridArrayList.add(new Grid(R.drawable.box85, R.layout.grid_5_29, 523, 2));
                break;

            case 6:
                gridArrayList.add(new Grid(R.drawable.box86, R.layout.grid_6_1, 61, 1));
                gridArrayList.add(new Grid(R.drawable.box87, R.layout.grid_6_2, 62, 1));
                gridArrayList.add(new Grid(R.drawable.box88, R.layout.grid_6_4, 64, 1));
                gridArrayList.add(new Grid(R.drawable.box89, R.layout.grid_6_3, 63, 1));
                gridArrayList.add(new Grid(R.drawable.box90, R.layout.grid_6_5, 65, 1));
                gridArrayList.add(new Grid(R.drawable.box91, R.layout.grid_6_6, 66, 1));
                gridArrayList.add(new Grid(R.drawable.box92, R.layout.grid_6_7, 67, 1));
                gridArrayList.add(new Grid(R.drawable.box93, R.layout.grid_6_8, 68, 1));
                gridArrayList.add(new Grid(R.drawable.box94, R.layout.grid_6_9, 69, 1));
                gridArrayList.add(new Grid(R.drawable.box95, R.layout.grid_6_10, 610, 1));
                gridArrayList.add(new Grid(R.drawable.box96, R.layout.grid_6_11, 611, 1));
                gridArrayList.add(new Grid(R.drawable.box97, R.layout.grid_6_12, 612, 1));
                gridArrayList.add(new Grid(R.drawable.box98, R.layout.grid_6_13, 613, 1));
                gridArrayList.add(new Grid(R.drawable.box99, R.layout.grid_6_14, 614, 1));
                gridArrayList.add(new Grid(R.drawable.box100, R.layout.grid_6_15, 615, 1));
                gridArrayList.add(new Grid(R.drawable.box101, R.layout.grid_6_16, 616, 1));
                gridArrayList.add(new Grid(R.drawable.box102, R.layout.grid_6_17, 617, 1));
                gridArrayList.add(new Grid(R.drawable.box103, R.layout.grid_6_18, 618, 1));
                gridArrayList.add(new Grid(R.drawable.box104, R.layout.grid_6_19, 619, 1));
                gridArrayList.add(new Grid(R.drawable.box105, R.layout.grid_6_20, 620, 1));
                break;

            case 7:
                gridArrayList.add(new Grid(R.drawable.box106, R.layout.grid_7_1, 71, 1));
                gridArrayList.add(new Grid(R.drawable.box107, R.layout.grid_7_2, 72, 1));
                gridArrayList.add(new Grid(R.drawable.box108, R.layout.grid_7_4, 74, 1));
                gridArrayList.add(new Grid(R.drawable.box109, R.layout.grid_7_3, 73, 1));
                gridArrayList.add(new Grid(R.drawable.box110, R.layout.grid_7_5, 75, 1));
                gridArrayList.add(new Grid(R.drawable.box111, R.layout.grid_7_6, 76, 1));
                gridArrayList.add(new Grid(R.drawable.box112, R.layout.grid_7_7, 77, 1));
                gridArrayList.add(new Grid(R.drawable.box113, R.layout.grid_7_8, 78, 1));
                gridArrayList.add(new Grid(R.drawable.box114, R.layout.grid_7_9, 79, 1));
                gridArrayList.add(new Grid(R.drawable.box115, R.layout.grid_7_10, 710, 1));
                gridArrayList.add(new Grid(R.drawable.box116, R.layout.grid_7_11, 711, 1));
                gridArrayList.add(new Grid(R.drawable.box117, R.layout.grid_7_12, 712, 1));
                gridArrayList.add(new Grid(R.drawable.box118, R.layout.grid_7_13, 713, 1));
                gridArrayList.add(new Grid(R.drawable.box119, R.layout.grid_7_14, 714, 1));
                gridArrayList.add(new Grid(R.drawable.box120, R.layout.grid_7_15, 715, 1));
                gridArrayList.add(new Grid(R.drawable.box121, R.layout.grid_7_16, 716, 1));
                gridArrayList.add(new Grid(R.drawable.box122, R.layout.grid_7_17, 717, 1));
                gridArrayList.add(new Grid(R.drawable.box123, R.layout.grid_7_18, 718, 1));
                break;

            case 8:
                gridArrayList.add(new Grid(R.drawable.box124, R.layout.grid_8_1, 81, 1));
                gridArrayList.add(new Grid(R.drawable.box125, R.layout.grid_8_2, 82, 1));
                gridArrayList.add(new Grid(R.drawable.box126, R.layout.grid_8_3, 83, 1));
                gridArrayList.add(new Grid(R.drawable.box127, R.layout.grid_8_4, 84, 1));
                gridArrayList.add(new Grid(R.drawable.box128, R.layout.grid_8_5, 85, 1));
                gridArrayList.add(new Grid(R.drawable.box129, R.layout.grid_8_6, 86, 1));
                gridArrayList.add(new Grid(R.drawable.box130, R.layout.grid_8_7, 87, 1));
                gridArrayList.add(new Grid(R.drawable.box131, R.layout.grid_8_8, 88, 1));
                gridArrayList.add(new Grid(R.drawable.box132, R.layout.grid_8_9, 89, 1));
                gridArrayList.add(new Grid(R.drawable.box133, R.layout.grid_8_10, 810, 1));
                gridArrayList.add(new Grid(R.drawable.box134, R.layout.grid_8_11, 811, 1));
                gridArrayList.add(new Grid(R.drawable.box135, R.layout.grid_8_12, 812, 1));
                gridArrayList.add(new Grid(R.drawable.box136, R.layout.grid_8_13, 813, 1));
                break;

            case 9:
                gridArrayList.add(new Grid(R.drawable.box137, R.layout.grid_9_1, 91, 1));
                gridArrayList.add(new Grid(R.drawable.box138, R.layout.grid_9_2, 92, 1));
                gridArrayList.add(new Grid(R.drawable.box139, R.layout.grid_9_5, 95, 1));
                gridArrayList.add(new Grid(R.drawable.box140, R.layout.grid_9_3, 93, 1));
                gridArrayList.add(new Grid(R.drawable.box141, R.layout.grid_9_4, 94, 1));
                gridArrayList.add(new Grid(R.drawable.box142, R.layout.grid_9_6, 96, 1));
                gridArrayList.add(new Grid(R.drawable.box143, R.layout.grid_9_7, 97, 1));
                gridArrayList.add(new Grid(R.drawable.box144, R.layout.grid_9_8, 98, 1));
                gridArrayList.add(new Grid(R.drawable.box145, R.layout.grid_9_9, 98, 2));
                break;
        }
        return gridArrayList;
    }

    public static ArrayList<Margin> fetchMargins(int position) {
        int fullMargin = 6, halfMargin = 6;
        ArrayList<Margin> marginArrayList = new ArrayList<>();
        switch (position)
        {
            case 11:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 12:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 21:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 22:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 23:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 24:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 31:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 32:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 33:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 34:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 35:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 36:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 41:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 42:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 43:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                break;

            case 44:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 45:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 46:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 47:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 48:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 49:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 410:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 411:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 412:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 413:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 414:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 415:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 51:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 52:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 53:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 54:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 55:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 56:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 57:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 58:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 59:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 510:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 511:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;


            case 512:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 513:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 514:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 515:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 516:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 517:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 518:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 519:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 520:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 521:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 522:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 523:
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                break;

            case 61:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 62:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 63:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 64:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 65:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 66:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 67:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 68:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 69:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 610:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 611:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 612:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 613:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 614:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 615:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 616:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 617:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 618:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 619:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 620:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 71:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 72:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 73:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 74:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 75:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 76:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 77:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 78:
                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 79:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, fullMargin));
                break;

            case 710:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 711:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 712:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 713:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 714:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 715:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 716:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 717:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 718:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));

                break;

            case 81:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 82:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 83:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 84:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 85:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 86:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 87:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 88:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 89:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 810:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 811:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 812:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 813:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 91:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 92:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 93:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 94:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 95:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 96:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 97:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

            case 98:
                marginArrayList.add(new Margin(fullMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, halfMargin, halfMargin));
                marginArrayList.add(new Margin(fullMargin, halfMargin, halfMargin, fullMargin));
                marginArrayList.add(new Margin(halfMargin, fullMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, halfMargin));
                marginArrayList.add(new Margin(halfMargin, halfMargin, fullMargin, fullMargin));
                break;

//            case 91:
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                marginArrayList.add(new Margin(fullMargin, fullMargin, fullMargin, fullMargin));
//                break;
        }
        return marginArrayList;
    }

    public static ArrayList<Pattern> fetchMainPattern()
    {
        ArrayList<Pattern> patternArrayList = new ArrayList<>();

        patternArrayList.add(new Pattern("Gradient", R.drawable.pattern_icon1));
        patternArrayList.add(new Pattern("Halloween", R.drawable.pattern_icon2));
        patternArrayList.add(new Pattern("Kimo", R.drawable.pattern_icon3));
        patternArrayList.add(new Pattern("Love", R.drawable.pattern_icon4));
        patternArrayList.add(new Pattern("Mouth", R.drawable.pattern_icon5));
        patternArrayList.add(new Pattern("Rush", R.drawable.pattern_icon6));
        patternArrayList.add(new Pattern("Tile", R.drawable.pattern_icon7));
        patternArrayList.add(new Pattern("Triangle", R.drawable.pattern_icon8));
        patternArrayList.add(new Pattern("Texture", R.drawable.pattern_icon9));
        patternArrayList.add(new Pattern("Watercolor", R.drawable.pattern_icon10));

        return patternArrayList;
    }

    public static int[] fetchSubPattern(int position)
    {
        switch (position)
        {
            case 0:
                int[] patternResource0 = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
                        R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
                        R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16};
                return patternResource0;

            case 1:
                int[] patternResource1 = {R.drawable.p17,
                        R.drawable.p18, R.drawable.p19, R.drawable.p20, R.drawable.p21, R.drawable.p22, R.drawable.p23,
                        R.drawable.p24, R.drawable.p25, R.drawable.p26, R.drawable.p27};
                return patternResource1;

            case 2:
                int[] patternResource2 = {R.drawable.p28, R.drawable.p29, R.drawable.p30, R.drawable.p31, R.drawable.p32,
                        R.drawable.p33, R.drawable.p34, R.drawable.p35, R.drawable.p36};
                return patternResource2;

            case 3:
                int[] patternResource3 = {R.drawable.p37, R.drawable.p38,
                        R.drawable.p39, R.drawable.p40, R.drawable.p41, R.drawable.p42, R.drawable.p43, R.drawable.p44,
                        R.drawable.p45, R.drawable.p46, R.drawable.p47, R.drawable.p48};
                return patternResource3;

            case 4:
                int[] patternResource4 = {R.drawable.p49, R.drawable.p50,
                        R.drawable.p51, R.drawable.p52, R.drawable.p53, R.drawable.p54, R.drawable.p55, R.drawable.p56,
                        R.drawable.p57, R.drawable.p58, R.drawable.p59, R.drawable.p60, R.drawable.p61, R.drawable.p62,
                        R.drawable.p63, R.drawable.p64, R.drawable.p65, R.drawable.p66, R.drawable.p67};
                return patternResource4;

            case 5:
                int[] patternResource5 = {R.drawable.p68, R.drawable.p69, R.drawable.p70, R.drawable.p71, R.drawable.p72,
                        R.drawable.p73, R.drawable.p74, R.drawable.p75, R.drawable.p76, R.drawable.p77,
                        R.drawable.p78, R.drawable.p79, R.drawable.p80, R.drawable.p81, R.drawable.p82, R.drawable.p83};
                return patternResource5;

            case 6:
                int[] patternResource6 = {R.drawable.p84, R.drawable.p85, R.drawable.p86,
                        R.drawable.p87, R.drawable.p88, R.drawable.p89, R.drawable.p90, R.drawable.p91, R.drawable.p92,
                        R.drawable.p93, R.drawable.p94, R.drawable.p95, R.drawable.p96, R.drawable.p97, R.drawable.p98,
                        R.drawable.p99, R.drawable.p100, R.drawable.p101, R.drawable.p102, R.drawable.p103, R.drawable.p104};
                return patternResource6;

            case 7:
                int[] patternResource7 = {R.drawable.p105, R.drawable.p106, R.drawable.p107, R.drawable.p108, R.drawable.p109, R.drawable.p110, R.drawable.p111, R.drawable.p112};
                return patternResource7;

            case 8:
                int[] patternResource8 = {R.drawable.p113,
                        R.drawable.p114, R.drawable.p115, R.drawable.p116, R.drawable.p117, R.drawable.p118, R.drawable.p119,
                        R.drawable.p120, R.drawable.p121, R.drawable.p122, R.drawable.p123, R.drawable.p124, R.drawable.p125, R.drawable.p126, R.drawable.p127, R.drawable.p128,
                        R.drawable.p129, R.drawable.p130, R.drawable.p131, R.drawable.p132, R.drawable.p133, R.drawable.p134,
                        R.drawable.p135, R.drawable.p136};
                return patternResource8;

            case 9:
                int[] patternResource9 = {R.drawable.p137, R.drawable.p138, R.drawable.p139, R.drawable.p140,
                        R.drawable.p141, R.drawable.p142, R.drawable.p143, R.drawable.p144, R.drawable.p145, R.drawable.p146,
                        R.drawable.p147};
                return patternResource9;

            default:
                return null;
        }
    }

    public static ArrayList<Frame> fetchFrame()
    {
        ArrayList<Frame> frameArrayList = new ArrayList<>();
        frameArrayList.add(new Frame(R.drawable.f_icon_1, R.drawable.f_icon_1));
        frameArrayList.add(new Frame(R.drawable.f_icon_1, R.drawable.frame_1));
        frameArrayList.add(new Frame(R.drawable.f_icon_2, R.drawable.frame_2));
        frameArrayList.add(new Frame(R.drawable.f_icon_3, R.drawable.frame_3));
        frameArrayList.add(new Frame(R.drawable.f_icon_4, R.drawable.frame_4));
        frameArrayList.add(new Frame(R.drawable.f_icon_5, R.drawable.frame_5));
        frameArrayList.add(new Frame(R.drawable.f_icon_6, R.drawable.frame_6));
        frameArrayList.add(new Frame(R.drawable.f_icon_7, R.drawable.frame_7));
        frameArrayList.add(new Frame(R.drawable.f_icon_8, R.drawable.frame_8));
        frameArrayList.add(new Frame(R.drawable.f_icon_9, R.drawable.frame_9));
        frameArrayList.add(new Frame(R.drawable.f_icon_10, R.drawable.frame_10));
        frameArrayList.add(new Frame(R.drawable.f_icon_11, R.drawable.frame_11));
        frameArrayList.add(new Frame(R.drawable.f_icon_12, R.drawable.frame_12));
        frameArrayList.add(new Frame(R.drawable.f_icon_13, R.drawable.frame_13));
        frameArrayList.add(new Frame(R.drawable.f_icon_14, R.drawable.frame_14));
        frameArrayList.add(new Frame(R.drawable.f_icon_15, R.drawable.frame_15));
        frameArrayList.add(new Frame(R.drawable.f_icon_16, R.drawable.frame_16));
        frameArrayList.add(new Frame(R.drawable.f_icon_17, R.drawable.frame_17));
        frameArrayList.add(new Frame(R.drawable.f_icon_18, R.drawable.frame_18));
        frameArrayList.add(new Frame(R.drawable.f_icon_19, R.drawable.frame_19));
        frameArrayList.add(new Frame(R.drawable.f_icon_20, R.drawable.frame_20));
        frameArrayList.add(new Frame(R.drawable.f_icon_21, R.drawable.frame_21));
        frameArrayList.add(new Frame(R.drawable.f_icon_22, R.drawable.frame_22));
        frameArrayList.add(new Frame(R.drawable.f_icon_23, R.drawable.frame_23));
        frameArrayList.add(new Frame(R.drawable.f_icon_24, R.drawable.frame_24));
        frameArrayList.add(new Frame(R.drawable.f_icon_25, R.drawable.frame_25));
        frameArrayList.add(new Frame(R.drawable.f_icon_26, R.drawable.frame_26));
        frameArrayList.add(new Frame(R.drawable.f_icon_27, R.drawable.frame_27));
        frameArrayList.add(new Frame(R.drawable.f_icon_28, R.drawable.frame_28));
        frameArrayList.add(new Frame(R.drawable.f_icon_29, R.drawable.frame_29));
        frameArrayList.add(new Frame(R.drawable.f_icon_30, R.drawable.frame_30));
        frameArrayList.add(new Frame(R.drawable.f_icon_31, R.drawable.frame_31));
        frameArrayList.add(new Frame(R.drawable.f_icon_32, R.drawable.frame_32));
        frameArrayList.add(new Frame(R.drawable.f_icon_33, R.drawable.frame_33));
        frameArrayList.add(new Frame(R.drawable.f_icon_34, R.drawable.frame_34));
        frameArrayList.add(new Frame(R.drawable.f_icon_35, R.drawable.frame_35));
        frameArrayList.add(new Frame(R.drawable.f_icon_36, R.drawable.frame_36));
        frameArrayList.add(new Frame(R.drawable.f_icon_37, R.drawable.frame_37));
        frameArrayList.add(new Frame(R.drawable.f_icon_38, R.drawable.frame_38));
        frameArrayList.add(new Frame(R.drawable.f_icon_39, R.drawable.frame_39));
        frameArrayList.add(new Frame(R.drawable.f_icon_40, R.drawable.frame_40));
        return frameArrayList;
    }

    public static ArrayList<Ratio> fetchRatio() {
        ArrayList<Ratio> ratioArrayList = new ArrayList<>();
        ratioArrayList.add(new Ratio(R.drawable.crop_1_1_btn, "1:1"));
        ratioArrayList.add(new Ratio(R.drawable.crop_3_2_btn, "3:2"));
        ratioArrayList.add(new Ratio(R.drawable.crop_2_3_btn, "2:3"));
        ratioArrayList.add(new Ratio(R.drawable.crop_4_3_btn, "4:3"));
        ratioArrayList.add(new Ratio(R.drawable.crop_3_4_btn, "3:4"));
        ratioArrayList.add(new Ratio(R.drawable.crop_16_9_btn, "16:9"));
        ratioArrayList.add(new Ratio(R.drawable.crop_9_16_btn, "9:16"));
        return ratioArrayList;
    }

    public static ArrayList<Crop> fetchCrop()
    {
        ArrayList<Crop> cropArrayList = new ArrayList<>();
        cropArrayList.add(new Crop(R.drawable.crop_original_btn, "Original", 10, 10));
        cropArrayList.add(new Crop(R.drawable.crop_1_1_btn, "1:1", 1, 1));
        cropArrayList.add(new Crop(R.drawable.crop_5_4_btn, "5:4", 5, 4));
        cropArrayList.add(new Crop(R.drawable.crop_4_5_btn, "4:5", 4, 5));
        cropArrayList.add(new Crop(R.drawable.crop_4_3_btn, "4:3", 4, 3));
        cropArrayList.add(new Crop(R.drawable.crop_3_4_btn, "3:4", 3, 4));
        cropArrayList.add(new Crop(R.drawable.crop_3_2_btn, "3:2", 3, 2));
        cropArrayList.add(new Crop(R.drawable.crop_2_3_btn, "2:3", 2, 3));
        cropArrayList.add(new Crop(R.drawable.crop_16_9_btn, "16:9", 16, 9));
        cropArrayList.add(new Crop(R.drawable.crop_9_16_btn, "9:16", 9, 16));
        return cropArrayList;
    }

    public static ArrayList<StickerData> fetchStickers(Context context)
    {
        ArrayList<StickerData> stickerDataArrayList = new ArrayList<>();

        for(int j=0; j<stickerFolderName().size(); j++)
        {
            String dirFrom = stickerFolderName().get(j);
            try {
                String[] fileList = context.getResources().getAssets().list(dirFrom);
                if (fileList != null) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].contains(".png")) {
                            String path = dirFrom + File.separator + fileList[i];
                            stickerDataArrayList.add(new StickerData(path, false));
                        }
                    }
                }
            }
            catch (Exception e){}
        }

        return stickerDataArrayList;
    }

    public static void setNotSelectedSticker()
    {
        for (int i=0; i<stickerArrayList.size(); i++)
        {
            stickerArrayList.set(i, new StickerData(stickerArrayList.get(i).getName(), false));
        }
    }

    public static ArrayList<String> stickerFolderName()
    {
        ArrayList<String> stringArrayList = new ArrayList<>();

        stringArrayList.add("emoji");
        stringArrayList.add("cartoon");
        stringArrayList.add("christmas");
        stringArrayList.add("cute");
        stringArrayList.add("fresh");
        stringArrayList.add("funny");
        stringArrayList.add("love");
        stringArrayList.add("makeup");
        stringArrayList.add("newyear");
        stringArrayList.add("snap");
        stringArrayList.add("summer");
        stringArrayList.add("writing");

        return stringArrayList;
    }

    public static ArrayList<String> listFilesAsset(String dirFrom, Context context) throws IOException {
        String[] fileList = context.getResources().getAssets().list(dirFrom);
        ArrayList<String> gridViewItems = new ArrayList();
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].contains(".png")) {
                    gridViewItems.add(dirFrom + File.separator + fileList[i]);
                }
            }
        }
        return gridViewItems;
    }

}
