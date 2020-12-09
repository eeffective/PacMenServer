package nl.dreamteam.server.controllers;

import nl.dreamteam.server.Enums.MessageType;
import nl.dreamteam.server.logic.LobbyLogic;
import nl.dreamteam.server.logic.MovementLogic;
import nl.dreamteam.server.messages.Message;
import nl.dreamteam.server.models.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final LobbyLogic lobbyLogic = new LobbyLogic();
    private final MovementLogic movementLogic = new MovementLogic(lobbyLogic);

    public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/move")
    public void movementUpdate(Message messageIn) {
        Lobby lobby = lobbyLogic.getLobby(messageIn.lobbyId);
        movementLogic.tryMove(messageIn.username, messageIn.direction, lobby, this);
    }

    @MessageMapping("/joinLobby")
    public void JoinLobby(Message messageIn) {
        lobbyLogic.joinLobby(messageIn.lobbyId, messageIn.username);
        Message messageOut = new Message();
        messageOut.messageType = MessageType.JOIN_LOBBY;
        messageOut.players = lobbyLogic.getPlayers(messageIn.lobbyId);
        messageOut.lobbyId = messageIn.lobbyId;
        SendMessageToPlayers(messageOut, messageOut.players);
    }

    @MessageMapping("/createLobby")
    public void CreateLobby(Message messageIn) {
        Message messageOut = new Message();
        messageOut.lobbyId = lobbyLogic.createLobbyAndReturnId(messageIn.username);
        messageOut.players = lobbyLogic.getPlayers(messageOut.lobbyId);
        messageOut.messageType = MessageType.CREATE_LOBBY;
        System.out.println(messageIn.to);
        simpMessagingTemplate.convertAndSend(messageIn.to, messageOut);
    }

    @MessageMapping("/start")
    public void StartGame(Message messageIn) {
        Lobby lobby = lobbyLogic.getLobby(messageIn.lobbyId);
        lobby.start();
        Message messageOut = new Message();
        messageOut.messageType = MessageType.START;
        messageOut.walls = lobby.getWalls();
        messageOut.squareWidth = Lobby.squareWidth;
        messageOut.players = lobby.getPlayers();
        SendMessageToPlayers(messageOut, lobbyLogic.getPlayers(messageIn.lobbyId));
    }

    public void UpdatePlayerMovement(ArrayList<Player> players, Player playerToMove){
        Message message = new Message();
        message.messageType = MessageType.MOVE;
        message.username = playerToMove.getUsername();
        message.position = playerToMove.getPosition();
        SendMessageToPlayers(message, players);
    }

    private void SendMessageToPlayers(Message message, ArrayList<Player> players){
        for(Player p : players){
            String to = "/topic/" + p.getUsername();
            simpMessagingTemplate.convertAndSend(to, message);
        }
    }

//    @MessageMapping("/loseLife")
//    @SendTo("/topic/game")
//    public Message LoseLife(int gameId, Player player){
//        Message message = new Message();
//        for (Game g : games){
//            if (gameId == g.getId()){
//                for (Player p : g.getPlayerList()){
//                    if (player.getId() == p.getId()){
//                        p.loseLife();
//                        message.playerList = g.getPlayerList();
//                    }
//                }
//            }
//        }
//        return message;
//    }
}
