package nl.dreamteam.server.websocket;

import SharedWebsocket.WebsocketDTO;
import models.Game;
import models.Player;

import javax.websocket.Session;
import java.util.ArrayList;

public class ServerLogicHandler {
    private CommunicatorWebsocket communicatorWebSocket;
    MessageSender messageSender = new MessageSender();
    private ArrayList<Player> playerList = new ArrayList<Player>();

    public ServerLogicHandler(CommunicatorWebsocket communicatorWebSocket){
        this.communicatorWebSocket = communicatorWebSocket;
    }

    public void JoinGame(Game game, WebsocketDTO dto, Session session) {
        game.JoinGame(dto.getPlayer());
        messageSender.ShowLobbyPlayers(session, dto);
    }
}
