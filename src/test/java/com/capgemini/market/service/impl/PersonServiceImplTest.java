package com.capgemini.market.service.impl;

import com.capgemini.market.model.Person;

import com.capgemini.market.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplTest {
    @Autowired
    PersonService personService;

    public Person victim = new Person();

    @BeforeEach
    public void setVictim() {
        victim.setId(1);
        victim.setStatus("user");
        victim.setName("Bill");
        victim.setUsername("duraley@gmail.com");
        victim.setPassword("0f0f891485d88db63b561763120689cd70e6dc27e73e85ea8b69332b7bdfc36d");
    }

    @Test
    void isNullOrEmptyPersonAdded() {
        Person person = new Person();
        assertFalse(personService.addPerson(person));
        assertFalse(personService.addPerson(null));
    }

    @Test
    void isPersonAdded() {
        Person personToAdd = new Person();
        personToAdd.setPassword("2");
        personToAdd.setUsername("gygy@mymail.mail");
        personToAdd.setStatus("user");
        personToAdd.setName("Vasya");
        assertTrue(personService.addPerson(personToAdd));
        personService.deletePerson(personToAdd.getId());
    }


    @Test
    void tryToFindAll() {
        List<Person> expected = List.of(victim);
        assertEquals(expected, personService.findAll());
    }


    @Test
    void isGetById() {
        assertEquals(victim, personService.getById(victim.getId()));
    }

    @Test
    void isPersonChecked() {
        String expected = "Bill, you have successfully logged in as a user.";
//        assertEquals(expected, personService.checkPerson("duraley@gmail.com", "1"));
    }

    @Test
    void isPersonChanged() {
        String expected = "User with id 1 has been updated";
        victim.setPassword("1");
        assertEquals(expected, personService.changedPerson(victim));
    }

    @Test
    void isNullorEmptyPersonChanged() {
        String expected = "This user doesn't exist";
        victim.setUsername("buraley@gmail.com");
        assertEquals(expected, personService.changedPerson(null));
        assertEquals(expected, personService.changedPerson(new Person()));
        assertEquals(expected, personService.changedPerson(victim));
    }

    @Test
    void deletePerson() {
        assertEquals("User with id 2 doesn't exist", personService.deletePerson(2));
    }
}