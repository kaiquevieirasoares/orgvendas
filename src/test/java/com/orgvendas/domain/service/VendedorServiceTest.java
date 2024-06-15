package com.orgvendas.domain.service;

import com.orgvendas.domain.datahandler.ConvertDateToDays;
import com.orgvendas.domain.dto.vendedor.DatasDeVendasDto;
import com.orgvendas.domain.dto.vendedor.VendedorCreateDto;
import com.orgvendas.domain.dto.vendedor.VendedorDto;
import com.orgvendas.domain.dto.vendedor.VendedorUpdateDto;
import com.orgvendas.domain.entity.Vendedor;
import com.orgvendas.domain.repository.VendedorRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class VendedorServiceTest {

    @Autowired
    EntityManager entityManager;

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;


    @Test
    @DisplayName("test whether the create method was successful.")
    public void testCreate() {
        VendedorCreateDto vendedorCreateDto = new VendedorCreateDto("Samily");

        ResponseEntity<Vendedor> response = vendedorService.create(vendedorCreateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    @DisplayName("Tests whether the getAll method is successful.")
    void testGetAll() {
        LocalDate data1 = LocalDate.parse("2024-06-04");
        LocalDate data2 = LocalDate.parse("2024-06-06");

        DatasDeVendasDto datasDeVendasDto = new DatasDeVendasDto(data1,data2);
        VendedorCreateDto vendedorKaique = new VendedorCreateDto("Kaique");

        Vendedor vendedor1 = this.createVendedor(vendedorKaique);
        Vendedor vendedor2 = this.createVendedor(vendedorKaique);
        List<Vendedor> vendedorList = Arrays.asList(vendedor1, vendedor2);
        this.mapListVendedores(vendedorList, datasDeVendasDto);
        when(vendedorRepository.findAll()).thenReturn(vendedorList);
        List<VendedorDto> result = vendedorService.getAll(datasDeVendasDto);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Tests whether the update method is successful.")
    void testUpdate() {
        Long vendedorId = 1L;

        VendedorUpdateDto dadosDeAtualizacao  = new VendedorUpdateDto("Kaique");
        VendedorCreateDto vendedoraSamily = new VendedorCreateDto("Samily");

        Vendedor vendedorExistente = this.createVendedor(vendedoraSamily);

        when(vendedorRepository.findById(vendedorId)).thenReturn(Optional.of(vendedorExistente));

        ResponseEntity<Object> response = vendedorService.update(dadosDeAtualizacao, vendedorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Kaique", vendedorExistente.getNome());

        verify(vendedorRepository, times(1)).save(vendedorExistente);
    }

    @Test
    @DisplayName("tests whether the getById method was successful.")
    void testGetById() {
        Long vendedorId = 1L;
        Vendedor vendedor = new Vendedor();
        when(vendedorRepository.findById(vendedorId)).thenReturn(Optional.of(vendedor));
        ResponseEntity<Object> response = vendedorService.getById(vendedorId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vendedor.getNome(), ((VendedorDto) response.getBody()).getNome());
    }

    @Test
    @DisplayName("Tests whether the delete method was successful")
    void testDelete() {
        VendedorCreateDto vendedorCreateDto = new VendedorCreateDto("Kaique");
        Vendedor vendedor = this.createVendedor(vendedorCreateDto);
        Long vendedorId = 1L;

        when(vendedorRepository.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        ResponseEntity<String> response = vendedorService.delete(vendedorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("O(a) vendedor(a) Kaique foi deletado(a). ", response.getBody());
        verify(vendedorRepository, times(1)).deleteById(vendedorId);
    }

    private Vendedor createVendedor(VendedorCreateDto vendedorCreateDto){
        Vendedor vendedor = new Vendedor();
        BeanUtils.copyProperties(vendedorCreateDto, vendedor);
        this.entityManager.persist(vendedor);
        return vendedor;
    }


    private List<VendedorDto> mapListVendedores(List<Vendedor> vendedores, DatasDeVendasDto datasDeVendasDto) {

        List<VendedorDto> listVendedorDto = new ArrayList<>(List.of());
        if (!vendedores.isEmpty()) {
            new ConvertDateToDays();

            double days = ConvertDateToDays.calcularData(datasDeVendasDto);

            for (Vendedor vendedor : vendedores) {
                VendedorDto vendedorDto = new VendedorDto();
                double mediaDeVendas = vendedor.getTotalDeVendas() / days;
                vendedorDto.setNome(vendedor.getNome());
                vendedorDto.setMediaDeVendas(mediaDeVendas);
                vendedorDto.setTotalDeVendas(vendedor.getTotalDeVendas());
                listVendedorDto.add(vendedorDto);
            }

        }
        return listVendedorDto;
    }
}