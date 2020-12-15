package nl.dreamteam.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.dreamteam.server.abstracts.GameObject;

@AllArgsConstructor
public class Map {
    @Getter @Setter
    private GameObject[][] gameObjects;
}
