package com.krishnavamshi.konnect.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krishnavamshi.konnect.config.JwtProvider;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.UserRepository;
import com.krishnavamshi.konnect.response.AuthResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse registerUser(User user) throws Exception {
        User isExists = userRepository.findByEmail(user.getEmail());
        if(isExists!=null) {
            throw new Exception("Email Already Exists");
        }
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        System.out.println("TOKEN GENERATED :"+token);
        AuthResponse res = new AuthResponse(token, "User Registered Successfully");
        return res;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new Exception("User Do not Exist with ID "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User existedUser = userRepository.findByEmail(email);
        return existedUser;
    }

    @Override
    public User followUser(Integer user1Id, Integer user2Id) throws Exception {
        // Optional<User> user1 = userRepository.findById(user1Id);
        User user1 = findUserById(user1Id);
        User user2 = findUserById(user2Id);
        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user, Integer id) throws Exception {
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()) {
            throw new Exception("User Do not Exist with ID "+id);
        }
        User existedUser = u.get();
        if(user.getFirstName()!=null) {
            existedUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            existedUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null) {
            existedUser.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null) {
            existedUser.setPassword(user.getPassword());
        }
        User updatedUser = userRepository.save(existedUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUsers(String query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        
        Predicate firstNamePredicate = cb.like(cb.lower(user.get("firstName")), "%"+query.toLowerCase()+"%");
        Predicate lastNamePredicate = cb.like(cb.lower(user.get("lastName")), "%"+query.toLowerCase()+"%");
        Predicate emailPredicate = cb.like(cb.lower(user.get("email")), "%"+query.toLowerCase()+"%");
        
        Predicate finalPredicate = cb.or(firstNamePredicate, lastNamePredicate, emailPredicate);

        cq.where(finalPredicate);
        // return userRepository.searchUser(query);

        return entityManager.createQuery(cq).getResultList();
    }
}
