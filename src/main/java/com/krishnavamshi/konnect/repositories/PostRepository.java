package com.krishnavamshi.konnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.krishnavamshi.konnect.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // Used persistence to do same in PostServiceImpl itself for findPostByUserId();
    // @Query("SELECT p from Post p WHERE p.userId = :userId")
    // List<Post> findPostByUserId(Integer userId);
}
