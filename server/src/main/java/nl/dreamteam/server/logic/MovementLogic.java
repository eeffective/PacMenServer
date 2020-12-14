package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.models.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

public class MovementLogic {

    private LobbyLogic lobbyLogic;
    private final int movementDistance = 4;

    public MovementLogic(LobbyLogic lobbyLogic){
        this.lobbyLogic = lobbyLogic;
    }

    private void move(Player player, Position position){
        player.setPosition(position);
    }

    private Position getNextPosition(Position currentPos, Direction direction){
        switch (direction){
            case Right:
                return new Position(currentPos.getX() + movementDistance, currentPos.getY());
            case Left:
                return new Position(currentPos.getX() - movementDistance, currentPos.getY());
            case Up:
                return new Position(currentPos.getX(), currentPos.getY() - movementDistance);
            case Down:
                return new Position(currentPos.getX(), currentPos.getY() + movementDistance);
            default: return currentPos;
        }
    }

    public void tryMove(String username, Direction direction, Lobby lobby, MessageController messageController){
        Player player = lobby.getPlayer(username);
        Position nextPos = getNextPosition(player.getPosition(), direction);
        if(isPathBlocked(nextPos, lobby.getMap().getWalls())){
            return;
        }
        if(collidesWithOpponent(player, direction, lobby.getPlayers())){
            //do stuff @Tom @Jesse @Jasper @Efaliso @Monique @Nicole
        }
        if(collidesWithDot(nextPos, lobby.getMap().getDots()) != null && player.getPlayerType() == PlayerType.PACMAN) {
            Dot dot = collidesWithDot(nextPos, lobby.getMap().getDots());
            player.addScore(dot.getValue());
            lobby.getMap().getDots().remove(dot);
            messageController.UpdatePacmanDots(lobby.getPlayer(username), dot);
        }
        move(player, nextPos);

        messageController.UpdatePlayerMovement(lobby.getPlayers());
    }


    private boolean isPathBlocked(Position nextPosition, ArrayList<Wall> walls){
        for (Wall wall: walls) {
            if(nextPosition.getX() < wall.getPosition().getX() + Lobby.squareWidth &&
                    nextPosition.getX() + Lobby.squareWidth > wall.getPosition().getX() &&
                    nextPosition.getY() < wall.getPosition().getY() + Lobby.squareWidth &&
                    nextPosition.getY() + Lobby.squareWidth > wall.getPosition().getY()){
                return true;
            }
        }
        return false;
    }

    private Dot collidesWithDot(Position nextPosition, ArrayList<Dot> dots){
        for (Dot dot: dots) {
            if(nextPosition.getX() < dot.getPosition().getX() + Lobby.squareWidth/2 &&
                    nextPosition.getX() + Lobby.squareWidth/2 > dot.getPosition().getX() &&
                    nextPosition.getY() < dot.getPosition().getY() + Lobby.squareWidth/2 &&
                    nextPosition.getY() + Lobby.squareWidth/2 > dot.getPosition().getY()){
                return dot;
            }
        }
        return null;
    }

    private boolean collidesWithOpponent(Player player, Direction direction, ArrayList<Player> players){
        return false;
    }
}
