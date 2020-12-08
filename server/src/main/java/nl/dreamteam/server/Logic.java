package nl.dreamteam.server;

import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Lobby> lobbies = new ArrayList<>();

    public int createLobbyAndReturnId(String username) {
        int lobbyId = lobbies.size() + 1;
        Player player = createPlayer(username);
        player.setHost();
        lobbies.add(new Lobby(lobbyId, player));
        return lobbyId;
    }

    public Player createPlayer(String username){
        return new Player(username);
    }

    private Lobby getLobby(int lobbyId) {
        return lobbies.stream().filter(lobby -> lobby.getId() == lobbyId).findFirst().get();
    }

    public  Player getPlayerWithUsername(String name, int lobbyId){
        return getLobby(lobbyId).getPlayers().stream().filter(player -> player.getUsername() == name).findFirst().get();
    }

    public ArrayList<Player> getPlayers(int lobbyId) {
        return getLobby(lobbyId).getPlayers();
    }

    public void joinLobby(int lobbyId, String username) {
        getLobby(lobbyId).addPlayer(createPlayer(username));
    }
}
