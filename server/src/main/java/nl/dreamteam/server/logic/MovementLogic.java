package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.models.Lobby;
import nl.dreamteam.server.models.Player;
import nl.dreamteam.server.models.Position;
import nl.dreamteam.server.models.Wall;

import java.util.ArrayList;

public class MovementLogic {

    private LobbyLogic lobbyLogic;
    private final int movementDistance = 4;

    public MovementLogic(LobbyLogic lobbyLogic){
        this.lobbyLogic = lobbyLogic;
    }

    private void move(Player player, Direction direction){
        Position position;
        switch (direction){
            case Right:
                position = new Position(player.getPosition().getX() + movementDistance, player.getPosition().getY());
                break;
            case Left:
                position = new Position(player.getPosition().getX() - movementDistance, player.getPosition().getY());
                break;
            case Up:
                position = new Position(player.getPosition().getX(), player.getPosition().getY() - movementDistance);
                break;
            case Down:
                position = new Position(player.getPosition().getX(), player.getPosition().getY() + movementDistance);
                break;
            default: position = player.getPosition();
        }
        player.setPosition(position);
    }

    public void tryMove(String username, Direction direction, Lobby lobby, MessageController messageController){
        Player player = lobby.getPlayer(username);
        if(isPathBlocked(player.getPosition(), direction, lobby.getWalls())){
            return;
        }
        if(collidesWithOpponent(player, direction, lobby.getPlayers())){
            //do stuff @Tom @Jesse @Jasper @Efaliso @Monique @Nicole
        }
        move(player, direction);
        messageController.UpdatePlayerMovement(lobby.getPlayers(), player);
    }

    private boolean isPathBlocked(Position position, Direction direction, ArrayList<Wall> walls){
        return false;
    }

    private boolean collidesWithOpponent(Player player, Direction direction, ArrayList<Player> players){
        return false;
    }
}
