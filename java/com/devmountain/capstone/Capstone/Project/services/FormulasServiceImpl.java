package com.devmountain.capstone.Capstone.Project.services;

import com.devmountain.capstone.Capstone.Project.Entities.Formulas;
import com.devmountain.capstone.Capstone.Project.Entities.User;
import com.devmountain.capstone.Capstone.Project.dtos.FormulasDto;
import com.devmountain.capstone.Capstone.Project.repositories.FormulaRepository;
import com.devmountain.capstone.Capstone.Project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormulasServiceImpl implements FormulasService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormulaRepository formulaRepository;


    @Override
    @Transactional
    public void addFormula(FormulasDto formulasDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Formulas formulas = new Formulas(formulasDto);
        userOptional.ifPresent(formulas::setUser);
        formulaRepository.saveAndFlush(formulas);
    }
    @Override
    @Transactional
    public void deleteFormulaById(Long formulaId){
        Optional<Formulas> formulasOptional = formulaRepository.findById(formulaId);
        formulasOptional.ifPresent(formulas -> formulaRepository.delete(formulas));
    }

    @Override
    @Transactional
    public void updateFormulaById(FormulasDto formulasDto){
        Optional<Formulas> formulasOptional = formulaRepository.findById(formulasDto.getId());
        formulasOptional.ifPresent(formulas -> {
            formulas.setFormula(formulasDto.getFormula());
            formulaRepository.saveAndFlush(formulas);
        });
    }

    @Override
    public List<FormulasDto> getAllFormulasByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
//        if(userOptional.isPresent()){
            List<Formulas> formulasList = formulaRepository.findAllByUserEquals(userOptional.get());
            return formulasList.stream().map(formulas -> new FormulasDto(formulas)).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
    }

    @Override
    public Optional<FormulasDto> getFormulaById(Long formulaId){
        Optional<Formulas> formulasOptional = formulaRepository.findById(formulaId);
        if(formulasOptional.isPresent()){
            return Optional.of(new FormulasDto(formulasOptional.get()));
        }
        return Optional.empty();
    }
}
