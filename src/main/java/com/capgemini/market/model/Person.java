package com.capgemini.market.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persons")
@NamedQuery(name = "Person.checkPerson", query = "SELECT person " +
        "FROM Person person WHERE person.login=: login and person.password=: password")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private String status;

    public Person() {
    }
}
