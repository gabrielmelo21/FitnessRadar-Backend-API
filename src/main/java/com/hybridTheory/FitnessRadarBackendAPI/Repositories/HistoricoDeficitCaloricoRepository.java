package com.hybridTheory.FitnessRadarBackendAPI.Repositories;


import com.hybridTheory.FitnessRadarBackendAPI.Models.HistoricoDeficitCalorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoDeficitCaloricoRepository extends JpaRepository<HistoricoDeficitCalorico, Long> {
}
