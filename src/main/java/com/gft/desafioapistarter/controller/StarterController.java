package com.gft.desafioapistarter.controller;

import com.gft.desafioapistarter.model.Starters;
import com.gft.desafioapistarter.repository.StartersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/starters")
public class StarterController {

    @Autowired
    private StartersRepository startersRepository;

    @GetMapping
    public List<Starters> listar() {
        return startersRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Starters adicionar(@RequestBody Starters starters) {
        return startersRepository.save(starters);
    }
}
