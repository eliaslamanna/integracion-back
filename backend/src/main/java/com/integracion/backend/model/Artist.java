package com.integracion.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="artist_id")
    private UUID artistId;

    @Column(name="artistic_name")
    private String artisticName;

    @Column(name="legal_owner")
    private String legalOwner;

    @Column(name="biography")
    private String bio;

    @Column(name="social_networks")
    private String socialNetworks;

    @Column()
    private String genres; //seria ideal que tenga una tabla a parte.

    @OneToMany(mappedBy = "artist")
    private List<Event> eventList;








}
