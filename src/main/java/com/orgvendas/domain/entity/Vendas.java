package com.orgvendas.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double valor;

    @Column
    private Date dataDaVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    @Column
    private String vendedorNome;
}
