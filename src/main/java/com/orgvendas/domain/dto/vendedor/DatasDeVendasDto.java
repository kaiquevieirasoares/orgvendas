package com.orgvendas.domain.dto.vendedor;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record DatasDeVendasDto(@JsonProperty("data-inicial") LocalDate dataInit, @JsonProperty("data-fim")LocalDate dataFim ) {
}
