package nl.dreamteam.server.models;


import lombok.Getter;
import lombok.Setter;

public class Message {

    @Setter
    @Getter
    private String message;

    public Message(String message) {
        this.message = message;
    }
}


