package com.test.testgame.ui.main;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public class MainContract {
    interface View extends MvpView {

    }

    interface UserActionsListener extends MvpPresenter<MainContract.View> {
        public void setNamePersonal1(String name);

        public void setNamePersonal2(String name);
    }
}
