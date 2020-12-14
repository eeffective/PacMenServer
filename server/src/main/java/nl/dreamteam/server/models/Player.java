package nl.dreamteam.server.models;


import nl.dreamteam.server.Enums.PlayerType;

public class Player {
    private String username;
    private PlayerType playerType;
    private Boolean alive;
    private int lives;
    private int score;
    private boolean isHost;
    private Position position;

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    public Player(String username) {
        this.username = username;
        this.lives = 3;
        this.isHost = false;
        this.alive = true;
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

    //    public void loseLife() {
//        this.lives -= 1;
//    }
//
//    public int getLife() {
//        return this.lives;
//    }
//
//    public boolean isDead() {
//        if (this.lives == 0){
//            return true;
//        }
//        return false;
//    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife (){
        this.lives--;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}
