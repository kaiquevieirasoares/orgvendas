package com.orgvendas.domain.service;

import com.orgvendas.domain.dto.vendedor.DatasDeVendasDto;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.dto.vendedor.VendedorCreateDto;
import com.orgvendas.domain.dto.vendedor.VendedorDto;
import com.orgvendas.domain.dto.vendedor.VendedorUpdateDto;
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

    //Retorna todos os vendedores com suas respectivas medias
    public List<VendedorDto> getAll(DatasDeVendasDto datasDeVendasDto) {

        List<Vendedor> vendedorList = vendedorRepository.findAll();
        new VendedorMapper();
        return VendedorMapper.mapListVendedores(vendedorList, datasDeVendasDto);
    }

    //Atualiza os vendedores
    public ResponseEntity<Object> update(VendedorUpdateDto vendedorUpdateDto, Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            BeanUtils.copyProperties(vendedorUpdateDto, vendedor);
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
            if(vendedor.getTotalDeVendas()>0){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Para deletar esse(a) vendedor(a) é necessário que as vendas associadas a ele(a) sejam tranferidas ou deletadas");
            }else{
                vendedorRepository.deleteById(id);
                return ResponseEntity.ok().body("O(a) vendedor(a) " + vendedor.getNome()+ " foi deletado(a). ");
            }

        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
