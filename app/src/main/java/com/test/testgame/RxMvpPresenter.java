package com.test.testgame;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public class RxMvpPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @CallSuper
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}