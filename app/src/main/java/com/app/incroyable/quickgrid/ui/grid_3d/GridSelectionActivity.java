package com.app.incroyable.quickgrid.ui.grid_3d;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.MenuItem;
import android.widget.TextView;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.adapter.GridImageAdapter;
import com.app.incroyable.quickgrid.model.Grid3D;
import com.app.incroyable.quickgrid.util.TabStripView;

public class GridSelectionActivity extends AppCompatActivity{

    Activity activity = GridSelectionActivity.this;

    RecyclerView recyclerView;
    GridImageAdapter gridImageAdapter;
    StaggeredGridLayoutManager layoutManager;
    boolean selectFlag;
    int showImageNumber;
    TabStripView tabStripView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_selection);

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
        textView.setText(getResources().getString(R.string.GridSelectionActivity_Title));
    }

    private void bindControls() {
        tabStripView = (TabStripView) findViewById(R.id.tabStripView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);
        gridImageAdapter = new GridImageAdapter(activity);
        recyclerView.setAdapter(gridImageAdapter);
        tabStripView.setListener(new tabStrip());
        gridImageAdapter.setShowItemPositionListener(new showItem());
        recyclerView.addOnScrollListener(new scroll());
    }

    class tabStrip implements TabStripView.ClickTabListener {
        int[] positions;

        tabStrip() {
            this.positions = new int[]{1, 14, 21, 34, 42};
        }

        public void onClick(int number) {
            selectFlag = true;
            if (number != 1) {
                int position = this.positions[number - 1];
                if (showImageNumber > number) {
                    layoutManager.smoothScrollToPosition(recyclerView, null, position);
                } else {
                    layoutManager.smoothScrollToPosition(recyclerView, null, position + 3);
                }
            } else {
                layoutManager.smoothScrollToPosition(recyclerView, null, 0);
            }
            showImageNumber = number;
        }
    }

    class showItem implements GridImageAdapter.ShowItemPositionListener {

        public void onShowPuzzleRes(Grid3D res) {
            if (res != null && !selectFlag) {
                int number = res.getImageNumber();
                if (showImageNumber != number) {
                    showImageNumber = number;
                    tabStripView.selectedNumber(showImageNumber);
                }
            }
        }
    }

    class scroll extends RecyclerView.OnScrollListener {
        private int offset;

        scroll() {
            this.offset = 0;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            this.offset = recyclerView.computeVerticalScrollOffset();
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (selectFlag && newState == 0) {
                selectFlag = false;
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
}
