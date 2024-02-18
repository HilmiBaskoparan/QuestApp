package com.baskoparan.QuestApp.controllers;

import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // CREATE NEW USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // UPDATE USER
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
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

    // DELETE USER
    @DeleteMapping("/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

    // FIND BY USERID
    @GetMapping("/{userId}")
    public User findByUserId(@PathVariable Long userId) {

        // Custom Exception
        return userRepository.findById(userId).orElse(null);
    }
}
