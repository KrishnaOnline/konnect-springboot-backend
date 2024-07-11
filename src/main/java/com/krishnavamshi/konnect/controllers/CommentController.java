package com.krishnavamshi.konnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Comment;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.services.CommentService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Comment newComment = commentService.createComment(comment, postId, user.getId());
        return newComment;
    }

    @PutMapping("/auth/comments/like/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Comment likedComment = commentService.likeComment(commentId, user.getId());
        return likedComment;
    }
}