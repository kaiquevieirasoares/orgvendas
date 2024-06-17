package com.orgvendas.controllers;

import com.orgvendas.domain.dto.vendedor.DatasDeVendasDto;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.dto.vendedor.VendedorCreateDto;
import com.orgvendas.domain.dto.vendedor.VendedorUpdateDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return vendedorService.getById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestBody DatasDeVendasDto datasDeVendasDto) {
        if (datasDeVendasDto.dataInit() == null|| datasDeVendasDto.dataFim() == null){
            return ResponseEntity.badRequest().body("Há algo errado no body do request");

        } else{

            return ResponseEntity.ok(vendedorService.getAll(datasDeVendasDto));
        }



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
    public ResponseEntity<Object> update(@PathVariable Long id ,@RequestBody VendedorUpdateDto vendedorUpdateDto) {

        if (vendedorUpdateDto.nome()==null || vendedorUpdateDto.nome().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return vendedorService.update(vendedorUpdateDto, id);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        

        return vendedorService.delete(id);
    }
}
