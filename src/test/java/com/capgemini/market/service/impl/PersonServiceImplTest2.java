package com.capgemini.market.service.impl;

import com.capgemini.market.model.Person;
import com.capgemini.market.repository.PersonRepository;
import com.capgemini.market.service.PersonService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PersonServiceImplTest2 {


    PersonService personService;

    PersonRepository personRepository;



    @BeforeEach
    void setRepository(){
        personRepository = Mockito.mock(PersonRepository.class);
        PersonServiceImpl personServiceImpl = new PersonServiceImpl();
        personServiceImpl.setPersonRepository(personRepository);
        personService = personServiceImpl;
    }

    @Test
    void findAll() {
        Mockito.when(personRepository.findAll()).thenReturn(List.of(new Person()));
        List <Person> expected = List.of(new Person());
        assertEquals(expected, personService.findAll());
    }
}