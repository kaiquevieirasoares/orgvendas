package com.orgvendas.domain.service;

import com.orgvendas.domain.dto.vendas.VendasCreateDto;
import com.orgvendas.domain.dto.vendedor.VendedorCreateDto;
import com.orgvendas.domain.entity.Vendas;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.mapper.VendasMapper;
import com.orgvendas.domain.repository.VendasRepository;
import com.orgvendas.domain.repository.VendedorRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class VendasServiceTest {

    @Autowired
    EntityManager entityManager;

    @Mock
    private VendasRepository vendasRepository;

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    @InjectMocks
    private VendasService vendasService;


    @Test
    @DisplayName("must create a sale")
    void createVendasTest() {
        LocalDate data_venda = LocalDate.parse("1999-10-10");
        VendasCreateDto vendasCreateDto = new VendasCreateDto(1500.0, data_venda, 1L);
        VendedorCreateDto vendedorCreateDto = new VendedorCreateDto("Samily");
        Vendedor vendedorExistente = this.createVendedor(vendedorCreateDto);
        when(vendedorRepository.findById(vendasCreateDto.vendedorId())).thenReturn(Optional.of(vendedorExistente));
        ResponseEntity<Vendedor> vendedorResponse = vendedorService.create(vendedorCreateDto);
        ResponseEntity<Object> vendaResponse = vendasService.create(vendasCreateDto);

        assertEquals(vendedorResponse.getStatusCode(), HttpStatus.CREATED);
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
        verify(vendedorRepository, times(1)).save(ArgumentMatchers.any(Vendedor.class));
        assertEquals(vendaResponse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void deleteVendaTest() {
        LocalDate data_venda = LocalDate.parse("1999-10-10");
        VendasCreateDto vendasCreateDto = new VendasCreateDto(1500.0, data_venda, 1L);
        Long vendaId = 1L;

        Vendas vendaExistente =(this.createVenda(vendasCreateDto));
        when(vendasRepository.findById(vendaId)).thenReturn(Optional.of(vendaExistente));
        ResponseEntity<Object> vendaDeleteResponse = vendasService.delete(vendaId);



        assertEquals(HttpStatus.NO_CONTENT, vendaDeleteResponse.getStatusCode());
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }


    private Vendedor createVendedor(VendedorCreateDto vendedorCreateDto){
        Vendedor vendedor = new Vendedor();
        BeanUtils.copyProperties(vendedorCreateDto, vendedor);
        this.entityManager.persist(vendedor);
        return vendedor;
    }

    private Vendas createVenda(VendasCreateDto vendasCreateDto){
        Vendas venda = new Vendas();
        BeanUtils.copyProperties(vendasCreateDto, venda);
        venda.setDataDaVenda(Date.valueOf("1999-06-08"));
        venda.setVendedorNome("kaique");
        VendedorCreateDto vendedorCreateDto = new VendedorCreateDto("Samily");
        Vendedor vendedor = createVendedor(vendedorCreateDto);
        venda.setVendedor(vendedor);
        this.entityManager.persist(venda);
        return venda;
    }

}