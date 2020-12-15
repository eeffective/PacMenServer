package nl.dreamteam.server.abstracts;

import nl.dreamteam.server.Enums.ObjectType;
import nl.dreamteam.server.models.Position;

public abstract class GameObject {
    public abstract Position getPosition();
    public abstract ObjectType getObjectType();
}
