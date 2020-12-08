package nl.dreamteam.server.messages;


import nl.dreamteam.server.Enums.MessageType;
import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import nl.dreamteam.server.models.Point;

import java.util.ArrayList;

public class Message {
    public String to;
    public int lobbyId;
    public String username;
    public MessageType messageType;
    public ArrayList<Player> players;
    public Point position;

    public Message() {
    }
}
