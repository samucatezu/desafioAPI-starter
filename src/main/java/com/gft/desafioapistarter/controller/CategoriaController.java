package com.gft.desafioapistarter.controller;


import com.gft.desafioapistarter.model.Categoria;
import com.gft.desafioapistarter.model.Starters;
import com.gft.desafioapistarter.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

//    @GetMapping
//    public List<Categoria> listar() {
//        return categoriaRepository.findAll();
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Categoria adicionar(@RequestBody Categoria categoria) {
//        return categoriaRepository.save(categoria);
//    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarTodos() {
        List<Categoria> categoria = new ArrayList<>();
        categoria = categoriaRepository.findAll();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/categoria")
    public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @GetMapping(path = "/categoria/{id}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long id) {
        Optional<Categoria> categoria;
        try {
            categoria = categoriaRepository.findById(id);
            return new ResponseEntity<Optional<Categoria>>(categoria, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Categoria>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/categoria/{id}")
    public ResponseEntity<Optional<Categoria>> deletarById(@PathVariable Long id) {
        try {
            categoriaRepository.deleteById(id);
            return new ResponseEntity<Optional<Categoria>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Categoria>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/categoria/{id}")
    public ResponseEntity<Categoria> updateById(@PathVariable Long id, @RequestBody Categoria newCategoria) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setTecnologia(newCategoria.getTecnologia());
                    categoria.setTurma(newCategoria.getTurma());
                    Categoria categoriaUpdate = categoriaRepository.save(categoria);
                    return ResponseEntity.ok().body(categoriaUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

}
