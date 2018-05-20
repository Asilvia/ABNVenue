package com.abn.asilvia.abnvenue.ui.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.ui.main.MainAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by asilvia on 20/05/2018.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    ArrayList<String> photosList;
    Context context;
    public PhotosAdapter(Context context) {
        photosList = new ArrayList<>();
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_list_item, parent, false);
        PhotosAdapter.ViewHolder viewHolder = new PhotosAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Timber.d(" ===== " + photosList.get(position));

        Glide
                .with(context)
                .load( photosList.get(position))
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        )
                        .into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public void setPhotosList(ArrayList<String> photosUrl)
    {
        photosList.addAll(photosUrl);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView photo;

        public ViewHolder(View view) {
            super(view);
            photo =(ImageView) view.findViewById(R.id.ivPhoto);
        }

    }
}
