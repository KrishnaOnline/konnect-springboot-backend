package com.krishnavamshi.konnect.services;

import java.util.List;

import com.krishnavamshi.konnect.models.Story;
import com.krishnavamshi.konnect.models.User;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
