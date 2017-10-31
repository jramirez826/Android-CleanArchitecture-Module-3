package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceItemObserver;
import com.globant.equattrocchio.data.response.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceItemObserver.*;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PicturesViewHolder> {

    private List<Image> mImages;
    private Context mContext;

    public PicturesAdapter(Context mContext) {
        this.mContext = mContext;
        mImages = new ArrayList<>();
    }

    public void setImages(List<Image> mImages) {
        this.mImages = mImages;
        notifyDataSetChanged();
    }

    @Override
    public PicturesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pictures, parent, false);
        return new PicturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PicturesViewHolder holder, int position) {
        final Image image = mImages.get(position);
        holder.labPrictureId.setText(String.valueOf(image.getId()));
        Glide.with(mContext).load(image.getUrl()).into(holder.imgPicture);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallServiceItemClicked callServiceItemClicked = new CallServiceItemClicked();
                callServiceItemClicked.pictureId = image.getId();
                RxBus.post(callServiceItemClicked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class PicturesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_picture) ImageView imgPicture;
        @BindView(R.id.label_picture_id) TextView labPrictureId;

        public PicturesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}