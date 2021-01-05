package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.models.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if(pacmanCollidesWithGhost(lobbyPlayer, nextPos, lobby.getPlayers())){
            lobby.resetPlayers();
            if(!lobbyPlayer.getAlive()){
                messageController.SendDeadMessage(lobby.getPlayers());
            }
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


    private boolean pacmanCollidesWithGhost(Player pacman, Position nextPos, ArrayList<Player> players){
        if (pacman.getPlayerType() != PlayerType.PACMAN) return false;
        List<Player> ghosts = players.stream().filter(p -> !p.getPlayerType().equals(PlayerType.PACMAN)).collect(Collectors.toList());

        for(var g : ghosts){
            if(g.getPosition().getX() == nextPos.getX() && g.getPosition().getY() == nextPos.getY()) {
                pacman.loseLife();
                return true;
            }
        } return false;

    }

}
