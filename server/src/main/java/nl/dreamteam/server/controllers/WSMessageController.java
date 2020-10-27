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
        return new WSMessage(message.getMessage(), message.getMessageType());
    }

}


// client 1 verzendt wsmessage met x1 y2
// client 2 krijgt x1 y2
