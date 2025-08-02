package com.example.demo1.tictactoe.model;

public class Cell {
    private int row;
    private int col;
    private Player player; // null means empty

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isEmpty() {
        return this.player == null;
    }

    // Getters & Setters
    public int getRow() { return row; }
    public int getCol() { return col; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
}
