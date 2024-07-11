package com.krishnavamshi.konnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Post;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.PostRepository;
import com.krishnavamshi.konnect.response.ApiResponse;
import com.krishnavamshi.konnect.services.PostService;
import com.krishnavamshi.konnect.services.UserService;


@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/auth/posts")    // Changed when adding Auth...
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post newPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/auth/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/paginate")
    public ResponseEntity<List<Post>> getPaginatedPosts(
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "5") Integer pageSize
    ) throws Exception {
        List<Post> pagedPosts = postService.findPaginatedPosts(pageNo, pageSize);
        return new ResponseEntity<List<Post>>(pagedPosts, HttpStatus.OK);
    }

    @PutMapping("/auth/posts/save/{postId}")
    public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post post = postService.savePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/auth/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
