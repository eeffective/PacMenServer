package nl.dreamteam.server.models;
import lombok.Getter;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.logic.MapGenerator;


import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<PlayerType> types = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            types.add(PlayerType.values()[i]);
        }
        Collections.shuffle(types);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPosition(new Position(1, 1));
            players.get(i).getPosition().translate(squareWidth);
            players.get(i).setPlayerType(types.get(i));
        }
    }
}
