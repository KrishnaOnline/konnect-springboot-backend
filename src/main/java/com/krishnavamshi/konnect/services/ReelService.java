package com.krishnavamshi.konnect.services;

import java.util.List;

import com.krishnavamshi.konnect.models.Reel;
import com.krishnavamshi.konnect.models.User;

public interface ReelService {
    public Reel createReel(Reel reel, User user);
    public List<Reel> getAllReels();
    public List<Reel> getUserReels(Integer userId) throws Exception;
}
