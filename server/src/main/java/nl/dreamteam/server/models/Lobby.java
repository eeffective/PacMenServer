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
        Collections.shuffle(players);
        for(int i = 0; i < players.size(); i++){
            players.get(i).setPlayerType(PlayerType.values()[i]);
            setRandomPositionsPlayer(players.get(i));
            players.get(i).getPosition().translate(squareWidth);
        }
    }

    private void setRandomPositionsPlayer(Player player) {
        if(player.getPlayerType() == PlayerType.PACMAN) {
            player.setPosition(getRandomPositionBottomLayer());
        } else {
            player.setPosition(getRandomPositionTopLayer());
        }
    }
    private Position getRandomPositionTopLayer() {
        int range = 8;
        return new Position((int)Math.random() * 8 + 1, 1);
    }

    private Position getRandomPositionBottomLayer() {
        int range = 8;
        return new Position((int)Math.random() * 10 + 1, 18);
    }

    public void resetPlayers() {
        for(Player player: players) {
            setRandomPositionsPlayer(player);
            player.getPosition().translate(squareWidth);
        }
    }
}
