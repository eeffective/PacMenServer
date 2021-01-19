package nl.dreamteam.server.models;


import lombok.Getter;
import lombok.Setter;
import nl.dreamteam.server.Enums.Direction;
import nl.dreamteam.server.Enums.PlayerType;

public class Player {
    private String username;
    private PlayerType playerType;
    private Boolean alive;
    private int lives;
    private int score;
    private boolean isHost;
    private Position position;
    @Getter @Setter
    private Direction currentDirection;
    @Getter @Setter
    private Direction futureDirection;
    @Getter @Setter
    private boolean powerUp;

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    private int score;



    public Player(String username) {
        this.username = username;
        this.lives = 3;
        this.isHost = false;
        this.alive = true;
        this.powerUp = false;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public String getUsername() {
        return this.username;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public int getLives() {
        return this.lives;
    }

    public boolean getIsHost() {
        return this.isHost;
    }

    public void setHost() {
        this.isHost = true;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }


    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife (){
        if (this.getLives() <= 1){
            this.setAlive(false);
        } else {
            this.setLives(this.lives--);
        }
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}
