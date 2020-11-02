package nl.dreamteam.server.models;


import lombok.Getter;
import lombok.Setter;

public class WSMessage {

    @Getter
    @Setter
    private Integer userId;

    @Getter
    @Setter
    private Integer x;

    @Getter
    @Setter
    private Integer y;

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

/*
    Geest + muur = client
    Pacman + muur = cient
    Pacman + Geest = client
 */

