package com.example.ErrandService.service;

import com.example.ErrandService.dto.UserRegistrationDTO;
import com.example.ErrandService.exception.UserException;
import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRegistrationDTO registrationDTO) {
        // Check if email already exists
        if (repository.findByEmail(registrationDTO.getEmail()).isPresent()) {
            throw new UserException("Email already registered");
        }

        try {
            // Create new user
            User user = new User();
            user.setName(registrationDTO.getName());
            user.setEmail(registrationDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            user.setSkill(registrationDTO.getSkill());

            return repository.save(user);
        } catch (Exception e) {
            throw new UserException("Error registering user", e);
        }
    }

    @Transactional
    public List<User> getAllUsers() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new UserException("Error retrieving users", e);
        }
    }

    public User getUserId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
    }

    @Transactional
    public User updateUser(Long id, User newUser) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));

        try {
            // Update fields
            user.setName(newUser.getName());
            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
            user.setSkill(newUser.getSkill());

            // Only update email if it's changed and not already taken
            if (!user.getEmail().equals(newUser.getEmail())) {
                if (repository.findByEmail(newUser.getEmail()).isPresent()) {
                    throw new UserException("Email already taken");
                }
                user.setEmail(newUser.getEmail());
            }

            return repository.save(user);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            throw new UserException("Error updating user", e);
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserException("User not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new UserException("Error deleting user", e);
        }
    }
}
