package nl.dreamteam.server.models;

import lombok.Getter;
import lombok.Setter;

public class Wall {
    @Getter
    private Position position;

    public Wall(Position position){
        this.position = position;
    }

    public void translatePosition(int translationValue){
        position.setPoint(position.getX() * translationValue, position.getY() * translationValue);
    }
}
