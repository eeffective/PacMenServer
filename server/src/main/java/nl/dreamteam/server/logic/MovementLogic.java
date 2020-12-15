package nl.dreamteam.server.logic;

import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;
import nl.dreamteam.server.abstracts.GameObject;
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
        lobbyPlayer.setCurrentDirection(direction);

        Position nextPos = getNextPosition(lobbyPlayer.getPosition(), direction);
        ArrayList<Position> convertedPositions = new ArrayList<>();
        if(isPerfectlyAligned(nextPos, direction)){
            convertedPositions.add(nextPositionPerfectAlignment(nextPos, direction));
        } else {
            convertedPositions = nextPositionImperfectAlignment(nextPos, direction);
        }

        if(isPathBlocked(convertedPositions, lobby.getMap().getGameObjects())){
            return;
        }
        if(collidesWithOpponent(lobbyPlayer, nextPos, lobby.getPlayers())){
            lobby.resetPlayers();
            messageController.UpdatePlayerMovement(lobby.getPlayers());
            return;
        }
        Dot dot = collidesWithDot(convertedPositions, lobby.getMap().getGameObjects());
        if(dot != null && lobbyPlayer.getPlayerType() == PlayerType.PACMAN) {
            lobbyPlayer.addScore(dot.getValue());
            GameObject[][] objects = lobby.getMap().getGameObjects();
            objects[dot.getPosition().getX()/Lobby.squareWidth][dot.getPosition().getY()/Lobby.squareWidth] = null;
            lobby.getMap().setGameObjects(objects);
            messageController.UpdatePacmanDots(lobby.getPlayers(), dot);
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

    private ArrayList<Position> nextPositionImperfectAlignment(Position oldPosition, Direction direction){
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(nextPositionPerfectAlignment(oldPosition, direction));
        int newX;
        int newY;
        switch(direction){
            case Up:
                newX = (int)Math.ceil(oldPosition.getX() / (double)Lobby.squareWidth);
                newY = (int)Math.ceil(oldPosition.getY() / Lobby.squareWidth);
                break;
            case Left:
                newX = (int)Math.ceil(oldPosition.getX() / Lobby.squareWidth);
                newY = (int)Math.ceil(oldPosition.getY() / (double)Lobby.squareWidth);
                break;
            case Down:
            case Right:
                newX = (int)Math.ceil(oldPosition.getX() / (double)Lobby.squareWidth);
                newY = (int)Math.ceil(oldPosition.getY() / (double)Lobby.squareWidth);
                break;
            default:
                newX = 0;
                newY = 0;
                break;
        }
        positions.add(new Position(newX, newY));
        return positions;
    }

    private boolean isHorizontalMovement(Direction direction){
        return direction == Direction.Left || direction == Direction.Right;
    }

    private boolean isPathBlocked(ArrayList<Position> positions, GameObject[][] objects){
        for (Position nextPosition:positions) {
            if(objects[nextPosition.getY()][nextPosition.getX()] instanceof Wall){
                return true;
            }
        }
        return false;
    }

    private Dot collidesWithDot(ArrayList<Position> positions, GameObject[][] objects){
        for (Position nextPosition:positions) {
            if(objects[nextPosition.getY()][nextPosition.getX()] instanceof Dot){
                return (Dot) objects[nextPosition.getY()][nextPosition.getX()];
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
