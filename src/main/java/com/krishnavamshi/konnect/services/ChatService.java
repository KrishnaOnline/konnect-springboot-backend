package com.krishnavamshi.konnect.services;

import java.util.List;

import com.krishnavamshi.konnect.models.Chat;
import com.krishnavamshi.konnect.models.User;

public interface ChatService {
    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUserChats(Integer userId);
}
