package com.orgvendas.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.query.JpaCountQueryCreator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private int totalDeVendas;

    @Column
    private double mediaDeVendas;


}
