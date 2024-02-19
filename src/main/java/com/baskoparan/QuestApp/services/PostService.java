package com.baskoparan.QuestApp.services;

import com.baskoparan.QuestApp.business.requests.PostCreateRequest;
import com.baskoparan.QuestApp.business.requests.PostUpdateRequest;
import com.baskoparan.QuestApp.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAllPosts(Optional<Long> userId);

    Post createPost(PostCreateRequest createRequest);

    Post updatePost(Long postId, PostUpdateRequest updateRequest);

    void deleteByPostId(Long postId);

    Post findById(Long postId);
}
