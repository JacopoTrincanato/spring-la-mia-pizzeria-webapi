package org.lessons.java.java_relazioni.java_pizzeria.controllers;

import java.util.List;

import org.lessons.java.java_relazioni.java_pizzeria.models.OffertaSpeciale;
import org.lessons.java.java_relazioni.java_pizzeria.models.Pizza;
import org.lessons.java.java_relazioni.java_pizzeria.repositories.IngredientiRepository;
import org.lessons.java.java_relazioni.java_pizzeria.repositories.OffertaSpecialeRepository;
import org.lessons.java.java_relazioni.java_pizzeria.repositories.PizzaRepository;
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
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    // chiamo la repository delle offerte
    @Autowired
    private OffertaSpecialeRepository offertaRepository;

    // chiamo la repository degli inredienti
    @Autowired
    private IngredientiRepository ingredientiRepository;

    // creo la rotta index
    @GetMapping
    public String index(Model model) {
        // creo la lista di pizze
        List<Pizza> pizze = repository.findAll();

        model.addAttribute("pizze", pizze);

        return "pizze/index";
    }

    // creo la rotta show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        // creo la singola pizza
        Pizza pizza = repository.findById(id).get();
        model.addAttribute("pizza", pizza);

        return "pizze/show";
    }

    // creo la rotta create
    @GetMapping("/create")
    public String create(Model model) {
        // creo una nuova pizza
        model.addAttribute("pizza", new Pizza());

        // prendo tutti gli ingredienti
        model.addAttribute("ingredienti", ingredientiRepository.findAll());
        return "pizze/create-or-edit";
    }

    // creo un metodo per aggiungere una pizza
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {

        // verifico che il processo di validazione sia avvenuto correttamente
        if (bindingResult.hasErrors()) {

            // prendo tutti gli ingredienti
            model.addAttribute("ingredienti", ingredientiRepository.findAll());
            return "pizze/create-or-edit";
        }

        // salvo il dato
        repository.save(pizzaForm);

        return "redirect:/pizze";
    }

    // creo la rotta edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // prendo tutti gli ingredienti
        model.addAttribute("ingredienti", ingredientiRepository.findAll());
        model.addAttribute("pizza", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "pizze/create-or-edit";
    }

    // creo update per modificare gli elementi della card
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {

        // verifico che il processo di validazione sia avvenuto correttamente
        if (bindingResult.hasErrors()) {
            // prendo tutti gli ingredienti
            model.addAttribute("ingredienti", ingredientiRepository.findAll());
            return "pizze/create-or-edit";
        }

        // aggiorno il dato
        repository.save(pizzaForm);

        return "redirect:/pizze/" + pizzaForm.getId();
    }

    // creo la rotta delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        // prendo tutte le offerte della pizza
        Pizza pizza = repository.findById(id).get();

        for (OffertaSpeciale offerteDaEliminare : pizza.getOfferteSpeciali()) {
            offertaRepository.delete(offerteDaEliminare);
        }

        // cancello la pizza
        repository.delete(pizza);

        return "redirect:/pizze";
    }

    // metodo per l'offerta
    @GetMapping("/{id}/offerte")
    public String offertaSpeciale(@PathVariable Integer id, Model model) {
        // creo una nuova offerta
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        offertaSpeciale.setPizza(repository.findById(id).get());

        model.addAttribute("offertaSpeciale", offertaSpeciale);

        return "offerte_speciali/create-or-edit";
    }
}
