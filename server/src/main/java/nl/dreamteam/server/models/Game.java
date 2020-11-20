package nl.dreamteam.server.models;

import nl.dreamteam.server.Enums.Type;

import javax.websocket.Session;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> playerList = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private int Id;

    public Game() {
    }

    public Game(int id) {
        Id = id;
    }

    //    public void AddSession(Session session){
//        for (Session s : sessions){
//            if (s.getId() == session.getId()){
//                sessions.add(session);
//            }
//        }
//    }

    public void JoinGame(Player player){
        playerList.add(player);
    }

    public boolean GameIsFull(){
        return playerList.size() == 5;
    }

    public boolean GameContainsPacman(){
        for (Player p : playerList){
            if (p.getType() == Type.PACMAN){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
