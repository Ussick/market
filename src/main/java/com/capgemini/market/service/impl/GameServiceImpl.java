package com.capgemini.market.service.impl;

import com.capgemini.market.model.Game;
import com.capgemini.market.model.Person;
import com.capgemini.market.repository.GameRepository;
import com.capgemini.market.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        List<Game> all = gameRepository.findAll();
        return (all != null) ? all : new ArrayList<>();
    }

    @Override
    public Game getById(int id) {
        Optional<Game> byId = gameRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return new Game();
        }
    }

    @Override
    public String deleteGame(int id) {
        Game game = getById(id);
        if (game.getId() != 0) {
            gameRepository.delete(game);
            return "Game with id " + id + " has been deleted";
        } else {
            return "Game with id " + id + " doesn't exist";
        }
    }

    @Override
    public String changedGame(Game game) {
        if (game == null){
            return "This game doesn't exist";
        }
        if (!gameExist(game)) {
            return "This game doesn't exist";
        }
            gameRepository.save(game);
            return "Game with id " + game.getId() + " has been updated";
    }

    @Override
    public Boolean addGame(Game game) {
        if (game == null || game.getName() == null) {
            return false;
        }
        gameRepository.save(game);
        return true;
    }

    private boolean gameExist(Game game) {
        boolean exist = false;
        List<Game> gameList = findAll();
        for (Game tmp : gameList) {
            if (tmp.getId()==game.getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
