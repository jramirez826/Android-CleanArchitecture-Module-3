package com.globant.equattrocchio.domain.service;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<Object> observer);

    void getImageClicked(Observer<Object> observer, Integer id);
}
