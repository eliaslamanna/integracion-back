package com.integracion.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha;

    @Column
    private int estadio;

    @Column
    private double cantidadSectorGeneral;

    @Column
    private double precioGeneralBEAT;

    @Column
    private double cantidadSectorIzquierda;

    @Column
    private double precioIzquierdaBEAT;

    @Column
    private double cantidadSectorDerecha;

    @Column
    private double precioDerechaBEAT;

    @Column
    private String imagenPrincipal;

    @Column
    private boolean habilitado;

    @Column
    private double cantidadSectorVip;

    @Column
    private double precioVipBEAT;

    @Column(name="purchase_link")
    private String purchaseLink;


    @ManyToMany
    @JoinTable(
            name = "artist_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

}