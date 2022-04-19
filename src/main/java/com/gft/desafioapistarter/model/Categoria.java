package com.gft.desafioapistarter.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String Tecnologia;

    @Column(nullable = false)
    private String Turma;

}
