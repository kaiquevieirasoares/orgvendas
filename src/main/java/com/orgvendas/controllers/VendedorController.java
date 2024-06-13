package com.orgvendas.controllers;

import com.orgvendas.domain.Vendedor;
import com.orgvendas.domain.dto.VendedorCreateDto;
import com.orgvendas.domain.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendedor")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public String index() {
        return "Vendedor";
    }

    @PostMapping("/post")
    public ResponseEntity<Vendedor> createVendedor(@RequestBody VendedorCreateDto vendedorCreateDto) {
        if (vendedorCreateDto.nome()==null || vendedorCreateDto.nome().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return vendedorService.create(vendedorCreateDto);
        }


    }
}
