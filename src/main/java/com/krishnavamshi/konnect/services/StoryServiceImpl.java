package com.krishnavamshi.konnect.services;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishnavamshi.konnect.models.Story;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story newStory = new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimeStamp(LocalDateTime.now());
        return storyRepository.save(newStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(userId);
    }
}
