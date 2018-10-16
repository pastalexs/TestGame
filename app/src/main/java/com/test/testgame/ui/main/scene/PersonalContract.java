package com.test.testgame.ui.main.scene;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.test.testgame.model.Personal;
import com.test.testgame.model.Warrion;

public class PersonalContract {
    interface View extends MvpView {
        void showCommandText(int id,String text);
        void setPersonal();

    }
    interface UserActionsListener extends MvpPresenter<PersonalContract.View> {
        void attackWarrion();
        void protectionWarrion();
        Warrion getWarrion(String name);
        void getPersonal(Personal.Pclass pclass);

    }
}
