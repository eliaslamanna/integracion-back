package com.integracion.backend.controllers.request;

import com.integracion.backend.model.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistRequest {

    @NotBlank
    private String artisticName;

    private String legalOwner;

    private String bio;

    private List<String> socialNetworks;

    private String genres;

}