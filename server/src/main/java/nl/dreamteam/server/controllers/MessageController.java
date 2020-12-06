package nl.dreamteam.server.controllers;

import nl.dreamteam.server.Enums.MessageType;
import nl.dreamteam.server.Logic;
import nl.dreamteam.server.messages.Message;
import nl.dreamteam.server.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Logic logic = new Logic();

    public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/move")
    public void movementUpdate(Message messageIn) {
        Message messageOut = new Message();
        messageOut.messageType = MessageType.MOVE;
        simpMessagingTemplate.convertAndSend(messageIn.to, messageIn);
    }

    @MessageMapping("/joinLobby")
    public void JoinLobby(Message messageIn) {
        logic.joinLobby(messageIn.lobbyId, messageIn.username);
        Message messageOut = new Message();
        messageOut.messageType = MessageType.JOIN_LOBBY;
        messageOut.players = logic.getPlayers(messageIn.lobbyId);
        messageOut.lobbyId = messageIn.lobbyId;
        for(Player p : messageOut.players){
            String to = "/topic/" + p.getUsername();
            System.out.println("to = " + to);
            simpMessagingTemplate.convertAndSend(to, messageOut);
        }
    }

    @MessageMapping("/createLobby")
    public void CreateLobby(Message messageIn) {
        Message messageOut = new Message();
        messageOut.lobbyId = logic.createLobbyAndReturnId(messageIn.username);
        messageOut.players = logic.getPlayers(messageOut.lobbyId);
        messageOut.messageType = MessageType.CREATE_LOBBY;
        System.out.println(messageIn.to);
        simpMessagingTemplate.convertAndSend(messageIn.to, messageOut);
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
