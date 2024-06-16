package com.krishnavamshi.konnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.krishnavamshi.konnect.models.Chat;
import com.krishnavamshi.konnect.models.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersId(Integer userId);

    // List<Chat> findByUsersContainingAndUsersContaining(User user, User reqUser);
    // OR
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatsByUsersIds(@Param("user") User user, @Param("reqUser") User reqUser);
    // OR Use JPA and Persistence Context...
}
