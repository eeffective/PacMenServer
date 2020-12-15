package nl.dreamteam.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.dreamteam.server.Enums.ObjectType;
import nl.dreamteam.server.abstracts.GameObject;

@RequiredArgsConstructor
public class Dot extends GameObject {
    @Getter @NonNull
    private Position position;

    @Getter
    private int value = 10;

    @Getter
    private ObjectType objectType = ObjectType.Dot;

}
