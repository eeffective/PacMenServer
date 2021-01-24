package BackendTests;


import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LobbyTests {
    Lobby lobby;
    Player player;

    @Before
    public void Initialize() {
        player = new Player("test");
    }

    @Test
    public void TestLobbyGetsInitializedCorrectly() {
        lobby = new Lobby(1, player);
        Assert.assertNotNull(lobby.getMap());
        Assert.assertNotNull(lobby.getPlayers());
        Assert.assertEquals(player, lobby.getPlayers().get(0));
        Assert.assertEquals(1, lobby.getPlayers().size());
    }

    @Test
    public void TestAddPlayerToLobby() {
        lobby = new Lobby(1, player);
        Player secondPlayer = new Player("secondPlayer");
        lobby.addPlayer(secondPlayer);
        Assert.assertEquals(secondPlayer, lobby.getPlayers().get(1));
        Assert.assertEquals(2, lobby.getPlayers().size());
    }

    @Test
    public void TestGetPlayerGiveCorrectPlayer(){
        lobby = new Lobby(1, player);
        Player secondPlayer = new Player("secondPlayer");
        lobby.addPlayer(secondPlayer);
        var actual = lobby.getPlayer("secondPlayer");
        Assert.assertEquals(secondPlayer, actual);
    }
}
