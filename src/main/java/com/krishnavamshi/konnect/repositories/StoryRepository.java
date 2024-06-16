package com.krishnavamshi.konnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishnavamshi.konnect.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    public List<Story> findByUserId(Integer userId);
}
