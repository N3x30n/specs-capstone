package com.devmountain.capstone.Capstone.Project.controllers;

import com.devmountain.capstone.Capstone.Project.dtos.FormulasDto;
import com.devmountain.capstone.Capstone.Project.services.FormulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/formulas")
public class FormulaController
{
    @Autowired
    private FormulasService formulasService;

    @GetMapping("/user/{userId}")
    public List<FormulasDto> getFormulasByUser(@PathVariable Long userId){
        return formulasService.getAllFormulasByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public void addFormula(@RequestBody FormulasDto formulasDto, @PathVariable Long userId){
        formulasService.addFormula(formulasDto, userId);
    }

    @DeleteMapping("/{formulasId}")
    public void deleteFormulaById(@PathVariable Long formulasId){
        formulasService.deleteFormulaById(formulasId);
    }

    @PutMapping
    public void updateFormula(@RequestBody FormulasDto formulasDto){
        formulasService.updateFormulaById(formulasDto);
    }

    @GetMapping("/{formulasId}")
    public Optional<FormulasDto> getFormulasById(@PathVariable Long formulasId){
        return formulasService.getFormulaById(formulasId);
    }
}
