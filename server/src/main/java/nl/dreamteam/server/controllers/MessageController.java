package nl.dreamteam.server.controllers;

import nl.dreamteam.server.models.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MessageController {

    ArrayList<Integer> usedIds = new ArrayList<>();

    @MessageMapping("/register")
    @SendTo("/topic/players")
    public Message register() {
        System.out.println("REGISTER MESSAGE REVEIVED");
        Message message = new Message();
        message.id = usedIds.size();
        return message;
    }

    @MessageMapping("/movementUpdate")
    @SendTo("/topic/players")
    public Message movementUpdate(Message message) {
        return message;
    }
}
