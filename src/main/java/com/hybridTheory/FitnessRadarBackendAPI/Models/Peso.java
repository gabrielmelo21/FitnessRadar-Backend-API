package com.hybridTheory.FitnessRadarBackendAPI.Models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "peso")
public class Peso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "peso", length = 10, nullable = false)
    private Double peso;

    @Column(name = "peso_objetivo", length = 10, nullable = false)
    private Double peso_objetivo;

    @Column(name = "data_dia", length = 6)
    private String data_dia;


}
