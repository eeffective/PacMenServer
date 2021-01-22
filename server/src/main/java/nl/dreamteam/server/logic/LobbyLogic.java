package nl.dreamteam.server.logic;

import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;

import java.util.ArrayList;

public class LobbyLogic {
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

    public Lobby getLobby(int lobbyId) {
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

    public Player getByName(String username, Lobby lobby){
        return lobby.getPlayers().stream().filter(p -> p.getUsername().equals(username)).findFirst().get();
    }

    public ArrayList<Player> closeLobbyOfPlayer(Player player){
        Lobby currentLobby = lobbies
                .stream().filter(lobby -> lobby.getPlayers()
                        .stream().anyMatch(player1 -> player1.getUsername() == player.getUsername())).findFirst().orElse(null);
        ArrayList<Player> players = currentLobby.getPlayers();
        for (Player p:players) {
            p.setAlive(true);
            p.setLives(3);
        }
        lobbies.remove(currentLobby);
        return players;
    }
}
