package com.coderain;

public class Character {
    private String type;
    private String player;
    private int health;


    public Character(String type, String player) {
        this.type = type;
        this.player = player;
        health = 100;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public String getPlayer() {
        return player;
    }

    public int getHealth() {
        return health;
    }

}
