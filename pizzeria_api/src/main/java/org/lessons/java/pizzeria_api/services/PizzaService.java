package org.lessons.java.pizzeria_api.services;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pizzeria_api.models.OffertaSpeciale;
import org.lessons.java.pizzeria_api.models.Pizza;
import org.lessons.java.pizzeria_api.repositories.OffertaSpecialeRepository;
import org.lessons.java.pizzeria_api.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaSpecialeRepository offertaRepository;

    // metodo per trovare tutte le pizze
    public List<Pizza> findPizze() {
        return pizzaRepository.findAll();
    }

    // metodo per trovare le pizze per nome
    public List<Pizza> findPizzeByName(String nome) {
        return pizzaRepository.findByNameContaining(nome);
    }

    // metodo per trovare la pizza per id
    public Optional<Pizza> findPizzaById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getPizzaById(Integer id) {
        // gestisco la casistica in cui non venga trovata la risposta
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        return pizzaAttempt.get();
    }

    // salvataggio della pizza
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // aggiornamento della pizza
    public Pizza updatePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // cancellazione della pizza
    public void deletePizza(Pizza pizza) {
        for (OffertaSpeciale offerteDaEliminare : pizza.getOfferteSpeciali()) {
            offertaRepository.delete(offerteDaEliminare);
        }

        // cancello la pizza
        pizzaRepository.delete(pizza);
    }
}
