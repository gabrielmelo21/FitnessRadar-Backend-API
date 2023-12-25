package com.hybridTheory.FitnessRadarBackendAPI.DTO;

import jakarta.validation.constraints.NotBlank;

public record CaloriasDTO(@NotBlank Long calorias_atual, Long tmb, Long deficit_calorico, String data_dia ) {
}
