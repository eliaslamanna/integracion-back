package com.integracion.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
@Data // genera setters y getters *
@AllArgsConstructor // genera un constructor con todos los atributos *
@NoArgsConstructor // genera otro constructor sin ningun atributo *

// * todas las implementaciones van por logica interna

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // GenerationType. da las opciones del tipo de ID
    private UUID userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, mappedBy = "user")
    private List<Follower> userFollowers;
}
