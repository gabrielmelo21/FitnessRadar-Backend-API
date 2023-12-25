package com.hybridTheory.FitnessRadarBackendAPI.DTO;

import jakarta.validation.constraints.NotBlank;

public record PesoDTO(@NotBlank Double peso, @NotBlank Double peso_objetivo, @NotBlank String data_dia ) {
}
