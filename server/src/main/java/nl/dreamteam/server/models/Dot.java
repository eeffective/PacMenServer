package nl.dreamteam.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Dot {
    @Getter
    private Position position;

    @Getter
    private int value = 10;

}
