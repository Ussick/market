package com.capgemini.market.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    @Test
    void test(){
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        Mockito.when(personRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), personRepository.findAll());
    }
}