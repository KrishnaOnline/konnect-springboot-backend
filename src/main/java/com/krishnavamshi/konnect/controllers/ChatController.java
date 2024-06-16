package com.krishnavamshi.konnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.Chat;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.request.CreateChatRequest;
import com.krishnavamshi.konnect.services.ChatService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/auth/chats")
    public List<Chat> findUserChats(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJWT(jwt);
        List<Chat> chats = chatService.findUserChats(user.getId());
        return chats;
    }
}
