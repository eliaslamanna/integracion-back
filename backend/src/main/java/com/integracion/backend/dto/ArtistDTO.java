package com.integracion.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    private UUID artistId;

    private String artisticName;

    private String legalOwner;

    private String bio;

    private List<SocialMediaDTO> socialNetworks;

    private List<GenreDTO> genres;

    private List<EventDTO> eventList;

    private List<ImageDTO> images;

}