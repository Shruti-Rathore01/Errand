package com.example.ErrandService.repo;

import com.example.ErrandService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
