package com.example.ErrandService.controller;

import com.example.ErrandService.model.Errand;
import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.UserRepository;
import com.example.ErrandService.service.ErrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/errands")
public class ErrandController {
    @Autowired
    private ErrandService errandService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Errand createErrand(@RequestBody Errand errand) {
        return errandService.createErrand(errand);
    }

    @GetMapping
    public ResponseEntity<?> getAllErrands() {
        try {
            List<Errand> errands = errandService.getAllErrands();
            return ResponseEntity.ok(errands);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving errands: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Errand getErrandById(@PathVariable Long id) {
        return errandService.getErrandById(id);
    }

    @PutMapping("/{id}")
    public Errand updateErrand(@PathVariable Long id, @RequestBody Errand updatedErrand) {
        return errandService.updateErrand(id, updatedErrand);
    }

    @DeleteMapping("/{id}")
    public void deleteErrand(@PathVariable Long id) {
        errandService.deleteErrand(id);
    }

    @GetMapping("/match/{userId}")
    public Object matchUserToErrand(@PathVariable Long userId)
    {
        User user = errandService.findUserById(userId);

        if (user == null) {
            return "User not found";
        }
        List<Errand> matchingErrands = errandService.getMatchingErrandsForUser(user);
        if (matchingErrands.isEmpty()) {
            return "No errands match your skill";
        }

        return matchingErrands;
    }

    @PutMapping("/accept/{userId}/{errandId}")
    public Object acceptErrand(@PathVariable Long userId,@PathVariable Long errandId)
    {
        User user=errandService.findUserById(userId);
        Errand errand=errandService.findErrandById(errandId);
        if (user == null || errand == null) {
            return "User or Errand not found";
        }
        if (errand.getUser() != null) {
            return "Errand already assigned to another user";
        }
        errand.setUser(user);
        errandService.saveErrand(errand);
        return "Errand accepted successfully!";
    }
    // Endpoint to mark errand as completed
    @PutMapping("/complete/{errandId}")
    public ResponseEntity<String> markErrandAsCompleted(@PathVariable Long errandId) {
        String response = errandService.markErrandAsCompleted(errandId);
        if (response.equals("Errand not found!") || response.equals("Errand is already completed.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
