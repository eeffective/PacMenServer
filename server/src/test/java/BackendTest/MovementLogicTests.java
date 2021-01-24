package BackendTest;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.logic.LobbyLogic;
import nl.dreamteam.server.logic.MovementLogic;
import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import nl.dreamteam.server.models.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MovementLogicTests {
    public String username;
    public Player playerTwo;
    public LobbyLogic lobbyLogic;
    public MovementLogic movementLogic;
    public Lobby lobby;
    public int lobbyId;
    public MessageController messageController;
    public SimpMessagingTemplate simpMessagingTemplate;
    public MessageChannel messageChannel;

    @Before
    public void Initialize() {
        username = "test";
        lobbyLogic = new LobbyLogic();
        movementLogic = new MovementLogic();
        lobbyId = lobbyLogic.createLobbyAndReturnId("test");
        lobby = lobbyLogic.getLobby(lobbyId);
        playerTwo = new Player("playerTwo");
        lobby.addPlayer(playerTwo);
        messageChannel = new MessageChannel() {
            @Override
            public boolean send(Message<?> message, long l) {
                return false;
            }
        };
        simpMessagingTemplate = new SimpMessagingTemplate(messageChannel);
        messageController = new MessageController(simpMessagingTemplate);
        movementLogic.useController = false;
        lobby.start();
    }

    @Test
    public void TestIfTryMoveLetsPlayerMoveAfterStartingTheGame() {
        Position pos = lobby.getPlayer("test").getPosition();
        movementLogic.tryMove(username, Direction.Right, lobby, messageController);
        Assert.assertNotEquals(lobby.getPlayer(username).getPosition(), pos);
        Assert.assertEquals(Direction.Right, lobby.getPlayer("test").getCurrentDirection());
    }

    @Test
    public void TestIfTryMoveStopsPlayerFromGoingThroughWallsAfterStartingTheGame() {
        Position pos = lobby.getPlayer("test").getPosition();
        movementLogic.tryMove(username, Direction.Left, lobby, messageController);
        Assert.assertEquals(lobby.getPlayer(username).getPosition(), pos);
        Assert.assertEquals(Direction.Left, lobby.getPlayer("test").getCurrentDirection());
    }

    @Test
    public void TestIfTryMoveStopsPlayerFromGoingThroughWallsAfterStartingTheGameAndMoving() {
        Position pos = new Position(1,1);
        for(int i = 0; i < lobby.getPlayers().size(); i++) {
            if (lobby.getPlayers().get(i).getPlayerType() == PlayerType.PACMAN) {
                movementLogic.tryMove(lobby.getPlayers().get(i).getUsername(), Direction.Right, lobby, messageController);
                pos = lobby.getPlayer(lobby.getPlayers().get(i).getUsername()).getPosition();
                movementLogic.tryMove(lobby.getPlayers().get(i).getUsername(), Direction.Up, lobby, messageController);
                Assert.assertEquals(lobby.getPlayers().get(i).getScore(), 10);
                Assert.assertEquals(lobby.getPlayer(lobby.getPlayers().get(i).getUsername()).getPosition(), pos);
                Assert.assertEquals(Direction.Up, lobby.getPlayer(lobby.getPlayers().get(i).getUsername()).getCurrentDirection());
            }
        }
    }

    @Test
    public void TestIfCollisionMeansLiveGetsTaken() {
        lobby.getPlayer("test").setPosition(new Position(40,40));
        lobby.getPlayer("playerTwo").setPosition(new Position(40,44));
        movementLogic.tryMove("test", Direction.Down, lobby, messageController);
        for(int i = 0; i < lobby.getPlayers().size(); i++) {
            if(lobby.getPlayers().get(i).getPlayerType() == PlayerType.PACMAN) {
                Assert.assertEquals(lobby.getPlayers().get(i).getLives(), 2);
            } else {
                Assert.assertEquals(lobby.getPlayers().get(i).getScore(), 250);
            }
        }
    }

    @Test
    public void TestPowerUpCollision() {
        for(int i = 0; i< lobby.getPlayers().size(); i++) {
            if(lobby.getPlayers().get(i).getPlayerType() == PlayerType.PACMAN) {
                lobby.getPlayers().get(i).setPosition(new Position(320, 284));
                movementLogic.tryMove(lobby.getPlayers().get(i).getUsername(), Direction.Up, lobby, messageController);
                Assert.assertEquals(lobby.getPlayers().get(i).isPowerUp(), true);
            }
        }
    }
}
