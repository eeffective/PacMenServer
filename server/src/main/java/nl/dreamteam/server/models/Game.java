package nl.dreamteam.server.models;

import javax.websocket.Session;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> playerList = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private int Id;

    public void AddSession(Session session){
        for (Session s : sessions){
            if (s.getId() == session.getId()){
                sessions.add(session);
            }
        }
    }

    public void JoinGame(Player player){
        if (!GameIsFull()){ playerList.add(player);}
    }

    public boolean GameIsFull(){
        return playerList.size() == 4;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
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
