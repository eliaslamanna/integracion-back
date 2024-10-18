package com.integracion.backend.dto;

import com.integracion.backend.model.Event;
import jakarta.persistence.*;
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

    private String socialNetworks;

    private String genres;

    private List<Event> eventList;

}