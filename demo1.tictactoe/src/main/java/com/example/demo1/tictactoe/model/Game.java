package com.example.demo1.tictactoe.model;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private GameStatus status;
    private Player winner;

    public Game(List<Player> players) {
        this.players = players;
        this.board = new Board();
        this.status = GameStatus.IN_PROGRESS;
        this.currentPlayerIndex = 0;
    }

    public boolean makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) return false;

        Player currentPlayer = players.get(currentPlayerIndex);
        boolean moveMade = board.markCell(row, col, currentPlayer);

        if (!moveMade) return false;

        if (checkWin(row, col, currentPlayer)) {
            status = GameStatus.WIN;
            winner = currentPlayer;
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        return true;
    }

    private boolean checkWin(int row, int col, Player player) {
        Cell[][] cells = board.getBoard();
        int size = 3;

        // Check row
        boolean winRow = true;
        for (int i = 0; i < size; i++)
            winRow &= cells[row][i].getPlayer() == player;

        // Check col
        boolean winCol = true;
        for (int i = 0; i < size; i++)
            winCol &= cells[i][col].getPlayer() == player;

        // Check diagonals
        boolean winDiag1 = row == col;
        if (winDiag1)
            for (int i = 0; i < size; i++)
                winDiag1 &= cells[i][i].getPlayer() == player;

        boolean winDiag2 = row + col == size - 1;
        if (winDiag2)
            for (int i = 0; i < size; i++)
                winDiag2 &= cells[i][size - 1 - i].getPlayer() == player;

        return winRow || winCol || winDiag1 || winDiag2;
    }

    public Board getBoard() { return board; }
    public Player getCurrentPlayer() { return players.get(currentPlayerIndex); }
    public GameStatus getStatus() { return status; }
    public Player getWinner() { return winner; }
}

