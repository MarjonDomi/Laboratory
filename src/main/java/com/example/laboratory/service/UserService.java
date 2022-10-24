package com.example.laboratory.service;

import com.example.laboratory.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveUser(Users user);
    Optional<Users> findUserByName(String name);
    List<Users> findAll();
    Users getId(long id);
    void deleteUser(Users user);
    Optional<Users> findByUsername(String username);
    Optional<Users> findUserByUsernameAndPassword(String username, String password);
}
