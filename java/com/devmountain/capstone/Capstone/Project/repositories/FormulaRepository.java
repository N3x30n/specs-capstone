package com.devmountain.capstone.Capstone.Project.repositories;

import com.devmountain.capstone.Capstone.Project.Entities.Formulas;
import com.devmountain.capstone.Capstone.Project.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
public interface FormulaRepository extends JpaRepository<Formulas, Long> {
    List<Formulas> findAllByUserEquals(User user);
}
