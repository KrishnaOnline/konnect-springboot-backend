package com.krishnavamshi.konnect.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishnavamshi.konnect.models.Chat;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.ChatRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Chat createChat(User reqUser, User user2) {
        List<Chat> existingChats = chatRepository.findChatsByUsersIds(reqUser, user2);
        if(!existingChats.isEmpty()) {
            // If there are multiple chats, return the first one or handle it as needed
            return existingChats.get(0);
        }
        Chat newChat = new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setTimeStamp(LocalDateTime.now());
        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if(chat.isEmpty()) {
            throw new Exception("Chat Not Found with ID "+chatId);
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUserChats(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}