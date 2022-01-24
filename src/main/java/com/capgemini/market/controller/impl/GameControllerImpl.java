package com.capgemini.market.controller.impl;

import com.capgemini.market.controller.GameController;
import com.capgemini.market.model.Game;
import com.capgemini.market.service.GameService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameControllerImpl implements GameController {

    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<List<Game>> getAllGame() {
        List<Game> all = gameService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteGame(int id) {
        return new ResponseEntity<>(gameService.deleteGame(id), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Game> getGameById(int id) {
        Game resultGame = gameService.getById(id);
        return new ResponseEntity<>(resultGame, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateGame(Game game) {
        return new ResponseEntity<>(gameService.changedGame(game), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Boolean> addGame(Game game) {
        Boolean isAdded = gameService.addGame(game);
        return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
    }
}
