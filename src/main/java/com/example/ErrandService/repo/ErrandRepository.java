package com.example.ErrandService.repo;

import com.example.ErrandService.model.Errand;
import com.example.ErrandService.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrandRepository extends JpaRepository<Errand, Long> {
    List<Errand> findByRequiredSkillsContains(Skill skill);
}
