package org.lessons.java.pizzeria_api.repositories;

import org.lessons.java.pizzeria_api.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
