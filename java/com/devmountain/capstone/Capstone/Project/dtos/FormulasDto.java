package com.devmountain.capstone.Capstone.Project.dtos;

import com.devmountain.capstone.Capstone.Project.Entities.Answers;
import com.devmountain.capstone.Capstone.Project.Entities.Formulas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormulasDto implements Serializable {
    private Long id;
    private String formula;
    private UserDto userDto;
    private Set<Answers> answersSet = new HashSet<>();


    public FormulasDto (Formulas formulas){
        if(formulas.getId() != null){
            this.id = formulas.getId();
        }
        if(formulas.getFormula() != null){
            this.formula = formulas.getFormula();
        }
    }
}
