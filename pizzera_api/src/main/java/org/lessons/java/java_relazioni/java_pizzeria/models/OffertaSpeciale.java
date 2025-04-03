package org.lessons.java.java_relazioni.java_pizzeria.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "offerteSpeciali")
public class OffertaSpeciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // pizza da cui dipendo
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Size(min = 5, max = 50, message = "Il titolo dell'offerta' deve avere almeno 5 caratteri")
    @Column(name = "titolo", nullable = false)
    @NotBlank(message = "Il campo titolo non può essere vuoto o nullo")
    private String titolo;

    @NotNull(message = "Il campo data d'inizio non può essere nullo")
    @PastOrPresent(message = "Il campo data d'inizio non può essere settato nel futuro")
    private LocalDate dataInizio;

    @NotNull(message = "Il campo data di fine non può essere nullo")
    @FutureOrPresent(message = "Il campo data di fine non può essere settato nel passato")
    private LocalDate dataFine;

    // metodi
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataInizio() {
        return this.dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return this.dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

}
