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

    @OneToMany(mappedBy = "artist")
    private List<Genre> genres;

    @OneToMany(mappedBy = "artist")
    private List<SocialMedia> socialMedia;

    @ManyToMany(mappedBy = "artists")
    private List<Event> eventList;

}