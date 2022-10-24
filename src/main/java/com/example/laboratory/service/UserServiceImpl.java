package com.example.laboratory.service;

import com.example.laboratory.entity.Users;
import com.example.laboratory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }


    public ResponseEntity doAutoLogin(String username , String password) {
        //find loged in user
        Optional<Users> findUserDetails = userRepository.findByUsername(username);
        //create simple authority based on user role
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(findUserDetails.get().getRoles().toString()));
        //start authentification
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, password,authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        //authorize this user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(authentication.getName(), HttpStatus.ACCEPTED);
    }

    @Override
    public Optional<Users> findUserByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users getId(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUser(Users user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
}
