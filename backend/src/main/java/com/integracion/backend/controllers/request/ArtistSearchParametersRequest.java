package com.integracion.backend.controllers.request;

import com.integracion.backend.model.Follower;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistSearchParametersRequest {

    private String artisticName;

}