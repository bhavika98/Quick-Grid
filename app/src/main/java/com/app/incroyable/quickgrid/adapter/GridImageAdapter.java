package com.app.incroyable.quickgrid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.Grid3D;
import com.app.mylib.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.app.mylib.sectionedrecyclerview.SectionedViewHolder;
import com.app.incroyable.quickgrid.ui.grid_3d.ImageSelectionActivity;
import com.app.incroyable.quickgrid.util.Constants;
import com.app.incroyable.quickgrid.util.DataManage;

import java.util.ArrayList;
import java.util.List;

public class GridImageAdapter extends SectionedRecyclerViewAdapter<GridImageAdapter.MainVH> {

    Activity activity;
    ArrayList<Integer> sectionPositions;
    private List<MainVH> holderList;
    private DataManage puzzleManage;
    private ShowItemPositionListener showItemPositionListener;

    public GridImageAdapter(Activity activity) {
        this.activity = activity;
        this.holderList = new ArrayList();
        this.puzzleManage = DataManage.getSingletManager(activity);
    }

    public void onViewAttachedToWindow(MainVH holder) {
        super.onViewAttachedToWindow(holder);
        if (this.showItemPositionListener != null) {
            this.showItemPositionListener.onShowPuzzleRes(holder.res);
        }
    }

    public interface ShowItemPositionListener {
        void onShowPuzzleRes(Grid3D grid3D);
    }

    public void setShowItemPositionListener(ShowItemPositionListener showItemPositionListener) {
        this.showItemPositionListener = showItemPositionListener;
    }

    @Override
    public int getSectionCount() {
        return 5;
    }

    @Override
    public int getItemCount(int section) {
        return switch (section) {
            case 0 -> 12;
            case 1 -> 7;
            case 2 -> 13;
            case 3 -> 8;
            case 4 -> 5;
            default -> 0;
        };
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section, boolean expanded) {
        String string = (section + 1) + " Frame";
        holder.title.setText(string);
    }

    @Override
    public void onBindFooterViewHolder(MainVH holder, int section) {

    }

    @Override
    public void onBindViewHolder(MainVH holder, final int section, final int relativePosition, final int position) {

        final Grid3D res = this.puzzleManage.getPuzzleRes(position);
        holder.imageView.setImageResource(res.getThumb());
        holder.res = res;

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, ImageSelectionActivity.class);
                intent.putExtra(Constants.currentGrid, res.getId());
                intent.putExtra(Constants.imgId, String.valueOf(res.getImageNumber()));
                intent.putExtra(Constants.imgSource, String.valueOf(res.getLayout()));
                intent.putExtra(Constants.sizeGrid, String.valueOf(res.getSize()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if (section == 1)
            return 0; // VIEW_TYPE_HEADER is -2, VIEW_TYPE_ITEM is -1. You can return 0 or greater.
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutRes;
        switch(viewType) {
            case VIEW_TYPE_HEADER:
                layoutRes = R.layout.adapter_grid_header;
                break;
            case VIEW_TYPE_ITEM:
                layoutRes = R.layout.adapter_grid_image;
                break;
            default:
                layoutRes = R.layout.adapter_grid_image;
                break;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutRes, parent, false);
        return new MainVH(v);
    }

    public static class MainVH extends SectionedViewHolder {

        TextView title;
        ImageView imageView;
        public Grid3D res;

        public MainVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}