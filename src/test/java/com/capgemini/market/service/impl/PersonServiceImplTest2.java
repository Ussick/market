package com.capgemini.market.service.impl;

import com.capgemini.market.repository.PersonRepository;
import com.capgemini.market.service.PersonService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplTest2 {
    @Autowired
    PersonService personService;


    @Test
    void findAll() {
        Mockito.when(personService.findAll()).thenReturn(null);
    }
}