package com.globant.equattrocchio.cleanarchitecture.mvp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.Constants;
import com.globant.equattrocchio.data.response.Image;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureDialog extends DialogFragment {

    private Image image;
    @BindView(R.id.image_picture) ImageView imagePicture;
    @BindView(R.id.label_picture_detail) TextView labelPictureDetail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getParcelable(Constants.BUNDLE_IMAGES);
        }
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_picture, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getActivity())
                .load(image.getLargeUrl())
                .into(imagePicture);
        labelPictureDetail.setText("Site: " + String.valueOf(image.getSite()));
        return view;
    }
}
