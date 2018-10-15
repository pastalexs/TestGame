package com.test.testgame.ui.main.scene;

import com.test.testgame.RxMvpPresenter;
import com.test.testgame.model.CreaterUnits;
import com.test.testgame.model.Personal;

public class PersonalPresenter extends RxMvpPresenter<PersonalContract.View>
        implements PersonalContract.UserActionsListener {
    private Personal mWarrion;

    public PersonalPresenter() {
    }

    @Override
    public void attackWarrion() {

    }

    @Override
    public void myProtection() {

    }

    @Override
    public void getPersonal(Personal.Pclass pclass) {
        switch (pclass) {
            case PAPER:
                mWarrion = CreaterUnits.getPaper();
                break;
            case ROCK:
                mWarrion = CreaterUnits.getRock();
                break;
            case SCISSORS:
                mWarrion = CreaterUnits.getScissors();
                break;
        }
        getView().setPersonal(mWarrion);
    }
}
