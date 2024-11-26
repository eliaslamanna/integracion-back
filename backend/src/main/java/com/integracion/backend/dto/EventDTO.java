package com.integracion.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;

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

    private List<String> artistNames;

}