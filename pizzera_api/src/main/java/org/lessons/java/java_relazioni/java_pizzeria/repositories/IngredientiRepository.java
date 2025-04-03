package org.lessons.java.java_relazioni.java_pizzeria.repositories;

import org.lessons.java.java_relazioni.java_pizzeria.models.Ingredienti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Integer> {

}
