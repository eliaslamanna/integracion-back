package com.integracion.backend.model;

import jakarta.persistence.*;

@Table(name="follower")
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Artist artist;
}
