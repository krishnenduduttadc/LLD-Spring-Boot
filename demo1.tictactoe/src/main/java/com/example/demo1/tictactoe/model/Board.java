package com.example.demo1.tictactoe.model;

public class Board {
    private final int size = 3;
    private Cell[][] board;

    public Board() {
        board = new Cell[size][size];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j].isEmpty()) return false;
        return true;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public boolean markCell(int row, int col, Player player) {
        if (row >= size || col >= size || !board[row][col].isEmpty()) return false;
        board[row][col].setPlayer(player);
        return true;
    }
}
