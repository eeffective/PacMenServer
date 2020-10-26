package nl.dreamteam.server.controllers;

import nl.dreamteam.server.models.WSMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSMessageController {

    @MessageMapping("/pacmen")
    @SendTo("topic/wsmessages")
    public WSMessage sendWSMessage(WSMessage message) throws Exception {
        Thread.sleep(1000);
        return new WSMessage(message.getMessage(), message.getMessageType());
    }
}
