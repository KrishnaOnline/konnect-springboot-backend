package com.krishnavamshi.konnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJWT(jwt);
        Reel newReel = reelService.createReel(reel, user);
        return newReel;
    }
}
