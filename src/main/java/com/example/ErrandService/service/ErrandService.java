package com.example.ErrandService.service;

import com.example.ErrandService.model.Errand;
import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.ErrandRepository;
import com.example.ErrandService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ErrandService{
    @Autowired
    private UserRepository repo;
    @Autowired
    private ErrandRepository errandRepository;

    public Errand createErrand(Errand errand) {
        if (errand.getRequiredSkills() == null) {
            errand.setRequiredSkills(new HashSet<>());
        }
        return errandRepository.save(errand);
    }

    public List<Errand> getAllErrands() {
        return errandRepository.findAll();
    }


    public Errand getErrandById(Long id) {
        return errandRepository.findById(id).orElse(null);
    }

    public Errand updateErrand(Long id, Errand updatedErrand) {
        Optional<Errand> optionalErrand = errandRepository.findById(id);

        if (optionalErrand.isPresent()) {
            Errand existingErrand = optionalErrand.get();
            existingErrand.setTitle(updatedErrand.getTitle());
            existingErrand.setDescription(updatedErrand.getDescription());
            existingErrand.setStatus(updatedErrand.getStatus());
            return errandRepository.save(existingErrand);
        } else {
            throw new RuntimeException("Errand not found with id: " + id);
        }
    }

    public void deleteErrand(Long id) {
        errandRepository.deleteById(id);}

    public List<Errand> getMatchingErrandsForUser(User user) {
        // Fetch all errands where the required skills contain the user's skill
        return errandRepository.findByRequiredSkillsContains(user.getSkill());
    }

    public Optional<Errand> findById(Long id) {
        return errandRepository.findById(id);
    }

    public User findUserById(Long userId) {
        return repo.findById(userId).orElse(null);  // Use the repository method here
    }



}
