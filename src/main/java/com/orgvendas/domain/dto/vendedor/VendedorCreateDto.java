package com.orgvendas.domain.dto.vendedor;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record VendedorCreateDto(@NotBlank @JsonProperty("nome") String nome){ }
