package com.baskoparan.QuestApp.services.imp;

import com.baskoparan.QuestApp.business.requests.PostCreateRequest;
import com.baskoparan.QuestApp.business.requests.PostUpdateRequest;
import com.baskoparan.QuestApp.entities.Post;
import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.PostRepository;
import com.baskoparan.QuestApp.services.PostService;
import com.baskoparan.QuestApp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    @Override
    public Post createPost(PostCreateRequest createRequest) {
        User user = userService.findById(createRequest.getUserId());
        Post post = new Post();
        post.setId(createRequest.getId());
        post.setText(createRequest.getText());
        post.setTitle(createRequest.getTitle());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, PostUpdateRequest updateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post updatePost = post.get();
            updatePost.setText(updateRequest.getText());
            updatePost.setTitle(updateRequest.getTitle());
            postRepository.save(updatePost);
            return updatePost;
        }
        return null;
    }

    @Override
    public void deleteByPostId(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
