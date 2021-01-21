package nl.dreamteam.server;


import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.logic.LobbyLogic;
import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LobbyTests {
    LobbyLogic lobbyLogic;

    @Before
    public void Initialize(){
        lobbyLogic = new LobbyLogic();
    }

    @Test
    public void TestAddPlayerToNewLobby(){
        Player player1 = new Player("Jasper");
        lobbyLogic.createLobbyAndReturnId(player1.getUsername());

        Assert.assertEquals(lobbyLogic.getPlayers(1).size(), 1);
    }

    @Test
    public void TestGetLobbyById(){
        Player player1 = new Player("Jasper");

        lobbyLogic.createLobbyAndReturnId(player1.getUsername());
        Assert.assertEquals(lobbyLogic.getLobby(1).getId(), 1);
    }

    @Test
    public void TestGetUserByNameInLobby(){
        Player player1 = new Player("Jasper");
        lobbyLogic.createLobbyAndReturnId(player1.getUsername());

        Player playerFound = lobbyLogic.getPlayerWithUsername("Jasper", 1);

        Assert.assertEquals(playerFound.getUsername(), player1.getUsername());
    }

    @Test
    public void TestGetPlayersByLobby(){
        Player player1 = new Player("Jasper");
        lobbyLogic.createLobbyAndReturnId(player1.getUsername());

        Player player2 = new Player("Efaliso");
        Player player3 = new Player("Jussi");
        lobbyLogic.joinLobby(1, player2.getUsername());
        lobbyLogic.joinLobby(1, player3.getUsername());

        Assert.assertEquals(lobbyLogic.getPlayers(1).size(), 3);
    }
//    @Test
//    public void TestGameIsFull(){
//        Player player1 = new Player("Jasper");
//        Player player2 = new Player("Jasper");
//        Player player3 = new Player("Jasper");
//        Player player4 = new Player("Jasper");
//        Player player5 = new Player("Jasper");
//
//        lobby.addPlayer(player1);
//        lobby.addPlayer(player2);
//        lobby.addPlayer(player3);
//        lobby.addPlayer(player4);
//        lobby.addPlayer(player5);
//
//        Assert.assertEquals(lobby.getPlayers().size(), 5);
//    }

//    @Test
//    public void TestGameIsNotFull(){
//        Player player1 = new Player("Jasper", Type.GHOST);
//        lobby.JoinLobby(player1);
//        Assert.assertFalse(lobby.LobbyIsFull());
//    }
//
//    @Test
//    public void TestGameContainsPacmanWithOnly1PlayerInLobby(){
//        Player player1 = new Player("Jasper", Type.PACMAN);
//        lobby.JoinLobby(player1);
//        Assert.assertTrue(lobby.LobbyContainsPacman());
//    }
//
//    @Test
//    public void TestGameContainsPacman(){
//        Player player1 = new Player("Jasper", Type.PACMAN);
//        Player player2 = new Player("Jasper", Type.GHOST);
//        lobby.JoinLobby(player1);
//        lobby.JoinLobby(player2);
//        Assert.assertTrue(lobby.LobbyContainsPacman());
//    }
//
//    @Test
//    public void TestGameDoesntContainPacman(){
//        Player player2 = new Player("Jasper", Type.GHOST);
//        lobby.JoinLobby(player2);
//        Assert.assertFalse(lobby.LobbyContainsPacman());
//    }
//
//    @Test
//    public void MakeGameWithCorrectId(){
//        ArrayList<Lobby> lobbies = new ArrayList<>();
//        Lobby lobby = new Lobby();
//        lobby.setId(lobbies.size() + 1);
//
//        Assert.assertTrue(lobby.getId() == 1);
//    }

//    @Test
//    public void MakeMultipleGamesWithCorrectId(){
//        ArrayList<Lobby> lobbies = new ArrayList<>();
//        Lobby lobby1 = new Lobby();
//        Lobby lobby2 = new Lobby();
//        Lobby lobby3 = new Lobby();
//        lobbies.add(lobby1);
//        lobbies.add(lobby2);
//        lobbies.add(lobby3);
//        Lobby lobby4 = new Lobby(lobbies.size() + 1);
//
//        Assert.assertTrue(lobby4.getId() == 4);
//    }
}
