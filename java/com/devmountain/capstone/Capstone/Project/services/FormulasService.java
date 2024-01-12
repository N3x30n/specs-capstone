package com.devmountain.capstone.Capstone.Project.services;

import com.devmountain.capstone.Capstone.Project.dtos.FormulasDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface FormulasService {
    @Transactional
    void addFormula(FormulasDto formulasDto, Long userId);

    @Transactional
    void deleteFormulaById(Long formulaId);

    @Transactional
    void updateFormulaById(FormulasDto formulasDto);

    List<FormulasDto> getAllFormulasByUserId(Long userId);

    Optional<FormulasDto> getFormulaById(Long formulaId);
}
