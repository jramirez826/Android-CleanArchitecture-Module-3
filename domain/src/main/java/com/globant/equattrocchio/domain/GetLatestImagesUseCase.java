package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<Object,Void> {

    private ImagesServices imagesServices;

    public GetLatestImagesUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, Void aVoid) {
        imagesServices.getLatestImages(observer);
    }
}
