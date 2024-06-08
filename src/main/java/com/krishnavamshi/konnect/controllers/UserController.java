package com.krishnavamshi.konnect.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.User;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User u1 = new User(1, "Abcd", "Singh", "abcd@mail.com", "123456");
        User u2 = new User(2, "Efgh", "Sharma", "efgh@mail.com", "654321");
        users.add(u1);
        users.add(u2);
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) {
        User u1 = new User(1, "Xyz", "Khan", "xyz@mail.com", "123456");
        u1.setId(id);
        return u1;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        User u1 = new User(1, "Xyz", "Khan", "xyz@mail.com", "123456");
        if(user.getFirstName()!=null) {
            u1.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            u1.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null) {
            u1.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null) {
            u1.setEmail(user.getEmail());
        }
        return u1;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Integer userId) {   // (@PathVariable("userId") integer id)
        return "User Deleted Successfully of ID "+userId;
    }
}