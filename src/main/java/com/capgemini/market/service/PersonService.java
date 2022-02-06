package com.capgemini.market.service;

import com.capgemini.market.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    Boolean addPerson(Person person);

//    String checkPerson(String login, String password);

    List<Person> findAll();

    Person getById(int id);

    String deletePerson(int id);

    String changedPerson(Person person);
}
