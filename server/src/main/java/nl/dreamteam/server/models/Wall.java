package nl.dreamteam.server.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.dreamteam.server.Enums.ObjectType;
import nl.dreamteam.server.abstracts.GameObject;

@RequiredArgsConstructor
public class Wall extends GameObject {
    @Getter @NonNull
    private Position position;

    @Getter
    private ObjectType objectType = ObjectType.Wall;
}
