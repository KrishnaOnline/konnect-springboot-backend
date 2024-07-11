package com.krishnavamshi.konnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishnavamshi.konnect.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
