package com.orgvendas.domain.mapper;

import com.orgvendas.domain.dto.vendas.VendasCreateDto;
import com.orgvendas.domain.entity.Vendas;
import com.orgvendas.domain.entity.Vendedor;

import java.sql.Date;

public class VendasMapper {

    public static Vendas createVendas(VendasCreateDto vendasCreateDto, Vendedor vendedor){
        Vendas venda = new Vendas();
        venda.setValor(vendasCreateDto.valor());
        venda.setDataDaVenda(Date.valueOf(vendasCreateDto.dataDeVenda()));
        venda.setVendedor(vendedor);
        venda.setVendedorNome(vendedor.getNome());
        return venda;
    }
}
