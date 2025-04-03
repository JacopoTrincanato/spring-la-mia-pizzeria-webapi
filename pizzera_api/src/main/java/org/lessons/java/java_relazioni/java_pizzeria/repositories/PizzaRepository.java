package org.lessons.java.java_relazioni.java_pizzeria.repositories;

import org.lessons.java.java_relazioni.java_pizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
