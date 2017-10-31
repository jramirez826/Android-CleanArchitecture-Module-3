package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CallServiceItemObserver extends BusObserver<CallServiceItemObserver.CallServiceItemClicked> {
    public CallServiceItemObserver() {
        super(CallServiceItemClicked.class);
    }

    public static class CallServiceItemClicked {
        public Integer pictureId;
    }
}
