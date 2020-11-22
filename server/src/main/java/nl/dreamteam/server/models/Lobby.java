package nl.dreamteam.server.models;

import nl.dreamteam.server.Enums.Type;

import javax.websocket.Session;
import java.util.ArrayList;

public class Lobby {
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private int id;

    public Lobby(int id, String username) {
        this.id = id;
        players.add(username);
    }

    //    public void AddSession(Session session){
//        for (Session s : sessions){
//            if (s.getId() == session.getId()){
//                sessions.add(session);
//            }
//        }
//    }

    public void addPlayer(String username){
        players.add(username);
    }

//    public boolean lobbyIsFull(){
//        return players.size() == 5;
//    }
//
//    public boolean LobbyContainsPacman(){
//        for (Player p : playerList){
//            if (p.getType() == Type.PACMAN){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public ArrayList<Player> getPlayerList() {
//        return playerList;
//    }
//
    public int getId() {
        return id;
    }
}
