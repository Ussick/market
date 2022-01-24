package com.capgemini.market.controller;

import com.capgemini.market.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/person")
public interface PersonController {

    @PostMapping(value = {"/login"})
    ResponseEntity<String> checkPerson(@RequestParam(name = "login") String login,
                                       @RequestParam(name = "password") String password);

    @PostMapping(value = {"/registration"})
    ResponseEntity<Boolean> insertPerson(@RequestBody Person person);

    @GetMapping(value = {"/all"})
    ResponseEntity<List<Person>> getAllPerson();

    @DeleteMapping(value = "/del/{id}")
    ResponseEntity<String> deletePerson(@PathVariable int id);

    @GetMapping(value = "/get", params = {"id"})
    ResponseEntity<Person> getPersonById(@RequestParam(name = "id") int id);

    @PostMapping(value = "/update")
    ResponseEntity<String>updatePerson(@RequestBody Person person);
}
