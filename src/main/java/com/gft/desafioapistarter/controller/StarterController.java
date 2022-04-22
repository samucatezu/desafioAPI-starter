package com.gft.desafioapistarter.controller;

import com.gft.desafioapistarter.model.Starters;
import com.gft.desafioapistarter.repository.StartersRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Sort.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/starters")
@Api("CRUD endpoint de starters ")
public class StarterController {

    @Autowired
    private StartersRepository startersRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping
    @ApiOperation("Retorna uma lista com todos os Starters cadastrados")
    public ResponseEntity<List<Starters>> listarTodos() {
        List<Starters> starters = new ArrayList<>();
        starters = startersRepository.findAll();
        return new ResponseEntity<>(starters, HttpStatus.OK);
    }

    @GetMapping("/desc")
    @ApiOperation("Retorna uma lista com todos os Starters cadastrados em ascendente")
    public ResponseEntity<List<Starters>> listarTodosDesc(@RequestParam(defaultValue = "nome,desc") String[] sort) {
        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Starters> Starters = startersRepository.findAll(Sort.by(orders));

            if (Starters.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Starters, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ApiOperation("Adicionar Starters para a lista")
    public ResponseEntity<Starters> adicionar(@RequestBody Starters starters) {
        startersRepository.save(starters);
        return new ResponseEntity<>(starters, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @ApiOperation("Retorna um Starter cadastrado pelo id")
    public ResponseEntity<Optional<Starters>> getById(@PathVariable Long id) {
        Optional<Starters> starters;
        try {
            starters = startersRepository.findById(id);
            return new ResponseEntity<Optional<Starters>>(starters, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Starters>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    @ApiOperation("Deleta um Starter cadastrado pelo id")
    public ResponseEntity<Optional<Starters>> deletarById(@PathVariable Long id) {
        try {
            startersRepository.deleteById(id);
            return new ResponseEntity<Optional<Starters>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Starters>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "id}")
    @ApiOperation("Edita um Starter cadastrado pelo id")
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

