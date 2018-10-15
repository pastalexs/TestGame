package com.test.testgame.model;

public class CreaterUnits {
    public static Personal getRock(){
        Personal warrion = new Personal(200,15,5,"",Personal.Pclass.ROCK);
        return warrion;
    }
    public static Personal getScissors(){
        Personal warrion = new Personal(50,3,13,"",Personal.Pclass.SCISSORS);
        return warrion;
    }
    public static Personal getPaper(){
        Personal warrion = new Personal(100,10,8,"",Personal.Pclass.PAPER);
        return warrion;
    }
}
