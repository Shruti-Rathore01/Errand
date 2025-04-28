package com.example.ErrandService.controller;

import com.example.ErrandService.model.Errand;
import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.UserRepository;
import com.example.ErrandService.service.ErrandService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Errand> getAllErrands() {
        return errandService.getAllErrands();
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
}
