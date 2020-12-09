package nl.dreamteam.server.models;

import lombok.Getter;

public class Position {

    @Getter
    private int x;
    @Getter
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int translationValue){
        x *= translationValue;
        y *= translationValue;
    }
}
