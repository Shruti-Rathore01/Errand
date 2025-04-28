package com.example.ErrandService.controller;


import com.example.ErrandService.model.User;
import com.example.ErrandService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public User registerUser(@RequestBody User user)
    {


        return service.registerUser(user);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers()
    {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserId(@PathVariable Long id)
    {
        return service.getUserId(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody  User newUser)
    {
        return service.updateUser(id,newUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        service.deleteUser(id);
    }
}
