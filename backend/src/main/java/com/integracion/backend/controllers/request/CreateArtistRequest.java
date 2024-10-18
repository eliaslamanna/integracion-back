package com.integracion.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistRequest {

    @NotBlank
    private String artisticName;

    private String legalOwner;

    private String bio;

    private List<String> socialMediaIds;

    private List<String> genreIds;

}