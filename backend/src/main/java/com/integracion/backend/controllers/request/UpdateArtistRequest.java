package com.integracion.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistRequest {

    private String artisticName;

    private String legalOwner;

    private String bio;

    private String socialNetworks;

    private String genres;

}