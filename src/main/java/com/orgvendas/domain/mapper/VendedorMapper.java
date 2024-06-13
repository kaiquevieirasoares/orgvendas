package com.orgvendas.domain.mapper;

import com.orgvendas.domain.dto.vendas.VendasCreateDto;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.dto.vendedor.VendedorDto;
import com.orgvendas.domain.repository.VendedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
