package models;

import javax.websocket.Session;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> playerList = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    public boolean AddSession(Session session){
        if (!GameIsFull()){
            sessions.add(session);
            return true;
        }
        return false;
    }

    public void JoinGame(Player player){
        playerList.add(player);
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
}
