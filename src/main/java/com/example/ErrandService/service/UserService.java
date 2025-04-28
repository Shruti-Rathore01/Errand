package com.example.ErrandService.service;

import com.example.ErrandService.model.User;
import com.example.ErrandService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User registerUser(User user) {

        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User updateUser(Long id, User newUser) {
        User existing=repo.findById(id).orElse(null);
        if(existing !=null)
        {
            existing.setName(newUser.getName());
            existing.setEmail(newUser.getEmail());
            existing.setPassword(newUser.getPassword());
            return repo.save(existing);
        }
        return null;
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
