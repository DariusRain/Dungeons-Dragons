package com.coderain;

public class Character {
    // type is refering to Driud, Wizard, Barbarian etc.
    private String type;

    // Player is the player's name
    private String player;

    // Health is initialized to 100 in constructor
    private int health;


    public Character(String type, String player) {
        this.type = type;
        this.player = player;
        health = 100;
    }

    // Getters & setters
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
