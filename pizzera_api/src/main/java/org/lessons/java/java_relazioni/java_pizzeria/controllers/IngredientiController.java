package org.lessons.java.java_relazioni.java_pizzeria.controllers;

import org.lessons.java.java_relazioni.java_pizzeria.models.Ingredienti;
import org.lessons.java.java_relazioni.java_pizzeria.models.Pizza;
import org.lessons.java.java_relazioni.java_pizzeria.repositories.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    // rotta index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", ingredientiRepository.findAll());
        return "ingredienti/index";
    }

    // rotta show
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredientiRepository.findById(id).get());
        return "ingredienti/show";
    }

    // rotta create
    @GetMapping("/create")
    public String create(Model model) {
        // creo il nuovo ingrediente
        model.addAttribute("ingrediente", new Ingredienti());

        return "ingredienti/create-or-edit";
    }

    // rotte store (crea effettivamente il nuovo ingrediente)
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") Ingredienti ingredienteForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/create-or-edit";
        }

        ingredientiRepository.save(ingredienteForm);

        return "redirect:/ingredienti";
    }

    // rotta edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        // trovo l'ingrediente per id
        model.addAttribute("ingrediente", ingredientiRepository.findById(id).get());

        model.addAttribute("edit", true);

        return "ingredienti/create-or-edit";
    }

    // rotte update (aggiorna effettivamente l'ingrediente)
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingrediente") Ingredienti ingredienteForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/create-or-edit";
        }

        ingredientiRepository.save(ingredienteForm);

        return "redirect:/ingredienti";
    }

    // rotta delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // cancella l'ingrediente
        Ingredienti ingredientiDaCancellare = ingredientiRepository.findById(id).get();

        for (Pizza pizzaLinkata : ingredientiDaCancellare.getPizze()) {
            pizzaLinkata.getIngredienti().remove(ingredientiDaCancellare);
        }

        ingredientiRepository.delete(ingredientiDaCancellare);

        return "redirect:/ingredienti";
    }
}
