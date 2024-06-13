package com.orgvendas.domain.repository;

import com.orgvendas.domain.entity.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
