package com.krishnavamshi.konnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Story;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.services.StoryService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/stories")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJWT(jwt);
        return storyService.createStory(story, user);
    }

    @GetMapping("/auth/stories/user/{userId}")
    public List<Story> getAllStories(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWT(jwt);
        return storyService.findStoryByUserId(userId);
    }
}