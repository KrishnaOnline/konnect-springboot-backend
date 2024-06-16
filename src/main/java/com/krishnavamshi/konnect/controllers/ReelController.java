package com.krishnavamshi.konnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Reel;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.services.ReelService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class ReelController {
    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/reels")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJWT(jwt);
        Reel newReel = reelService.createReel(reel, user);
        return newReel;
    }

    @GetMapping("/auth/reels")
    public List<Reel> findAllReels() {
        return reelService.getAllReels();
    }

    @GetMapping("/auth/reels/user/{userId}")
    public List<Reel> findUserReels(@PathVariable Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return reelService.getUserReels(user.getId());
    }
}
