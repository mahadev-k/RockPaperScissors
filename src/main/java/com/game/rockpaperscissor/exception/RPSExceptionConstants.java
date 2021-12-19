package com.game.rockpaperscissor.exception;

public enum RPSExceptionConstants {

    NO_TOKEN("no.token"),
    INVALID_TOKEN("invalid.token"),
    INVALID_MOVE("invalid.move");

    private String type;

    private RPSExceptionConstants(String message){
        this.type = message;
    }

    public String getType() {
        return type;
    }
}
