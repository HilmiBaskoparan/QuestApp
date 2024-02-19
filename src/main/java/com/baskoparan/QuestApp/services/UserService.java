package com.baskoparan.QuestApp.services;

import com.baskoparan.QuestApp.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(Long userId, User user);

    void deleteById(Long userId);

    User findById(Long userId);
}
