package com.integracion.backend.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CreateEventRequest {

    @NotNull
    private LocalDate date;

    @NotBlank
    private String location;

    @NotBlank
    private String address;

    private String latitude;

    private String longitude;

    @NotBlank
    private String city;

    private String region;

    @NotBlank
    private String country;

    @NotBlank
    private String title;

    @NotNull
    private Double duration;

    private String observation;

    private String purchaseLink;

    private boolean available;

    private boolean visible;

    private List<String> artistIds = new ArrayList<>();

}