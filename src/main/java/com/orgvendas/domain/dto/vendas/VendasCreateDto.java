package com.orgvendas.domain.dto.vendas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public record VendasCreateDto(double valor, @JsonProperty("data_venda") Date dataDeVenda,@JsonProperty("vendedor_id") Long vendedorId) {
}
