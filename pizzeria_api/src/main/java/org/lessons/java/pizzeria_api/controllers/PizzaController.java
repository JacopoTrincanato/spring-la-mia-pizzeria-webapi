package org.lessons.java.pizzeria_api.controllers;

import java.util.List;

import org.lessons.java.pizzeria_api.models.OffertaSpeciale;
import org.lessons.java.pizzeria_api.models.Pizza;
import org.lessons.java.pizzeria_api.repositories.IngredientiRepository;
//import org.lessons.java.pizzeria_api.repositories.OffertaSpecialeRepository;
import org.lessons.java.pizzeria_api.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    // chiamo il service della pizza
    @Autowired
    private PizzaService pizzaService;

    // chiamo la repository delle offerte
    // @Autowired
    // private OffertaSpecialeRepository offertaRepository;

    // chiamo la repository degli inredienti
    @Autowired
    private IngredientiRepository ingredientiRepository;

    // creo la rotta index
    @GetMapping
    public String index(Model model) {
        // creo la lista di pizze
        List<Pizza> pizze = pizzaService.findPizze();

        model.addAttribute("pizze", pizze);

        return "pizze/index";
    }

    // creo la rotta show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        // creo la singola pizza
        Pizza pizza = pizzaService.getPizzaById(id);
        model.addAttribute("pizza", pizza);

        return "pizze/show";
    }

    // metodo per trovare la pizza per nome
    @GetMapping("/searchByName")
    public String getSearchByTitle(@RequestParam(name = "nome") String nome, Model model) {
        List<Pizza> pizza = pizzaService.findPizzeByName(nome);
        model.addAttribute("pizza", pizza);
        return "pizze/index";
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
        pizzaService.createPizza(pizzaForm);

        return "redirect:/pizze";
    }

    // creo la rotta edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // prendo tutti gli ingredienti
        model.addAttribute("ingredienti", ingredientiRepository.findAll());
        model.addAttribute("pizza", pizzaService.getPizzaById(id));
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
        pizzaService.updatePizza(pizzaForm);

        return "redirect:/pizze/" + pizzaForm.getId();
    }

    // creo la rotta delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        // prendo tutte le offerte della pizza
        Pizza pizza = pizzaService.getPizzaById(id);

        /*
         * for (OffertaSpeciale offerteDaEliminare : pizza.getOfferteSpeciali()) {
         * offertaRepository.delete(offerteDaEliminare);
         * }
         */

        // cancello la pizza
        pizzaService.deletePizza(pizza);

        return "redirect:/pizze";
    }

    // metodo per l'offerta
    @GetMapping("/{id}/offerte")
    public String offertaSpeciale(@PathVariable Integer id, Model model) {
        // creo una nuova offerta
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        offertaSpeciale.setPizza(pizzaService.getPizzaById(id));

        model.addAttribute("offertaSpeciale", offertaSpeciale);

        return "offerte_speciali/create-or-edit";
    }
}