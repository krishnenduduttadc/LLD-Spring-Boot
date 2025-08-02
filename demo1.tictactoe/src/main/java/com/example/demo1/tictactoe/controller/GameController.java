package com.example.demo1.tictactoe.controller;

import com.example.demo1.tictactoe.model.*;
import com.example.demo1.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public String startGame(@RequestParam String player1, @RequestParam String player2) {
        return gameService.startNewGame(player1, player2);
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam int row, @RequestParam int col) {
        boolean result = gameService.makeMove(row, col);
        if (!result) {
            return "Invalid move!";
        }

        Game game = gameService.getGame();
        if (game.getStatus() == GameStatus.WIN) {
            return "Player " + game.getWinner().getName() + " wins!";
        } else if (game.getStatus() == GameStatus.DRAW) {
            return "Game is a draw!";
        } else {
            return "Move successful! Next: " + game.getCurrentPlayer().getName();
        }
    }

    @GetMapping("/status")
    public Game getGameStatus() {
        return gameService.getGame();
    }
}
