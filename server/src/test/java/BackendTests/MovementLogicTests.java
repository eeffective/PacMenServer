//package BackendTests;
//
//import nl.dreamteam.server.Enums.Direction;
//import nl.dreamteam.server.controllers.MessageController;
//import nl.dreamteam.server.logic.LobbyLogic;
//import nl.dreamteam.server.logic.MovementLogic;
//import nl.dreamteam.server.messages.Message;
//import nl.dreamteam.server.models.Lobby;
//import nl.dreamteam.server.models.Player;
//import nl.dreamteam.server.models.Position;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MovementLogicTests {
////    MovementLogic movementLogic;
////    LobbyLogic lobbyLogic;
////    @Mock
////    MessageController controller;
////    SimpMessagingTemplate simpMessagingTemplate;
////    MessageChannel messageChannel;
////    Lobby lobby;
////
////    @Before
////    public void Initialize() {
////        MockitoAnnotations.initMocks(this);
////        lobbyLogic = new LobbyLogic();
////        movementLogic = new MovementLogic();
////        lobbyLogic.createLobbyAndReturnId("test");
////        lobby = lobbyLogic.getLobby(1);
////        lobby.start();
////        messageChannel = Mockito.mock(MessageChannel.class);
////        simpMessagingTemplate = new SimpMessagingTemplate(messageChannel);
////        controller = new MessageController(simpMessagingTemplate);
////    }
//
////    @Test
////    public void TryMoveWorksCorrectlyNoObstructions() {
////        movementLogic.tryMove("test", Direction.Up, lobby, controller);
////        Position pos = lobby.getPlayer("test").getPosition();
////        Assert.assertEquals(Direction.Up, lobby.getPlayer("test").getCurrentDirection());
////        Assert.assertNotEquals(pos, lobby.getPlayer("test").getPosition());
////    }
//}
