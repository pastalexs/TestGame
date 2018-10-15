package com.test.testgame.model;

public class Personal {
    private int maxHealthPoint;
    private int healthPoint;
    private int attack;
    private int protect;
    private String img;
    private Pclass pclass;
    private boolean fLive;

    public Personal(int healthPoint, int attack, int protect, String img, Pclass pclass) {
        this.healthPoint = healthPoint;
        this.maxHealthPoint = healthPoint;
        this.attack = attack;
        this.protect = protect;
        this.img = img;
        this.pclass = pclass;
        fLive = true;
    }

    public void lvlUp(){
        attack+=10;
        protect+=3;
        maxHealthPoint+=50;
    }
    public boolean lessHP(int less){
        healthPoint-=less;
        if(healthPoint<=0){
            fLive=false;
            healthPoint=0;
        }
        return fLive;
    }
    public void plusHP(int health){
        healthPoint+=health;
        if(healthPoint>maxHealthPoint){
            healthPoint=maxHealthPoint;
        }
    }

    public boolean isfLive() {
        return fLive;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getProtect() {
        return protect;
    }

    public void setProtect(int protect) {
        this.protect = protect;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Pclass getPclass() {
        return pclass;
    }

    public void setPclass(Pclass pclass) {
        this.pclass = pclass;
    }

    public enum Pclass {
        ROCK("Rock"),
        SCISSORS("Scissors"),
        PAPER("Paper");

        private String name;

        Pclass(String str) {
            name = str;
        }
        public boolean equalsPclassName(String otherName) {
            return name.equals(otherName);
        }
        public String toString() {
            return this.name;
        }
    }
}
