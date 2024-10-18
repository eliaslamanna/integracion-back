package com.integracion.backend.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {

    private LocalDate date;

    private String location;

    private String address;

    private String latitude;

    private String longitude;

    private String city;

    private String region;

    private String country;

    private String title;

    private double duration;

    private String observation;

    private String purchaseLink;

    private boolean available;

    private boolean visible;

}