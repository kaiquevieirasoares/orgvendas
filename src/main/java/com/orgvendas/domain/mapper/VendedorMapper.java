package com.orgvendas.domain.mapper;

import com.orgvendas.domain.Vendedor;
import com.orgvendas.domain.dto.VendedorDto;

import java.util.ArrayList;
import java.util.List;

public class VendedorMapper {

    public static List<VendedorDto> mapListVendedores(List<Vendedor> vendedores) {
        List<VendedorDto>  listVendedorDto = new ArrayList<>(List.of());
        if (!vendedores.isEmpty()) {
            for (Vendedor vendedor : vendedores) {
                VendedorDto vendedorDto = new VendedorDto();
                vendedorDto.setNome(vendedor.getNome());
                vendedorDto.setMediaDeVendas(vendedor.getMediaDeVendas());
                vendedorDto.setTotalDeVendas(vendedor.getTotalDeVendas());
                listVendedorDto.add(vendedorDto);
            }

        }
        return listVendedorDto;
    }

    public static VendedorDto mapVendedorDto(Vendedor vendedor) {
        VendedorDto vendedorDto = new VendedorDto();
        vendedorDto.setNome(vendedor.getNome());
        vendedorDto.setMediaDeVendas(vendedor.getMediaDeVendas());
        vendedorDto.setTotalDeVendas(vendedor.getTotalDeVendas());
        return vendedorDto;
    }
}
