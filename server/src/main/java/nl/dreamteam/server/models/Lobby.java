package nl.dreamteam.server.models;

import javax.websocket.Session;
import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private int id;

    public Lobby(int id, Player player) {
        this.id = id;
        players.add(player);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
