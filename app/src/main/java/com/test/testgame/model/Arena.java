package com.test.testgame.model;

import android.content.res.Resources;

import com.test.testgame.R;
import com.test.testgame.model.event.FinishGame;

import org.greenrobot.eventbus.EventBus;

public class Arena {
    private static Arena arena;
    private static Warrion warrionLeft;
    private static Warrion warrionRight;

    private Arena(String warrionLeft, Personal.Pclass left
            , String warriomRight, Personal.Pclass right) {
        this.warrionRight = new Warrion(warriomRight, right);
        this.warrionLeft = new Warrion(warrionLeft, left);
    }

    public static Arena getInstance(String warrionLeft, Personal.Pclass left
            , String warriomRight, Personal.Pclass right) {
        if (arena == null) {
            arena = new Arena(warrionLeft, left, warriomRight, right);
        }
        return arena;
    }

    public static void deletInstance() {
        if (arena != null) {
            arena = null;
        }
    }

    public static Arena getInstance() {
        return arena;
    }

    public Warrion getWarrion(String name) {
        if (warrionRight.getName().equals(name)) {
            return warrionRight;
        } else {
            if (warrionLeft.getName().equals(name)) {
                return warrionLeft;
            }
        }
        return null;
    }

    public Personal getSelectPerson(String name, Personal.Pclass pclass) {
        return getWarrion(name).selectedClassUnit(pclass);
    }

    public boolean warrionAttack(String name, int attack) {
        Warrion warrion = getWarrion(name);
        if (warrion.fcommand) {
            warrion.command = attack;
            warrion.fcommand = false;
            return true;
        }
        return false;
    }

    public boolean warrionProtect(String name, int protect) {
        Warrion warrion = getWarrion(name);
        if (warrion.command == 0) {
            warrion.command = -protect;
            warrion.fcommand = false;
            return true;
        }
        return false;
    }

    public static void fighting() {
        if (!warrionLeft.fcommand &&
                !warrionRight.fcommand) {
            if (warrionRight.command > 0 &&
                    warrionLeft.command > 0) {
                warrionLeft.lessHP(warrionRight.command);
                warrionRight.lessHP(warrionLeft.command);
            } else if (warrionRight.command > 0 &&
                    warrionLeft.command < 0) {
                int attack = warrionRight.command + warrionLeft.command;
                if (attack > 0) {
                    warrionLeft.lessHP(attack);
                }
            } else if (warrionRight.command < 0 &&
                    warrionLeft.command > 0) {
                int attack = warrionRight.command + warrionLeft.command;
                if (attack > 0) {
                    warrionRight.lessHP(attack);
                }
            }
            warrionLeft.command = 0;
            warrionRight.command = 0;
            warrionLeft.fcommand = true;
            warrionRight.fcommand = true;
            checkWinner();
        }
    }

    public static void checkWinner() {
        boolean right = warrionRight.getCurrenciWarrion().isfLive();
        boolean left = warrionLeft.getCurrenciWarrion().isfLive();
        if (!right && !left) {
            EventBus.getDefault().post(new FinishGame(R.string.Draw, ""));
        } else {
            if (!right) {
                EventBus.getDefault().post(new FinishGame(R.string.WinnerIs, warrionLeft.getName()));
            } else if (!left) {
                EventBus.getDefault().post(new FinishGame(R.string.WinnerIs, warrionRight.getName()));
            }
        }
    }
}
