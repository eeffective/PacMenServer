package nl.dreamteam.server.models;


import nl.dreamteam.server.Enums.PlayerType;

public class Player {
    private String username;
    private PlayerType playerType;
    private int lives;
    private boolean isHost;
<<<<<<< Updated upstream
    private int x;
    private int y;
=======
    private Point position;

    public void setPosition(Point position){
        this.position = position;
    }

    public Point getPosition(){
        return this.position;
    }
>>>>>>> Stashed changes

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
