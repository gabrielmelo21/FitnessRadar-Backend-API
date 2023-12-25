package com.hybridTheory.FitnessRadarBackendAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "calorias")
public class Calorias {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "calorias_atual", length = 10, nullable = false)
    private Long calorias_atual;

    @Column(name = "tmb", length = 10)
    private Long tmb;

    @Column(name = "deficit_calorico", length = 10)
    private Long deficit_calorico;

    @Column(name = "data_dia", length = 6)
    private String data_dia;




}
