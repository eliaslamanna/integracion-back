package com.integracion.backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name="follower")
@Entity
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Artist artist;

}
