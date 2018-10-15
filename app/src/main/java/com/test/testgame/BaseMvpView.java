package com.test.testgame;

import com.hannesdorfmann.mosby3.mvp.MvpView;


public interface BaseMvpView extends MvpView {

    void onSessionExpired();

    void showInternalError();

    void showInternalServerError();

    void showLoading();

    void showServerIsUnreachable();
}
