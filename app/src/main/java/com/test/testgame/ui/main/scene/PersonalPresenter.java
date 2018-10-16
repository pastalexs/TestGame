package com.test.testgame.ui.main.scene;

import android.content.res.Resources;

import com.test.testgame.R;
import com.test.testgame.RxMvpPresenter;
import com.test.testgame.model.Arena;
import com.test.testgame.model.Personal;
import com.test.testgame.model.Warrion;

public class PersonalPresenter extends RxMvpPresenter<PersonalContract.View>
        implements PersonalContract.UserActionsListener {
    private Arena arena;
    private Warrion mWarrion;

    public PersonalPresenter() {
        arena = Arena.getInstance();
    }

    @Override
    public void attackWarrion() {
        arena.warrionAttack(mWarrion.getName(), mWarrion.getAttack());
        if (getView() != null) {
            getView().showCommandText(R.string.attack,String.valueOf(mWarrion.getAttack()));
        }
    }

    @Override
    public void protectionWarrion() {
        arena.warrionProtect(mWarrion.getName(), mWarrion.getProtect());
        if (getView() != null) {
            getView().showCommandText(R.string.propertis,String.valueOf(mWarrion.getProtect()));
        }
    }

    @Override
    public Warrion getWarrion(String name) {
        mWarrion = arena.getWarrion(name);
        return mWarrion;
    }

    @Override
    public void getPersonal(Personal.Pclass pclass) {
        mWarrion.selectedClassUnit(pclass);
        if (getView() != null) {
            getView().showCommandText(R.string.SelectUnit,mWarrion.getCurrenciWarrion().getPclass().toString());
        }
        getView().setPersonal();
    }
}
