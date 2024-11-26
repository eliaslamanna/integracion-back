package com.integracion.backend.controllers.request;

import jakarta.persistence.Column;
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

    private LocalDate fecha;


    private int estadio;


    private double cantidadSectorGeneral;


    private double precioGeneralBEAT;


    private double cantidadSectorIzquierda;


    private double precioIzquierdaBEAT;


    private double cantidadSectorDerecha;


    private double precioDerechaBEAT;


    private String imagenPrincipal;


    private boolean habilitado;


    private double cantidadSectorVip;


    private double precioVipBEAT;

    private List<String> artistIds = new ArrayList<>();

}