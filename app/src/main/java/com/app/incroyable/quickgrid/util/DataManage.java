package com.app.incroyable.quickgrid.util;

import android.content.Context;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Grid3D;

import java.util.ArrayList;
import java.util.List;

import static com.app.incroyable.quickgrid.util.DataBinder.fetchGrid3dData;

public class DataManage {

    private static DataManage manage;
    protected Context context;
    private List<Grid3D> grid3DArrayList;

    public static DataManage getSingletManager(Context context) {
        if (manage == null) {
            manage = new DataManage(context);
        }
        return manage;
    }

    protected DataManage(Context context) {
        this.context = context;
        this.grid3DArrayList = new ArrayList();

        grid3DArrayList = fetchGrid3dData();
    }

    public Grid3D getPuzzleRes(int id) {
        return (Grid3D) this.grid3DArrayList.get(id);
    }

    public int getCount() {
        return this.grid3DArrayList.size();
    }

}
