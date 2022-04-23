package com.gft.desafioapistarter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String Tecnologia;

    @Column(nullable = false)
    private String Turma;

}
