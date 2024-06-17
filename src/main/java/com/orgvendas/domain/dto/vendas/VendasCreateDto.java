package com.orgvendas.domain.dto.vendas;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record VendasCreateDto(double valor, @JsonProperty("data_venda") LocalDate dataDeVenda, @JsonProperty("vendedor_id") Long vendedorId) {
}
