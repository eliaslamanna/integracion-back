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

    private String email;

    @ManyToMany
    @JoinTable(
            name = "artist_genre",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "artist_social_media",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "social_media_id")
    )
    private List<SocialMedia> socialMedia;

    @ManyToMany
    @JoinTable(
            name = "artist_event",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventList;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artisticName='" + artisticName + '\'' +
                ", legalOwner='" + legalOwner + '\'' +
                ", bio='" + bio + '\'' +

                '}';
    }
}