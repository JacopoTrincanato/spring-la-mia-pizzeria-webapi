package org.lessons.java.pizzeria_api.controllers;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pizzeria_api.models.Pizza;
import org.lessons.java.pizzeria_api.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pizze")
public class RestPizzaController {

    @Autowired
    private PizzaService pizzaService;

    // index
    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizze = pizzaService.findPizze();
        return pizze;
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@Valid @PathVariable Integer id) {

        Optional<Pizza> pizzaAttempt = pizzaService.findPizzaById(id);

        // controllo se pizzaAttempt è vuoto
        if (pizzaAttempt.isEmpty()) {
            // se non è presente, invio una 404
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        // altrimenti mostro la pizza
        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);

    }

    // store
    @PostMapping
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza) {

        // creo il nuovo libro
        return new ResponseEntity<Pizza>(pizzaService.createPizza(pizza), HttpStatus.OK);
    }
}
