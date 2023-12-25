package com.hybridTheory.FitnessRadarBackendAPI.DTO;

import jakarta.validation.constraints.NotBlank;

public record AlimentosDTO(Long id, @NotBlank String alimento, @NotBlank Long calorias) {
    public Long getId() {
        return this.id;
    }
}
