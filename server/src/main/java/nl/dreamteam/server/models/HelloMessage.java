package nl.dreamteam.server.models;

import lombok.Getter;
import lombok.Setter;

public class HelloMessage {
    @Getter
    @Setter
    private String name;

    public HelloMessage(String name) {
        this.name = name;
    }
}
