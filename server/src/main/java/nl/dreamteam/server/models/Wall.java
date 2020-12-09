package nl.dreamteam.server.models;

import lombok.Getter;

public class Wall {
    @Getter
    private Position position;

    public Wall(Position position){
        this.position = position;
    }
}
