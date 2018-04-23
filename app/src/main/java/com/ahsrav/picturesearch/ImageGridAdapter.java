package com.ahsrav.picturesearch;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahsrav.picturesearch.models.ImageList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;

class ImageGridAdapter extends RecyclerView.Adapter {
    private ImageList imageList;

    public ImageGridAdapter(ImageList imageList) {
        this.imageList = imageList;
    }


    // This creates viewholders for items that it can't use a recycled view for
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_image_layout, parent, false);

        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;
    }

    // This is called for every item in the grid. position indicates which-th item in the list it is.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String url = imageList.hits.get(position).previewURL;
        Uri uri = Uri.parse(url);
        ((ImageViewHolder) holder).imageView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return imageList.hits.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fresco_image);
        }
    }
}
