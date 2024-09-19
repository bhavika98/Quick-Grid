package com.app.incroyable.quickgrid.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.incroyable.quickgrid.R;
import com.app.incroyable.quickgrid.model.StickerData;
import com.app.incroyable.quickgrid.ui.grid.GridActivity;
import com.app.incroyable.quickgrid.ui.grid_3d.GridEditActivity;
import com.app.incroyable.quickgrid.ui.singlepic.SinglePicActivity;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.app.incroyable.quickgrid.ui.MainActivity.stickerArrayList;
import static com.app.incroyable.quickgrid.ui.MainActivity.stickerValue;
import static com.app.incroyable.quickgrid.util.DataBinder.listFilesAsset;
import static com.app.incroyable.quickgrid.util.DataBinder.stickerFolderName;

public class Fragment_Sticker extends Fragment {

    String name = "";
    GridView gridView;
    ArrayList<String> stringArrayList = new ArrayList<>();
    StickerAdapter stickerAdapter;
    ArrayList<String> selectedStrings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());

        gridView = (GridView) view.findViewById(R.id.gridView);
        name = stickerFolderName().get(position);
        new fetchSticker().execute();

        selectedStrings = new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                for (StickerData stickerData : stickerArrayList) {
                    if (stickerData.getName().equals(stringArrayList.get(position))) {
                        if (stickerData.isSelected()) {
                            stickerData.setSelected(false);
                            stickerValue--;

                            if (getActivity() instanceof GridEditActivity)
                                ((GridEditActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));
                            else if (getActivity() instanceof GridActivity)
                                ((GridActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));
                            else if (getActivity() instanceof SinglePicActivity)
                                ((SinglePicActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));

                            stickerAdapter.notifyDataSetChanged();
                        } else {
                            if (stickerValue == 10) {
                                Toast.makeText(getActivity(), "Can't select more than 10 sticker.", Toast.LENGTH_SHORT).show();
                            } else {
                                stickerData.setSelected(true);
                                stickerValue++;

                                if (getActivity() instanceof GridEditActivity)
                                    ((GridEditActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));
                                else if (getActivity() instanceof GridActivity)
                                    ((GridActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));
                                else if (getActivity() instanceof SinglePicActivity)
                                    ((SinglePicActivity) getActivity()).stickerCounting(String.valueOf(stickerValue));

                                stickerAdapter.notifyDataSetChanged();
                            }
                        }
                        break;
                    }
                }
            }
        });
    }

    public class StickerAdapter extends BaseAdapter {

        ArrayList<String> stickers;
        LayoutInflater inflater;
        Context contxt;
        List<Integer> selectedPositions;

        public StickerAdapter(Context contxt, ArrayList<String> imageid) {
            stickers = imageid;
            inflater = LayoutInflater.from(contxt);
            this.contxt = contxt;
            selectedPositions = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return stickers.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class MyViewHolder {
            ImageView imageView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = inflater.inflate(R.layout.adapter_sticker, parent, false);

            final MyViewHolder holder = new MyViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.sticker_img);

            for (StickerData stickerData : stickerArrayList)
            {
                if (stickerData.getName().equals(stickers.get(position))) {
                    if (stickerData.isSelected())
                        holder.imageView.setBackgroundResource(R.color.Sticker_Selected_Color);
                    else
                        holder.imageView.setBackgroundResource(R.color.Sticker_BG_Color);
                    break;
                }
            }

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(contxt.getAssets().open(stickers.get(position)));
                holder.imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return convertView;
        }
    }

    public class fetchSticker extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                try {
                    stringArrayList = listFilesAsset(name, getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            stringArrayList.clear();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            stickerAdapter = new StickerAdapter(getActivity(), stringArrayList);
            gridView.setAdapter(stickerAdapter);
            super.onPostExecute(aVoid);
        }

    }
}

