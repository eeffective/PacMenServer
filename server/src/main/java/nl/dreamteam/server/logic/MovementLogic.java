package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.abstracts.GameObject;
import nl.dreamteam.server.controllers.MessageController;
import nl.dreamteam.server.models.*;

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
        lobbyPlayer.setCurrentDirection(direction);

        Position nextPos = getNextPosition(lobbyPlayer.getPosition(), direction);
        Position convertedPos = nextPositionPerfectAlignment(nextPos, direction);
        if(isPerfectlyAligned(nextPos, direction)){
            if(isPathBlocked(convertedPos, lobby.getMap().getGameObjects())){
                return;
            }
        } else {
            return;
        }

        if(pacmanCollidesWithGhost(lobbyPlayer, nextPos, lobby.getPlayers(), lobby)){
                if(!lobbyPlayer.getAlive()){
                    messageController.SendDeadMessage(lobby.getPlayers());
                    messageController.SendEndMessage(lobby.getPlayers());
                }
                return;

        } else if (ghostCollidesWithPacman(lobbyPlayer, nextPos, lobby.getPlayers(), lobby)) {
            ;
        }
        Dot dot = collidesWithDot(convertedPos, lobby.getMap().getGameObjects());
        if(dot != null && lobbyPlayer.getPlayerType() == PlayerType.PACMAN) {
            lobbyPlayer.addScore(dot.getValue());
            GameObject[][] objects = lobby.getMap().getGameObjects();
            objects[dot.getPosition().getY()/Lobby.squareWidth][dot.getPosition().getX()/Lobby.squareWidth] = null;
            lobby.getMap().setGameObjects(objects);
            messageController.UpdatePacmanDots(lobby.getPlayers(), dot);
        }
        PowerUp pup = collidesWithPowerup(convertedPos, lobby.getMap().getGameObjects());
        if(pup != null && lobbyPlayer.getPlayerType() == PlayerType.PACMAN) {
            lobbyPlayer.setPowerUp(true);
            GameObject[][] objects = lobby.getMap().getGameObjects();
            objects[pup.getPosition().getY()/Lobby.squareWidth][pup.getPosition().getX()/Lobby.squareWidth] = null;
            lobby.getMap().setGameObjects(objects);
            messageController.UpdatePowerUps(lobby.getPlayers(), pup);
        }
        move(lobbyPlayer, nextPos);

        messageController.UpdatePlayerMovement(lobby.getPlayers());
    }

    private boolean isPerfectlyAligned(Position oldPosition, Direction direction){
        if(isHorizontalMovement(direction)){
            return oldPosition.getY() % Lobby.squareWidth == 0;
        } else {
            return oldPosition.getX() % Lobby.squareWidth == 0;
        }
    }

    private Position nextPositionPerfectAlignment(Position oldPosition, Direction direction){
        int newX;
        int newY;
        switch(direction){
            case Up:
            case Left:
                newX = oldPosition.getX() / Lobby.squareWidth;
                newY = oldPosition.getY() / Lobby.squareWidth;
                break;
            case Down:
                newX = oldPosition.getX() / Lobby.squareWidth;
                newY = (int)Math.ceil(oldPosition.getY() / (double)Lobby.squareWidth);
                break;
            case Right:
                newX = (int)Math.ceil(oldPosition.getX() / (double)Lobby.squareWidth);
                newY = oldPosition.getY() / Lobby.squareWidth;
                break;
            default:
                return oldPosition;
        }
        return new Position(newX, newY);
    }

    private boolean isHorizontalMovement(Direction direction){
        return direction == Direction.Left || direction == Direction.Right;
    }

    private boolean isPathBlocked(Position nextPosition, GameObject[][] objects){
        if(objects[nextPosition.getY()][nextPosition.getX()] instanceof Wall){
            return true;
        }
        return false;
    }

    private Dot collidesWithDot(Position nextPosition, GameObject[][] objects){
        if(objects[nextPosition.getY()][nextPosition.getX()] instanceof Dot){
            return (Dot) objects[nextPosition.getY()][nextPosition.getX()];
        }
        return null;
    }

    private PowerUp collidesWithPowerup(Position nextPosition, GameObject[][] objects){
        if(objects[nextPosition.getY()][nextPosition.getX()] instanceof PowerUp){
            return (PowerUp) objects[nextPosition.getY()][nextPosition.getX()];
        }
        return null;
    }

    private boolean pacmanCollidesWithGhost(Player pacman, Position nextPos, ArrayList<Player> players, Lobby lobby){
        if (pacman.getPlayerType() != PlayerType.PACMAN) return false;
        List<Player> ghosts = players.stream().filter(p -> !p.getPlayerType().equals(PlayerType.PACMAN)).collect(Collectors.toList());

        for(var g : ghosts){
            if(g.getPosition().getX() == nextPos.getX() && g.getPosition().getY() == nextPos.getY() && !isPoweredUp(pacman)) {
                pacman.loseLife();
                lobby.resetPlayers();
                g.addScore(250);
                return true;
            } else if(g.getPosition().getX() == nextPos.getX() && g.getPosition().getY() == nextPos.getY() && isPoweredUp(pacman)) {
                pacman.addScore(150);
                lobby.resetSinglePlayer(g);
                return true;
            }
        } return false;

    }

    private boolean ghostCollidesWithPacman(Player ghost, Position nextPos, ArrayList<Player> players, Lobby lobby){
        if (!isGhost(ghost.getPlayerType().toString())) return false;
        Player pacman = players.stream().filter(p -> p.getPlayerType().equals(PlayerType.PACMAN)).findFirst().get();


            if(pacman.getPosition().getX() == nextPos.getX() && pacman.getPosition().getY() == nextPos.getY() && !isPoweredUp(pacman)) {
                ghost.addScore(250);
                pacman.loseLife();
                lobby.resetPlayers();
                return true;
            }  else if(pacman.getPosition().getX() == nextPos.getX() && pacman.getPosition().getY() == nextPos.getY() && isPoweredUp(pacman)) {
                pacman.addScore(150);
                lobby.resetSinglePlayer(ghost);
                return true;
        }   return false;

    }

    private boolean isGhost(String input) {
        return input.contains("GHOST");
    }

    private boolean isPoweredUp(Player player) {
        return player.getPlayerType() == PlayerType.PACMAN && player.isPowerUp();
    }
}
