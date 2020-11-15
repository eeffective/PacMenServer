package nl.dreamteam.server.websocket;

import SharedWebsocket.MessageType;
import SharedWebsocket.WebsocketDTO;
import SharedWebsocket.WebsocketMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import models.Game;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

//Receives messages from client
@ServerEndpoint(value = "/websocketserver")
public class CommunicatorWebsocket {
    private static final List<Session> sessions = new ArrayList<>();
    private static final List<Game> games = new ArrayList<>();
    private Gson gson = new Gson();
    ServerLogicHandler logicHandler = new ServerLogicHandler( this);

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        WebsocketMessage wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage, WebsocketMessage.class);
        } catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket ERROR: cannot parse Json message " + jsonMessage);
            return;
        }

        // Operation defined in message
        MessageType operation;
        operation = wbMessage.getOperation();

        // Process message based on operation
        WebsocketDTO dto = wbMessage.getDto();
        Game game = findGame(session);

        try {
            if (game == null) {
                game = connectToGame(session);
            }
        } catch(Exception e){
            gameNotFoundError(session);
        }

        if (operation != null) {
            switch (operation) {
                case JOIN_GAME:
                    logicHandler.JoinGame(game, dto, session);
                    break;
                default:
                    System.out.println("[WebSocket ERROR: cannot process Json message " + jsonMessage);
                    break;
            }
        }
    }

    public Game connectToGame(Session session) {
        for (Game g : games) {
            if (g.AddSession(session)) {
                return g;
            }
        }
        Game game = new Game();
        game.AddSession(session);
        games.add(game);
        return game;
    }

    public Game findGame(Session session){
        for(Game game : games){
            for(Session s : game.getSessions()){
                if(session == s){
                    return game;
                }
            }
        }
        return null;
    }

    public void gameNotFoundError(Session s){
        WebsocketMessage communicatorWebSocketMessage = new WebsocketMessage(MessageType.GAME_NOT_FOUND_ERROR);
        String message = gson.toJson(communicatorWebSocketMessage, WebsocketMessage.class);
        s.getAsyncRemote().sendText(message);
    }
}
