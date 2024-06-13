package com.orgvendas.controllers;

import com.orgvendas.domain.Vendedor;
import com.orgvendas.domain.dto.VendedorCreateDto;
import com.orgvendas.domain.dto.VendedorUpdate;
import com.orgvendas.domain.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendedor")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return vendedorService.getById(id);
    }

    @GetMapping
    public List<Vendedor> getAll() {
        return vendedorService.getAll();
    }

    @PostMapping("/post")
    public ResponseEntity<Vendedor> createVendedor(@RequestBody VendedorCreateDto vendedorCreateDto) {
        if (vendedorCreateDto.nome()==null || vendedorCreateDto.nome().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return vendedorService.create(vendedorCreateDto);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id ,@RequestBody VendedorUpdate vendedorUpdate) {

        if (vendedorUpdate.nome()==null || vendedorUpdate.nome().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return vendedorService.update(vendedorUpdate, id);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return vendedorService.delete(id);
    }
}
