package nl.dreamteam.server.models;
import lombok.Getter;
import nl.dreamteam.server.logic.MapGenerator;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players = new ArrayList<>();
    @Getter
    private Map map;


    public static final int squareWidth = 40;

    private int id;

    public Lobby(int id, Player player) {
        this.id = id;
        players.add(player);
        map = MapGenerator.getMap(squareWidth);
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

    public Player getPlayer(String username){
        return players.stream().filter(p -> p.getUsername().equals(username)).findAny().get();
    }

    //don't remove plz <3 isgoed ;)
    public void start(){
        initializePlayers();
    }

    private void initializePlayers(){
        for (Player player: players) {
            player.setPosition(new Position(1, 1));
            player.getPosition().translate(squareWidth);
        }
    }
}
