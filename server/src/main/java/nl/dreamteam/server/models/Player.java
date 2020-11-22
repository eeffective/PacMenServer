package nl.dreamteam.server.models;


import nl.dreamteam.server.Enums.Type;

public class Player {
    private int Id;
    private String name;
    private Type type;

    private int Life;

    public Player(String name, Type type) {
        this.name = name;
        this.type = type;
        Life = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void loseLife() {
        this.Life -= 1;
    }

    public int getId() {
        return Id;
    }

    public int getLife() {
        return Life;
    }

    public boolean isDead() {
        if (Life == 0){
            return true;
        }
        return false;
    }
}
