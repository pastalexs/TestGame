package com.test.testgame.model;

public class Warrion {
    private Personal pRock, pPaper, pScissors;
    private Personal currenciWarrion;
    private String name;
    public int command = 0;
    public boolean fcommand;

    public Warrion(String name, Personal.Pclass pclass) {
        this.name = name;
        fcommand = true;
        pPaper = CreaterUnits.getPaper();
        pRock = CreaterUnits.getRock();
        pScissors = CreaterUnits.getScissors();
        selectedClassUnit(pclass);
    }

    public Personal selectedClassUnit(Personal.Pclass pclass) {
        switch (pclass) {
            case PAPER:
                currenciWarrion = pPaper;
                break;
            case ROCK:
                currenciWarrion = pRock;
                break;
            case SCISSORS:
                currenciWarrion = pScissors;
                break;
        }
        return currenciWarrion;
    }

    public void lvlUp() {
        currenciWarrion.lvlUp();
    }

    public boolean lessHP(int less) {
        return currenciWarrion.lessHP(less);
    }

    public void plusHP(int health) {
        currenciWarrion.plusHP(health);
    }

    public int getAttack() {
        return currenciWarrion.getAttack();
    }

    public int getProtect() {
        return currenciWarrion.getProtect();
    }

    public int getHealthPoint() {
        return currenciWarrion.getHealthPoint();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Personal getCurrenciWarrion() {
        return currenciWarrion;
    }

    public void setCurrenciWarrion(Personal currenciWarrion) {
        this.currenciWarrion = currenciWarrion;
    }
}
