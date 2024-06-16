package com.krishnavamshi.konnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishnavamshi.konnect.models.Reel;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Integer> {
    public List<Reel> findByUserId(Integer userId);
}