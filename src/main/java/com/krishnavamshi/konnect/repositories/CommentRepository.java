package com.krishnavamshi.konnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishnavamshi.konnect.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
