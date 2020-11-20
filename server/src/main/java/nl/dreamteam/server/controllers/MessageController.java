package nl.dreamteam.server.controllers;

import nl.dreamteam.server.models.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MessageController {

    ArrayList<Integer> usedIds = new ArrayList<>();
    ArrayList<Game> games = new ArrayList<>();

    @MessageMapping("/register")
    @SendTo("/topic/players")
    public Message register() {
        System.out.println("REGISTER MESSAGE REVEIVED");
        Message message = new Message();
        message.id = usedIds.size();
        return message;
    }

    @MessageMapping("/movementUpdate")
    @SendTo("/topic/players")
    public Message movementUpdate(Message message) {
        return message;
    }

    @MessageMapping("/joinLobby")
    @SendTo("/topic/lobby")
    public Message JoinLobby(int gameId, Player player){
        Message message = new Message();
        
        if (games.size() == 0){ games.add(new Game(games.size() + 1)); }
        for (Game g : games){
            if (g.getId() == gameId){
                if (!g.GameContainsPacman() && !g.GameIsFull()) {
                    g.JoinGame(player);
                    message.playerList.addAll(g.getPlayerList());
                }
            }
        }
        return message;
    }

    @MessageMapping("/lobby")
    @SendTo("/topic/lobby")
    public Message GetLobby(Game game){
        Message message = new Message();
        for (Game g : games){
            if (g.getId() == game.getId()){
                message.playerList = g.getPlayerList();
            }
        }
        return message;
    }

    @MessageMapping("/makeLobby")
    @SendTo("/topic/lobby")
    public Message MakeLobby(){
        Message message = new Message();
        Game game = new Game(games.size() + 1);

        games.add(game);
        message.lobbyList = games;
        return message;
    }
}
