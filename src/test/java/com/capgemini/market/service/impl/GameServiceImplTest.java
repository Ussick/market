package com.capgemini.market.service.impl;

import com.capgemini.market.model.Game;
import com.capgemini.market.model.Person;
import com.capgemini.market.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceImplTest {
    @Autowired
    GameService gameService;

    public Game victim = new Game();

    @BeforeEach
    public void setVictim() {
        victim.setId(1);
        victim.setName("Last of us");
        victim.setPrice(300);
        victim.setAvailable(true);
    }


    @Test
    void tryToFindAll() {
        List<Game> expected = List.of(victim);
        assertEquals(expected, gameService.findAll());
    }


    @Test
    void isGameAdded() {
        Game gameToAdd = new Game();
        gameToAdd.setName("Last of us 2");
        gameToAdd.setAvailable(false);
        gameToAdd.setPrice(1000);
        assertTrue(gameService.addGame(gameToAdd));
        gameService.deleteGame(gameToAdd.getId());
    }

    @Test
    void isNullOrEmptyGameAdded() {
        assertFalse(gameService.addGame(new Game()));
        assertFalse(gameService.addGame(null));
    }

    @Test
    void isGetById() {
        assertEquals(victim, gameService.getById(victim.getId()));
    }

    @Test
    void isNullorEmptyOrAbsentGameChanged() {
        String expected = "This game doesn't exist";
        victim.setId(100);
        assertEquals(expected, gameService.changedGame(null));
        assertEquals(expected, gameService.changedGame(new Game()));
        assertEquals(expected, gameService.changedGame(victim));
    }

    @Test
    void deleteGame() {
        assertEquals("Game with id 2 doesn't exist", gameService.deleteGame(2));
    }
}