package com.krishnavamshi.konnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.repositories.PostRepository;
import com.krishnavamshi.konnect.services.PostService;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;
}
