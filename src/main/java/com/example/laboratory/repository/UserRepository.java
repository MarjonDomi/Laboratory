package com.example.laboratory.repository;

import com.example.laboratory.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
 Optional<Users> findAllByName(String name);

 Optional<Users> findByUsername(String username);

 Optional<Users> findUserByUsernameAndPassword(String username, String password);

}
