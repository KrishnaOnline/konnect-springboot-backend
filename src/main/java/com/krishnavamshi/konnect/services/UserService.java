package com.krishnavamshi.konnect.services;

import java.util.List;

import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.response.AuthResponse;

public interface UserService {
    public AuthResponse registerUser(User user) throws Exception;
    public User findUserById(Integer userId) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Integer user1Id, Integer user2Id) throws Exception;
    public User updateUser(User user, Integer id) throws Exception;
    public List<User> searchUsers(String query);
}
