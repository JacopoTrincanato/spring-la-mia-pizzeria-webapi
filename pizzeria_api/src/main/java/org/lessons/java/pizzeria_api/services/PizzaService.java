package org.lessons.java.pizzeria_api.services;

import org.lessons.java.pizzeria_api.repositories.OffertaSpecialeRepository;
import org.lessons.java.pizzeria_api.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;
}
