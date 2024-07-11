package com.krishnavamshi.konnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Message;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.services.MessageService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/messages/chat/{chatId}")
    public Message createMessage(@PathVariable Integer chatId, @RequestBody Message req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/auth/messages/chat/{chatId}")
    public List<Message> getChatMessages(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWT(jwt);
        return messageService.findChatMessages(chatId);
    }
}