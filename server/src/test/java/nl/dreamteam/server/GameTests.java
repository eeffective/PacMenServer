package nl.dreamteam.server;


import nl.dreamteam.server.Enums.Type;
import nl.dreamteam.server.models.Game;
import nl.dreamteam.server.models.Message;
import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GameTests {
    Game game;

    @Before
    public void Initialize(){
        game = new Game();
    }

    @Test
    public void TestAddPlayerToEmptyLobby(){
        Player player = new Player("Player", Type.PACMAN);
        game.JoinGame(player);
        Assert.assertEquals(game.getPlayerList().size(), 1);
    }

    @Test
    public void TestGameIsFull(){
        Player player1 = new Player("Jasper", Type.GHOST);
        Player player2 = new Player("Jasper", Type.GHOST);
        Player player3 = new Player("Jasper", Type.GHOST);
        Player player4 = new Player("Jasper", Type.GHOST);
        Player player5 = new Player("Jasper", Type.PACMAN);

        game.JoinGame(player1);
        game.JoinGame(player2);
        game.JoinGame(player3);
        game.JoinGame(player4);
        game.JoinGame(player5);

        Assert.assertTrue(game.GameIsFull());
    }

    @Test
    public void TestGameIsNotFull(){
        Player player1 = new Player("Jasper", Type.GHOST);
        game.JoinGame(player1);
        Assert.assertFalse(game.GameIsFull());
    }

    @Test
    public void TestGameContainsPacmanWithOnly1PlayerInLobby(){
        Player player1 = new Player("Jasper", Type.PACMAN);
        game.JoinGame(player1);
        Assert.assertTrue(game.GameContainsPacman());
    }

    @Test
    public void TestGameContainsPacman(){
        Player player1 = new Player("Jasper", Type.PACMAN);
        Player player2 = new Player("Jasper", Type.GHOST);
        game.JoinGame(player1);
        game.JoinGame(player2);
        Assert.assertTrue(game.GameContainsPacman());
    }

    @Test
    public void TestGameDoesntContainPacman(){
        Player player2 = new Player("Jasper", Type.GHOST);
        game.JoinGame(player2);
        Assert.assertFalse(game.GameContainsPacman());
    }

    @Test
    public void MakeGameWithCorrectId(){
        ArrayList<Game> games = new ArrayList<>();
        Game game = new Game();
        game.setId(games.size() + 1);

        Assert.assertTrue(game.getId() == 1);
    }

    @Test
    public void MakeMultipleGamesWithCorrectId(){
        ArrayList<Game> games = new ArrayList<>();
        Game game1 = new Game();
        Game game2 = new Game();
        Game game3 = new Game();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        Game game4 = new Game(games.size() + 1);

        Assert.assertTrue(game4.getId() == 4);
    }
}
