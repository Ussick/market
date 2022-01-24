package com.capgemini.market.repository;

import com.capgemini.market.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person checkPerson(String login, String password);
}
