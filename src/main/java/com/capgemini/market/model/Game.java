package com.capgemini.market.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "available", nullable = false)
    private boolean isAvailable;

    public Game() {
    }
}
