package com.ahaohuo.api.retrofit;

import rx.Subscriber;

/**
 * Author jzy
 * Date 2016/8/30
 */

public abstract class RxSubscribe<T> extends Subscriber<T> {

    public RxSubscribe() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onCompleted() {

    }


}
