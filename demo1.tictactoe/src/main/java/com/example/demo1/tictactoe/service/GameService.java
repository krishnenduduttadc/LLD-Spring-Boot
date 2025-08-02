package com.example.demo1.tictactoe.service;

import com.example.demo1.tictactoe.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private Game currentGame;

    public String startNewGame(String playerOneName, String playerTwoName) {
        List<Player> players = new ArrayList<>();
        players.add(new Player(playerOneName, PlayerType.X));
        players.add(new Player(playerTwoName, PlayerType.O));
        currentGame = new Game(players);
        return "Game started!";
    }

    public boolean makeMove(int row, int col) {
        if (currentGame == null) {
            throw new IllegalStateException("Game not started");
        }
        return currentGame.makeMove(row, col);
    }

    public Game getGame() {
        if (currentGame == null) {
            throw new IllegalStateException("Game not started");
        }
        return currentGame;
    }
}
