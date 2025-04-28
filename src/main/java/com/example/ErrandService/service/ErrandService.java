package com.example.ErrandService.service;

import com.example.ErrandService.model.Errand;
import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.ErrandRepository;
import com.example.ErrandService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.example.ErrandService.model.Status.COMPLETED;

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


    public Errand findErrandById(Long errandId) {
        return errandRepository.findById(errandId).orElse(null);
    }

    public void saveErrand(Errand errand) {
         errandRepository.save(errand);
    }

    public String markErrandAsCompleted(Long errandId) {
        Optional<Errand> errandOpt = errandRepository.findById(errandId);

        if (!errandOpt.isPresent()) {
            return "Errand not found!";
        }
        Errand errand = errandOpt.get();

        // Check if the errand is already completed
        if ("COMPLETED".equals(errand.getStatus())) {
            return "Errand is already completed.";
        }

        // Update the errand status to COMPLETED
        errand.setStatus(COMPLETED);

        // Save the updated errand
        errandRepository.save(errand);

        // Send notification to the user who created the errand
        User user = errand.getUser();
        sendCompletionNotification(user);

        return "Errand marked as completed successfully!";
    }

    // Method to send notification to the user
    private void sendCompletionNotification(User user) {
        // Implement the logic for sending a notification (email/SMS/in-app)
        String message = "Your errand has been completed!";
        // Example: sendEmail(user.getEmail(), message);
    }
    }

