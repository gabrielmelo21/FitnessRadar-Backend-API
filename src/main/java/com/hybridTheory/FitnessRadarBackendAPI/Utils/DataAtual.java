package com.hybridTheory.FitnessRadarBackendAPI.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataAtual {

    public String dataDiaAtual(){
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        return dataHoraAtual.format(formatter);
    }


}
