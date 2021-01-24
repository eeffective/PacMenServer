package BackendTests;

import nl.dreamteam.server.logic.LobbyLogic;
import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LobbyLogicTests {
    LobbyLogic lobbyLogic;

    @Before
    public void Initialize() {
        lobbyLogic = new LobbyLogic();
    }

    @Test
    public void TestCreateLobbyAndReturnId_ReturnsIdCorrectlyFirstLobby() {
        int actual = lobbyLogic.createLobbyAndReturnId("test");
        Assert.assertNotNull(lobbyLogic.getLobby(1));
        Assert.assertEquals("test", lobbyLogic.getLobby(1).getPlayers().get(0).getUsername());
        Assert.assertTrue(lobbyLogic.getLobby(1).getPlayer("test").getIsHost());
        Assert.assertEquals(1, actual);
    }

    @Test
    public void TestGetLobbyReturnsLobby() {
        lobbyLogic.createLobbyAndReturnId("test");
        var lobby = lobbyLogic.getLobby(1);
        Assert.assertEquals(Lobby.class, lobby.getClass());
    }

    @Test
    public void GetPlayerWithUserNameReturnsCorrectUser() {
        int lobbyId = lobbyLogic.createLobbyAndReturnId("test");
        Player player = lobbyLogic.getPlayerWithUsername("test", lobbyId);
        Assert.assertEquals(lobbyLogic.getLobby(lobbyId).getPlayers().get(0), player);
    }

    @Test
    public void JoinLobbyAddsPlayer() {
        int lobbyId = lobbyLogic.createLobbyAndReturnId("test");
        lobbyLogic.joinLobby(lobbyId, "secondPlayer");
        Assert.assertEquals(2, lobbyLogic.getLobby(lobbyId).getPlayers().size());
    }
}
