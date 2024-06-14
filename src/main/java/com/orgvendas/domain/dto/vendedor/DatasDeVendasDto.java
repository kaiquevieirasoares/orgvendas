package com.orgvendas.domain.dto.vendedor;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;

public record DatasDeVendasDto(@JsonProperty("data-inicial") Date dataInit,@JsonProperty("data-fim")Date dataFim ) {
}
