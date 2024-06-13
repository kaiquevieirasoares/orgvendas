package com.orgvendas.domain.service;

import com.orgvendas.domain.Vendedor;
import com.orgvendas.domain.dto.VendedorCreateDto;
import com.orgvendas.domain.repository.VendedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public ResponseEntity<Vendedor> create(VendedorCreateDto vendedorCreateDto) {
        Vendedor newVendedor = new Vendedor();
        BeanUtils.copyProperties(vendedorCreateDto, newVendedor);
        vendedorRepository.save(newVendedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
