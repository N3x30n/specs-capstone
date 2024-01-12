package com.devmountain.capstone.Capstone.Project.Entities;

import com.devmountain.capstone.Capstone.Project.dtos.AnswersDto;
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
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long answer;

    @ManyToOne
    @JsonBackReference(value = "UserAnswers")
    private User user;

    @ManyToMany(mappedBy = "answersSet", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Formulas> formulasSet = new HashSet<>();

    public Answers(AnswersDto answersDto){
        if(answersDto.getAnswer() != null){
            this.answer = answersDto.getAnswer();
        }
    }
}
