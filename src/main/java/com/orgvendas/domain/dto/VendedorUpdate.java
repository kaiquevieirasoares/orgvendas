package com.orgvendas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record VendedorUpdate(@NotBlank @JsonProperty("nome")  String nome) {

}
