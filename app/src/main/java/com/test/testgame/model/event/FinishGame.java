package com.test.testgame.model.event;

public class FinishGame {
    private final String message;

    private final String nameWinner;

    public FinishGame(String message,String nameWinner) {
        this.message = message;
        this.nameWinner=nameWinner;
    }

    public String getMessage() {
        return message;
    }

    public String getNameWinner() {
        return nameWinner;
    }
}
