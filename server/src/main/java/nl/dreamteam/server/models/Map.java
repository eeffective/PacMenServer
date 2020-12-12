package nl.dreamteam.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
public class Map {
    @Getter
    private ArrayList<Wall> walls;

    @Getter
    private ArrayList<Dot> dots;
}
