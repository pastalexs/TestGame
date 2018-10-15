package com.test.testgame.ui.main.scene;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.test.testgame.model.Personal;

public class PersonalContract {
    interface View extends MvpView {
        void showAttack();
        void showProtection();
        void showSelectClass();
        void setPersonal(Personal personal);

    }
    interface UserActionsListener extends MvpPresenter<PersonalContract.View> {
        void attackWarrion();
        void myProtection();
        void getPersonal(Personal.Pclass pclass);

    }
}
