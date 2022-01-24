package com.capgemini.market.controller;

import com.capgemini.market.model.Game;
import com.capgemini.market.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/game")
public interface GameController {

    @GetMapping(value = {"/all"})
    ResponseEntity<List<Game>> getAllGame();

    @DeleteMapping(value = "/del/{id}")
    ResponseEntity<String> deleteGame(@PathVariable int id);

    @GetMapping(value = "/get", params = {"id"})
    ResponseEntity<Game> getGameById(@RequestParam(name = "id") int id);

    @PostMapping(value = "/update")
    ResponseEntity<String> updateGame(@RequestBody Game game);

    @PostMapping(value = {"/add"})
    ResponseEntity<Boolean> addGame(@RequestBody Game game);
}
