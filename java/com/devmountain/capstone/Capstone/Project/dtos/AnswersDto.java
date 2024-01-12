package com.devmountain.capstone.Capstone.Project.dtos;

import com.devmountain.capstone.Capstone.Project.Entities.Answers;
import com.devmountain.capstone.Capstone.Project.Entities.Formulas;
import com.devmountain.capstone.Capstone.Project.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersDto implements Serializable {
    private Long id;
    private Long answer;
    private UserDto userDto;
    private Set<Formulas> formulasSet = new HashSet<>();

    public AnswersDto(Answers answers){
        if(answers.getId() != null){
            this.id = answers.getId();
        }
        if(answers.getAnswer() != null){
            this.answer = answers.getAnswer();
        }
    }
}
