package com.krishnavamshi.konnect.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.UserRepository;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;

    @GetMapping("/auth/users")
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

    @GetMapping("/auth/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping("/auth/users")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @DeleteMapping("/auth/users")
    public String deleteUser(@RequestHeader("Authorization") String jwt) throws Exception {   // (@PathVariable("userId") integer id)
        User reqUser = userService.findUserByJWT(jwt);
        Optional<User> existedUser = userRepository.findById(reqUser.getId());
        if(existedUser.isEmpty()) {
            throw new Exception("User with ID "+reqUser.getId()+" Does not Exists");
        }
        // userRepository.delete(existedUser.get());
        // OR
        userRepository.deleteById(reqUser.getId());
        return "User Deleted with ID "+reqUser.getId();
    }

    @PutMapping("/auth/users/follow/{user2Id}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable("user2Id") Integer user2Id) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        User user = userService.followUser(reqUser.getId(), user2Id);
        return user;
    }

    @GetMapping("/auth/users/search")   // accesses as "/users/search?query=@mail.com"
    public List<User> searchUsersHandler(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        return users;
    }

    @GetMapping("/auth/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        System.out.println("JWT Token -------------- "+jwt);
        User user = userService.findUserByJWT(jwt);
        user.setPassword(null);
        return user;
    }
}