package com.krishnavamshi.konnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishnavamshi.konnect.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
