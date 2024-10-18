package com.integracion.backend.controllers.request;

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
public class UpdateArtistRequest {

    private String artisticName;

    private String legalOwner;

    private String bio;

    private List<String> socialNetworkIds = new ArrayList<>();

    private List<String> genreIds = new ArrayList<>();

}