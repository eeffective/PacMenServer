package nl.dreamteam.server.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatMessage {

    @Getter
    private String content;

    @Getter
    private String sender;
}
