package com.orgvendas.controllers;

import com.orgvendas.domain.dto.vendas.VendasCreateDto;
import com.orgvendas.domain.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendas")
public class VendasController {
    @Autowired
    private VendasService vendasService;

    @GetMapping
    public String index() {
        return "Vendas";
    }

    @PostMapping("/post")
    public ResponseEntity<Object> post(@RequestBody VendasCreateDto vendasCreateDto) {
        if ((vendasCreateDto.dataDeVenda() == null) || (vendasCreateDto.valor() == 0) ||
                (vendasCreateDto.vendedorId() == null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
             return vendasService.create(vendasCreateDto);
        }
    }


}
