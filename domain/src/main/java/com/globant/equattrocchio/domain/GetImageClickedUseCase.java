package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageClickedUseCase extends UseCase<Object, Integer> {

    private ImagesServices imagesServices;

    public GetImageClickedUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, Integer integer) {
        imagesServices.getImageClicked(observer, integer);
    }
}
