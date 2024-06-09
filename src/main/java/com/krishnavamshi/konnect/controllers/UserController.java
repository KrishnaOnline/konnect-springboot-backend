package com.krishnavamshi.konnect.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.UserRepository;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        // List<User> users = new ArrayList<>();
        // User u1 = new User(1, "Abcd", "Singh", "abcd@mail.com", "123456");
        // User u2 = new User(2, "Efgh", "Sharma", "efgh@mail.com", "654321");
        // users.add(u1);
        // users.add(u2);
        // return users;
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        throw new Exception("User Do not Exist with ID "+id);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable("userId") Integer id) throws Exception {
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()) {
            throw new Exception("User Do not Exist with ID "+id);
        }
        User existedUser = u.get();
        if(user.getFirstName()!=null) {
            existedUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            existedUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null) {
            existedUser.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null) {
            existedUser.setPassword(user.getPassword());
        }

        User updatedUser = userRepository.save(existedUser);
        return updatedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception {   // (@PathVariable("userId") integer id)
        Optional<User> existedUser = userRepository.findById(userId);
        if(existedUser.isEmpty()) {
            throw new Exception("User with ID "+userId+" Does not Exists");
        }
        // userRepository.delete(existedUser.get());
        // OR
        userRepository.deleteById(userId);
        return "User Deleted with ID "+userId;
    }
}