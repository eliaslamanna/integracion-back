package com.integracion.backend.controllers.request;

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

    private List<String> socialNetworkIds;

    private List<String> genreIds;

}