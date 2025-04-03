package org.lessons.java.java_relazioni.java_pizzeria.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredienti")
public class Ingredienti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "il nome dell'ingrediente deve essere compilato")
    @Size(min = 3, max = 30, message = "il nome dell'ingrediente deve essere lungo almeno 3 caratteri e non più di 30")
    private String nome;

    @Lob
    private String descrizione;

    // lista di pizze a cui fa riferimento
    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze;

    // metodi
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

}
