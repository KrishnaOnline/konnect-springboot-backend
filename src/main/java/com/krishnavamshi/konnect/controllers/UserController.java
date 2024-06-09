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

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return newUser;
        // return userService.registerUser(user);
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
        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable("userId") Integer id) throws Exception {
        User updatedUser = userService.updateUser(user, id);
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

    @PutMapping("/users/follow/{user1Id}/{user2Id}")
    public User followUserHandler(@PathVariable Integer user1Id, @PathVariable("user2Id") Integer user2Id) throws Exception {
        User user = userService.followUser(user1Id, user2Id);
        return user;
    }

    @GetMapping("/users/search")   // accesses as "/users/search?query=@mail.com"
    public List<User> searchUsersHandler(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        return users;
    }
}