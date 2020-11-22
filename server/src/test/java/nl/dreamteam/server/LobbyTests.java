//package nl.dreamteam.server;
//
//
//import nl.dreamteam.server.Enums.Type;
//import nl.dreamteam.server.models.Lobby;
//import nl.dreamteam.server.models.Player;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class LobbyTests {
//    Lobby lobby;
//
//    @Before
//    public void Initialize(){
//        lobby = new Lobby();
//    }
//
//    @Test
//    public void TestAddPlayerToEmptyLobby(){
//        Player player = new Player("Player", Type.PACMAN);
//        lobby.JoinGame(player);
//        Assert.assertEquals(lobby.getPlayerList().size(), 1);
//    }
//
////    @Test
////    public void TestGameIsFull(){
////        Player player1 = new Player("Jasper", Type.GHOST);
////        Player player2 = new Player("Jasper", Type.GHOST);
////        Player player3 = new Player("Jasper", Type.GHOST);
////        Player player4 = new Player("Jasper", Type.GHOST);
////        Player player5 = new Player("Jasper", Type.PACMAN);
////
////        lobby.JoinLobby(player1);
////        lobby.JoinLobby(player2);
////        lobby.JoinLobby(player3);
////        lobby.JoinLobby(player4);
////        lobby.JoinLobby(player5);
////
////        Assert.assertTrue(lobby.LobbyIsFull());
////    }
//
////    @Test
////    public void TestGameIsNotFull(){
////        Player player1 = new Player("Jasper", Type.GHOST);
////        lobby.JoinLobby(player1);
////        Assert.assertFalse(lobby.LobbyIsFull());
////    }
////
////    @Test
////    public void TestGameContainsPacmanWithOnly1PlayerInLobby(){
////        Player player1 = new Player("Jasper", Type.PACMAN);
////        lobby.JoinLobby(player1);
////        Assert.assertTrue(lobby.LobbyContainsPacman());
////    }
////
////    @Test
////    public void TestGameContainsPacman(){
////        Player player1 = new Player("Jasper", Type.PACMAN);
////        Player player2 = new Player("Jasper", Type.GHOST);
////        lobby.JoinLobby(player1);
////        lobby.JoinLobby(player2);
////        Assert.assertTrue(lobby.LobbyContainsPacman());
////    }
////
////    @Test
////    public void TestGameDoesntContainPacman(){
////        Player player2 = new Player("Jasper", Type.GHOST);
////        lobby.JoinLobby(player2);
////        Assert.assertFalse(lobby.LobbyContainsPacman());
////    }
//
//    @Test
//    public void MakeGameWithCorrectId(){
//        ArrayList<Lobby> lobbies = new ArrayList<>();
//        Lobby lobby = new Lobby();
//        lobby.setId(lobbies.size() + 1);
//
//        Assert.assertTrue(lobby.getId() == 1);
//    }
//
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
//}
