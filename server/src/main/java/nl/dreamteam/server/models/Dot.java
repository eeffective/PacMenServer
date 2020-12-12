package nl.dreamteam.server.models;

import lombok.Getter;

public class Dot {
    @Getter
    private Position position;

    public Dot(Position position){
        this.position = position;
    }
}
