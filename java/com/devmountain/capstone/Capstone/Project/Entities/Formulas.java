package com.devmountain.capstone.Capstone.Project.Entities;

import com.devmountain.capstone.Capstone.Project.dtos.FormulasDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "formulas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String formula;

    @ManyToOne
    @JsonBackReference(value = "UserFormulas")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "FormulasAnswers",
            joinColumns = @JoinColumn(name = "formulas_id"),
            inverseJoinColumns = @JoinColumn(name = "answers_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Answers> answersSet = new HashSet<>();

    public Formulas(FormulasDto formulasDto){
        if(formulasDto.getFormula() != null){
            this.formula = formulasDto.getFormula();
        }
    }
}
