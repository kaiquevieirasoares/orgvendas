package com.orgvendas.domain.service;

import com.orgvendas.domain.dto.vendas.VendasCreateDto;
import com.orgvendas.domain.entity.Vendas;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.mapper.VendasMapper;
import com.orgvendas.domain.repository.VendasRepository;
import com.orgvendas.domain.repository.VendedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendasService {
    private VendasRepository vendasRepository;
    private VendedorRepository vendedorRepository;


    public VendasService(VendasRepository vendasRepository, VendedorRepository vendedorRepository) {
        this.vendasRepository = vendasRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public ResponseEntity<Object> create(VendasCreateDto vendasCreateDto) {
        Long id = vendasCreateDto.vendedorId();
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedor.setTotalDeVendas(vendedor.getTotalDeVendas() + 1);

            Vendas venda =  VendasMapper.createVendas(vendasCreateDto,vendedor);

            vendasRepository.save(venda);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> delete(Long vendas_id) {
        Optional<Vendas> vendasOptional = vendasRepository.findById(vendas_id);
        if (vendasOptional.isPresent()) {
            Vendas venda = vendasOptional.get();
            Vendedor vendedor = venda.getVendedor();
            vendedor.setTotalDeVendas(vendedor.getTotalDeVendas() - 1);
            vendedorRepository.save(vendedor);
            vendasRepository.delete(venda);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
