package com.app.mylib.scrollgalleryview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.mylib.R;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class ImageFragment extends Fragment {

    private MediaInfo mMediaInfo;

    private HackyViewPager viewPager;
    private ImageView backgroundImage;
    private PhotoViewAttacher photoViewAttacher;

    public void setMediaInfo(MediaInfo mediaInfo) {
        mMediaInfo = mediaInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        backgroundImage = (ImageView) rootView.findViewById(R.id.backgroundImage);
        viewPager = (HackyViewPager) getActivity().findViewById(R.id.viewPager);

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(Const.IS_LOCKED, false);
            viewPager.setLocked(isLocked);
            backgroundImage.setImageBitmap((Bitmap) savedInstanceState.getParcelable(Const.IMAGE));
            createViewAttacher(savedInstanceState);
        }

        loadImageToView();

        return rootView;
    }

    private void loadImageToView() {
        if (mMediaInfo != null) {
            mMediaInfo.getLoader().loadMedia(getActivity(), backgroundImage, new MediaLoader.SuccessCallback() {
                @Override
                public void onSuccess() {
                    createViewAttacher(getArguments());
                }
            });
        }
    }

    private void createViewAttacher(Bundle savedInstanceState) {
        if (savedInstanceState.getBoolean(Const.ZOOM)) {
            photoViewAttacher = new PhotoViewAttacher(backgroundImage);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(Const.IS_LOCKED, viewPager.isLocked());
        }
        outState.putParcelable(Const.IMAGE, ((BitmapDrawable) backgroundImage.getDrawable()).getBitmap());
        outState.putBoolean(Const.ZOOM, photoViewAttacher != null);
        super.onSaveInstanceState(outState);
    }

    private boolean isViewPagerActive() {
        return viewPager != null;
    }
}
