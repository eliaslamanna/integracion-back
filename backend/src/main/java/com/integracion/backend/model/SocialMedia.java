package com.integracion.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "social_media")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "platform")
    private String platform;

    @Column(name = "url")
    private String url;

    @ManyToMany(mappedBy = "socialMedia")
    private List<Artist> artists;

}