package com.orgvendas.domain.service;

import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.dto.vendedor.VendedorCreateDto;
import com.orgvendas.domain.dto.vendedor.VendedorDto;
import com.orgvendas.domain.dto.vendedor.VendedorUpdate;
import com.orgvendas.domain.mapper.VendedorMapper;
import com.orgvendas.domain.repository.VendedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    // Cria um vendedor
    public ResponseEntity<Vendedor> create(VendedorCreateDto vendedorCreateDto) {
        Vendedor newVendedor = new Vendedor();
        BeanUtils.copyProperties(vendedorCreateDto, newVendedor);
        vendedorRepository.save(newVendedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Retorna todos os vendedores
    public List<VendedorDto> getAll() {
        List<Vendedor> vendedorList = vendedorRepository.findAll();
        new VendedorMapper();
        return VendedorMapper.mapListVendedores(vendedorList);
    }

    //Atualiza os vendedores
    public ResponseEntity<Object> update(VendedorUpdate vendedorUpdate, Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            BeanUtils.copyProperties(vendedorUpdate, vendedor);
            vendedorRepository.save(vendedor);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    // busca por id
    public ResponseEntity<Object> getById(Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);
        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            VendedorDto vendedorDto = new VendedorMapper().mapVendedorDto(vendedor);
            return ResponseEntity.ok().body(vendedorDto);
        }
        return ResponseEntity.notFound().build();
    }

    //Deleta um vendedor
    public ResponseEntity<String> delete(Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedorRepository.deleteById(id);
            return ResponseEntity.ok().body("O(a) vendedor(a) " + vendedor.getNome()+ " foi deletado(a). ");
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
