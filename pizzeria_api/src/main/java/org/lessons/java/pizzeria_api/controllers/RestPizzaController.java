package org.lessons.java.pizzeria_api.controllers;

import java.util.List;

import org.lessons.java.pizzeria_api.models.Pizza;
import org.lessons.java.pizzeria_api.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizze")
public class RestPizzaController {

    @Autowired
    private PizzaService pizzaService;

    // index
    public List<Pizza> index() {
        List<Pizza> pizze = pizzaService.findPizze();
        return pizze;
    }
}
