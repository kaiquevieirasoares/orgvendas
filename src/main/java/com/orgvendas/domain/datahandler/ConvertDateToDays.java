package com.orgvendas.domain.datahandler;

import com.orgvendas.domain.dto.vendedor.DatasDeVendasDto;
import java.time.Duration;
import java.time.LocalDate;

public class ConvertDateToDays {


    public static long calcularData(DatasDeVendasDto datasDeVendasDto){
        LocalDate dataInicial = LocalDate.parse(datasDeVendasDto.dataInit().toString());
        LocalDate dataFinal = LocalDate.parse(datasDeVendasDto.dataFim().toString());

        Duration duration = Duration.between(dataInicial.atStartOfDay(), dataFinal.atStartOfDay());
        long days = duration.toDays();
        Math.abs(days);
        return days;
    }
}
