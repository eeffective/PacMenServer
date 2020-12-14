package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.models.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

public class MovementLogic {

    private final int movementDistance = 4;

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
        Player lobbyPlayer = lobby.getPlayer(username);
        Position nextPos = getNextPosition(lobbyPlayer.getPosition(), direction);
        if(isPathBlocked(nextPos, lobby.getMap().getWalls())){
            return;
        }
        if(collidesWithOpponent(lobbyPlayer, nextPos, lobby.getPlayers())){
            lobby.resetPlayers();
            messageController.UpdatePlayerMovement(lobby.getPlayers());
            return;
        }
        if(collidesWithDot(nextPos, lobby.getMap().getDots()) != null && lobbyPlayer.getPlayerType() == PlayerType.PACMAN) {
            Dot dot = collidesWithDot(nextPos, lobby.getMap().getDots());
            lobbyPlayer.addScore(dot.getValue());
            lobby.getMap().getDots().remove(dot);
            messageController.UpdatePacmanDots(lobby.getPlayers(), dot);
        }
        move(lobbyPlayer, nextPos);

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

    private boolean collidesWithOpponent(Player currentPlayer, Position nextPos, ArrayList<Player> players){
        for(Player player: players) {
            if(player.getPosition().getX() == nextPos.getX() && player.getPosition().getY() == nextPos.getY() && isOpponent(currentPlayer.getPlayerType(), player.getPlayerType())) {
                return true;
            }
        }
        return false;
    }

    private boolean isOpponent(PlayerType playerType, PlayerType opponentType) {
        int ghostCounter = 0;
        String player = playerType.toString();
        if(isGhost(player)) {
            ghostCounter++;
        }
        String opponent = opponentType.toString();
        if(isGhost(opponent)) {
            ghostCounter++;
        }

        if(ghostCounter == 1) {
            return true;
        }
        return false;
    }

    private boolean isGhost(String input) {
        return input.indexOf("GHOST") !=-1? true: false;
    }
}
