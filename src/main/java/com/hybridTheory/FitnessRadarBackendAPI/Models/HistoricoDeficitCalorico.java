package com.hybridTheory.FitnessRadarBackendAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "historico_deficit_calorico")
public class HistoricoDeficitCalorico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "deficit_calorico", length = 10)
    private Long deficit_calorico;

    @Column(name = "data_dia", length = 6)
    private String data_dia;

}
