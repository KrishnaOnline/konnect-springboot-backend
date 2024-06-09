package com.krishnavamshi.konnect.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.repositories.UserRepository;

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

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        User savedUser = userRepository.save(newUser);
        return savedUser;
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
