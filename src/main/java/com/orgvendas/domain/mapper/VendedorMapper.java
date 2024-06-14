package com.orgvendas.domain.mapper;

import com.orgvendas.domain.datahandler.ConvertDateToDays;
import com.orgvendas.domain.dto.vendedor.DatasDeVendasDto;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.dto.vendedor.VendedorDto;
import java.util.ArrayList;
import java.util.List;


public class VendedorMapper {

    public static List<VendedorDto> mapListVendedores(List<Vendedor> vendedores, DatasDeVendasDto datasDeVendasDto) {

        List<VendedorDto>  listVendedorDto = new ArrayList<>(List.of());
        if (!vendedores.isEmpty()) {
            new ConvertDateToDays();

            double days = ConvertDateToDays.calcularData(datasDeVendasDto);

            for (Vendedor vendedor : vendedores) {
                VendedorDto vendedorDto = new VendedorDto();
                double mediaDeVendas = vendedor.getTotalDeVendas()/days;
                vendedorDto.setNome(vendedor.getNome());
                vendedorDto.setMediaDeVendas(mediaDeVendas);
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
