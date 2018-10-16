package com.test.testgame.model;

public class CreaterUnits {
    public static Personal getRock() {
        return new Personal(200, 15, 5, "", Personal.Pclass.ROCK);
    }

    public static Personal getScissors() {
        return new Personal(50, 13, 3, "", Personal.Pclass.SCISSORS);
    }

    public static Personal getPaper() {
        return new Personal(100, 10, 8, "", Personal.Pclass.PAPER);
    }

    public static Personal getRockLevel(int level) {
        return getWarrionLevel(level, getRock());
    }

    public static Personal getScissorsLevel(int level) {
        return getWarrionLevel(level, getScissors());
    }

    public static Personal getPaperLevel(int level) {
        return getWarrionLevel(level, getPaper());
    }

    private static Personal getWarrionLevel(int level, Personal warrior) {
        for (int i = 0; i < level; i++) {
            warrior.lvlUp();
        }
        return warrior;
    }
}
