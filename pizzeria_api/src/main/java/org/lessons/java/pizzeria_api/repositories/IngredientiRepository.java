package org.lessons.java.pizzeria_api.repositories;

import org.lessons.java.pizzeria_api.models.Ingredienti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Integer> {

}
