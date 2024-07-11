package com.krishnavamshi.konnect.services;

import java.util.List;

import com.krishnavamshi.konnect.models.Message;
import com.krishnavamshi.konnect.models.User;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatMessages(Integer chatId) throws Exception;
}
