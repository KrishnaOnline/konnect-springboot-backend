package com.krishnavamshi.konnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishnavamshi.konnect.models.Reel;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.ReelRepository;

@Service
public class ReelServiceImpl implements ReelService {
    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel newReel = new Reel();
        newReel.setTitle(reel.getTitle());
        newReel.setVideo(reel.getVideo());
        newReel.setUser(user);
        return reelRepository.save(newReel);
    }

    @Override
    public List<Reel> getAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> getUserReels(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        if(user==null) {
            throw new Exception("User Not Found");
        }
        return reelRepository.findByUserId(userId);
    }
}