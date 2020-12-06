package nl.dreamteam.server.models;


import nl.dreamteam.server.Enums.PlayerType;

public class Player {
    private String username;
    private PlayerType playerType;
    private int lives;
    private boolean isHost;

    public Player(String username) {
        this.username = username;
        this.lives = 3;
        this.isHost = false;
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
}
