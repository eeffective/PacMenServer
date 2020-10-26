package nl.dreamteam.server.models;


import lombok.Getter;
import lombok.Setter;

public class WSMessage {

    @Setter
    @Getter
    private String message;

    @Getter
    @Setter
    private WSMessageType messageType;

    public WSMessage(String message, WSMessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }
}


