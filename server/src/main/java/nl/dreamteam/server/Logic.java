package nl.dreamteam.server;

import nl.dreamteam.server.models.Lobby;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Lobby> lobbies = new ArrayList<>();

    public int createLobbyAndReturnId(String username) {
        int lobbyId = lobbies.size() + 1;
        lobbies.add(new Lobby(lobbyId, username));
        return lobbyId;
    }

    private Lobby getLobby(int lobbyId) {
//        for (Lobby lobby : lobbies){
//            if (lobby.getId() == lobbyId){
//                return lobby;
//            }
//        }
//        return null;
        return lobbies.stream().filter(lobby -> lobby.getId() == lobbyId).findFirst().get();
    }

    public ArrayList<String> getPlayers(int lobbyId) {
        return getLobby(lobbyId).getPlayers();
    }

    public void joinLobby(int lobbyId, String username) {
        getLobby(lobbyId).addPlayer(username);
    }
}
