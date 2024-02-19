package com.baskoparan.QuestApp.controllers;

import com.baskoparan.QuestApp.business.requests.PostCreateRequest;
import com.baskoparan.QuestApp.business.requests.PostUpdateRequest;
import com.baskoparan.QuestApp.entities.Post;
import com.baskoparan.QuestApp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest createRequest) {
        return postService.createPost(createRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest) {
        return postService.updatePost(postId, updateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteByPostId(@PathVariable Long postId) {
        postService.deleteByPostId(postId);
    }

    @GetMapping("/{postId}")
    public Post findById(@PathVariable Long postId) {
        return postService.findById(postId);
    }
}
