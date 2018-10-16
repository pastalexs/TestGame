package com.test.testgame.model.event;

public class FinishGame {
    private final int idMessage;

    private final String nameWinner;

    public FinishGame(int idMessage, String nameWinner) {
        this.idMessage = idMessage;
        this.nameWinner = nameWinner;
    }

    public String getNameWinner() {
        return nameWinner;
    }

    public int getIdMessage() {
        return idMessage;
    }
}
