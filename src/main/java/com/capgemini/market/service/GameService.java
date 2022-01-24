package com.capgemini.market.service;

import com.capgemini.market.model.Game;
import com.capgemini.market.model.Person;

import java.util.List;

public interface GameService {

    Boolean addGame(Game game);

    List<Game> findAll();

    Game getById(int id);

    String deleteGame(int id);

    String changedGame(Game game);
}
