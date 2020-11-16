package nl.dreamteam.server.websocket;

import SharedWebsocket.MessageType;
import SharedWebsocket.WebsocketDTO;
import SharedWebsocket.WebsocketMessage;
import com.google.gson.Gson;
import models.Player;

import javax.websocket.Session;
import java.util.ArrayList;

//Sends messages to client
public class MessageSender {
    Gson gson = new Gson();

    public void ShowLobbyPlayers(Session sess, WebsocketDTO dto) {
        WebsocketMessage socketMessage = new WebsocketMessage(MessageType.SHOW_PLAYER_LIST, dto);
        String message = gson.toJson(socketMessage, WebsocketMessage.class);
        sess.getAsyncRemote().sendText(message);
    }
}