package com.integracion.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate date;

    @Column
    private String location;

    @Column
    private String address;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String city;

    @Column
    private String region;

    @Column
    private String country;

    @Column
    private String title;

    @Column
    private double duration;

    @Column
    private String observation;

    @Column(name="purchase_link")
    private String purchaseLink;

    @Column
    private boolean available;

    @Column
    private boolean visible;

    @ManyToOne
    private Artist artist;


}
