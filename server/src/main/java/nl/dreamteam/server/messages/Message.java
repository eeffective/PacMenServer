package nl.dreamteam.server.messages;


import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.MessageType;
import nl.dreamteam.server.models.*;

import java.util.ArrayList;

public class Message {
    public String to;
    public int lobbyId;
    public String username;
    public MessageType messageType;
    public ArrayList<Player> players;
    public Position position;
    public Map map;
    public int squareWidth;
    public Direction direction;
    public Dot dot;
    public int score;
    public PowerUp powerUp;
    public int score;
//
//    public Integer id;
//
//    public Integer x;
//
//    public Integer y;
    public ChatMessage chatMessage;

    public Message() {
    }
}
