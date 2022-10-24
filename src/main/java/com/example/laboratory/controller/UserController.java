package com.example.laboratory.controller;

import com.example.laboratory.entity.Users;
import com.example.laboratory.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/u")
public class UserController {

    private UserServiceImpl userService;
    public static AuthenticationManager authenticationManager;

    public static BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserServiceImpl service, AuthenticationManager authenticationManager,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = service;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/allusers")
    public List<Users> getAllUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public Users getUserId(@PathVariable(value = "id") long id) {
        return userService.getId(id);
    }

    @PostMapping("/addnewuser")
    public Users createUser(@RequestBody Users user){
        return userService.saveUser(user);
    }

    @PutMapping("/updateuser/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public Users updateUser(@PathVariable (value="id")long id,@RequestBody Users user){
        Users upduser = userService.getId(id);
//        upduser.setName(user.getName());
//        upduser.setLastname(user.getLastname());
//        upduser.setAddress(user.getAddress());
//        upduser.setBirthdate(user.getBirthdate());
        return userService.saveUser(upduser);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable (value="id")long id){
        Users user = userService.getId(id);
        userService.deleteUser(user);
        System.out.println("ok");
    }
    @GetMapping("/finduserbyname")
    public Optional<Users> findUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody @Valid Users user, BindingResult result) {
        Optional<Users> findByUsername = userService.findByUsername(user.getUsername());
//        log.warn("asdfghjkl;lkjhgfd");
        if (result.hasErrors()) {
            return new ResponseEntity(result.getAllErrors(), HttpStatus.CONFLICT);
        } else if (findByUsername.isPresent()) {
            return new ResponseEntity("This username already exist , try another one ", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(userService.saveUser(user), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody Users user) {
        Optional<Users> findUserByUsername = userService.findByUsername(user.getUsername());
        if (!findUserByUsername.isPresent()) {
            return new ResponseEntity("User doesnt exist", HttpStatus.NO_CONTENT);
        } else if (user.getPassword().equals(findUserByUsername.get().getPassword())) {
            return new ResponseEntity(userService.doAutoLogin(user.getUsername(), user.getPassword()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Password is incorrect", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/logout-page")
    public ResponseEntity logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //shto dhe kusht nqs eshte annonymousUser ktu
        if (!authentication.getName().isEmpty()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            authentication.setAuthenticated(false);
            return new ResponseEntity(authentication.getName() + " session ended", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("No user authenticated", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/home")
    public ResponseEntity homePage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String authenticatedUser = authentication.getName();

        return new ResponseEntity(authenticatedUser, HttpStatus.ACCEPTED);
    }


}
