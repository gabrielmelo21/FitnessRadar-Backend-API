package com.hybridTheory.FitnessRadarBackendAPI.Repositories;

import com.hybridTheory.FitnessRadarBackendAPI.Models.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentosRepository extends JpaRepository<Alimentos, Long> {
}
