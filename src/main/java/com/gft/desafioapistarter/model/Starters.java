package com.gft.desafioapistarter.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Starters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private Long cpf;

    @Column
    private String Letra;

    @Column
    private String Email;


}
