package com.hybridTheory.FitnessRadarBackendAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alimentos")
public class Alimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "alimento", length = 40, nullable = false, unique = true)
    private String alimento;

    @Column(name = "calorias", length = 10, nullable = false)
    private Long calorias;


}
