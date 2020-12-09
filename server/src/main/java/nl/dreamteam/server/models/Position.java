package nl.dreamteam.server.models;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
