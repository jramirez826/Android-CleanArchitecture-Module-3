package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter.PicturesAdapter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.dialog.PictureDialog;
import com.globant.equattrocchio.cleanarchitecture.util.Constants;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceItemObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.GetImageClickedUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageClickedUseCase getImageClickedUseCase;


    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase, GetImageClickedUseCase getImageClickedUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getImageClickedUseCase = getImageClickedUseCase;
    }

    public void onCountButtonPressed() {

        view.showText(new String(""));//todo: aca va el string que me devuelva el execute del usecase


    }

    private void onCallImageClicked(Integer id) {
        getImageClickedUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object o) {
                Log.d("asd", String.valueOf(((Image)o).getId()));
                PictureDialog dialog = new PictureDialog();
                Bundle args = new Bundle();
                args.putParcelable(Constants.BUNDLE_IMAGES, (Parcelable) o);
                dialog.setArguments(args);
                dialog.show(view.getFragmentManager(), null);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        }, id);
    }

    private void onCallServiceButtonPressed() {
        getLatestImagesUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object o) {
                loadFromPreferences();
                view.showText(new Gson().toJson(o));
                Result result = (Result) o;
                view.setPicturesList(result.getImages());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getLatestImages(this);
            }
        }, null);

        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    private void loadFromPreferences() {
        // view.showText("EL TEXTO QUE ME TRAGIA DE LAS PREFERENCES");// todo: traerme el texto de las preferences
    }


    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

        RxBus.subscribe(activity, new CallServiceItemObserver() {
            @Override
            public void onEvent(CallServiceItemClicked value) {
                onCallImageClicked(value.pictureId);
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
