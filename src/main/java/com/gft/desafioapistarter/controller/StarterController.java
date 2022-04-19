package com.gft.desafioapistarter.controller;

import com.gft.desafioapistarter.model.Starters;
import com.gft.desafioapistarter.repository.StartersRepository;

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
public class StarterController {

    @Autowired
    private StartersRepository startersRepository;

    @GetMapping("/starters")
    public ResponseEntity<List<Starters>> listarTodos() {
        List<Starters> starters = new ArrayList<>();
        starters = startersRepository.findAll();
        return new ResponseEntity<>(starters, HttpStatus.OK);
    }

    @PostMapping("/starters")
    public ResponseEntity<Starters> adicionar(@RequestBody Starters starters) {
        startersRepository.save(starters);
        return new ResponseEntity<>(starters, HttpStatus.CREATED);
    }

    @GetMapping(path = "/starters/{id}")
    public ResponseEntity<Optional<Starters>> getById(@PathVariable Long id) {
        Optional<Starters> starters;
        try {
            starters = startersRepository.findById(id);
            return new ResponseEntity<Optional<Starters>>(starters, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Starters>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/starters/{id}")
    public ResponseEntity<Optional<Starters>> deletarById(@PathVariable Long id) {
        try {
            startersRepository.deleteById(id);
            return new ResponseEntity<Optional<Starters>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Starters>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/starters/{id}")
    public ResponseEntity<Starters> updateById(@PathVariable Long id, @RequestBody Starters newStarter) {
        return startersRepository.findById(id)
                .map(starters -> {
                    starters.setNome(newStarter.getNome());
                    starters.setCpf(newStarter.getCpf());
                    starters.setLetra(newStarter.getLetra());
                    starters.setEmail(newStarter.getEmail());
                    Starters StarterUpdate = startersRepository.save(starters);
                    return ResponseEntity.ok().body(StarterUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}

