package com.integracion.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CreateArtistRequest {

    @NotBlank
    private String artisticName;

    private String legalOwner;

    private String bio;

    private List<String> socialMediaIds = new ArrayList<>();

    private List<String> genreIds = new ArrayList<>();

    private List<String> imageUrls = new ArrayList<>();

    private String email;

}