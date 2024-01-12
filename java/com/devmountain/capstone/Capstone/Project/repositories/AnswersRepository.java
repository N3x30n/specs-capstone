package com.devmountain.capstone.Capstone.Project.repositories;

import com.devmountain.capstone.Capstone.Project.Entities.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
}
