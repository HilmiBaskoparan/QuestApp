package com.baskoparan.QuestApp.services.imp;

import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.UserRepository;
import com.baskoparan.QuestApp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> updateUser = userRepository.findById(userId);

        if (updateUser.isPresent()){
            User foundUser = updateUser.get();
            foundUser.setUserName(user.getUserName());
            foundUser.setPassword(user.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }

    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        // Custom Exception
        return userRepository.findById(userId).orElse(null);
    }
}
