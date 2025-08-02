package com.example.demo1.tictactoe.model;

public class Player {
    private String name;
    private PlayerType type;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public PlayerType getType() { return type; }
}
