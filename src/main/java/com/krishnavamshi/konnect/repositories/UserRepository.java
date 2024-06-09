package com.krishnavamshi.konnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.krishnavamshi.konnect.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    
    // Did below with JPA in UserServceImpl itself, Checkout...
    // // public List<User> searchUser(@Param(value = "") String query);
    // @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    // public List<User> searchUser(@Param("query") String query);
    // // List<User> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
