package com.orgvendas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendedorDto {
    private String nome;
    private int totalDeVendas;
    private double mediaDeVendas;
}
