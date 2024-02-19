package com.baskoparan.QuestApp.controllers;

import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.UserRepository;
import com.baskoparan.QuestApp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // CREATE NEW USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // UPDATE USER
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    // DELETE USER
    @DeleteMapping("/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    // FIND BY USERID
    @GetMapping("/{userId}")
    public User findByUserId(@PathVariable Long userId) {
        return userService.findById(userId);
    }
}
