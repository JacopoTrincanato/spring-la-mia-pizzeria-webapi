package org.lessons.java.pizzeria_api.repositories;

import java.util.List;

import org.lessons.java.pizzeria_api.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    // creo il metodo per trovare la pizza per titolo
    public List<Pizza> findByNomeContaining(String nome);
}
