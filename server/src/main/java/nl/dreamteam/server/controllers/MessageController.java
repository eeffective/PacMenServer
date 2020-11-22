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
    public void JoinLobby(Message messageIn){
        logic.joinLobby(messageIn.lobbyId, messageIn.username);
        Message messageOut = new Message();
        messageOut.messageType = MessageType.JOIN_LOBBY;
        simpMessagingTemplate.convertAndSend(messageIn.to, messageOut);
    }

    @MessageMapping("/createLobby")
    public void CreateLobby(Message messageIn){
        Message messageOut = new Message();
        messageOut.lobbyId = logic.createLobbyAndReturnId(messageIn.username);
        messageOut.messageType = MessageType.CREATE_LOBBY;
        simpMessagingTemplate.convertAndSend(messageIn.to, messageOut);
    }
}
