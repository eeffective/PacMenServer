package nl.dreamteam.server.models;

import lombok.Getter;
import lombok.Setter;

public class Greeting {
    @Getter
    @Setter
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}
