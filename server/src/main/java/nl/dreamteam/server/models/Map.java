package nl.dreamteam.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
public class Map {
    @Getter
    private ArrayList<Wall> walls;

    @Getter @Setter
    private ArrayList<Dot> dots;
}
