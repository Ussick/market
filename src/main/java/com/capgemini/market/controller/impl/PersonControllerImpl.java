package com.capgemini.market.controller.impl;

import com.capgemini.market.controller.PersonController;
import com.capgemini.market.model.Person;
import com.capgemini.market.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class PersonControllerImpl implements PersonController {
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<String> checkPerson(String login, String password) {
        return new ResponseEntity<>(personService.checkPerson(login, password), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> insertPerson(Person person) {
        Boolean isInsert = personService.addPerson(person);
        return new ResponseEntity<>(isInsert, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> all = personService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deletePerson(int id) {
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> updatePerson(Person person) {
        return new ResponseEntity<>(personService.changedPerson(person), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Person> getPersonById(int id) {
        Person resultPerson = personService.getById(id);
        return new ResponseEntity<>(resultPerson, HttpStatus.OK);
    }
}
