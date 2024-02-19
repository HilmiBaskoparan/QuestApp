package com.baskoparan.QuestApp.controllers;

import com.baskoparan.QuestApp.business.requests.LikeCreateRequest;
import com.baskoparan.QuestApp.entities.Like;
import com.baskoparan.QuestApp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }

    @GetMapping("/{likeId}")
    public Like findById(@PathVariable Long likeId) {
        return likeService.findById(likeId);
    }
}
