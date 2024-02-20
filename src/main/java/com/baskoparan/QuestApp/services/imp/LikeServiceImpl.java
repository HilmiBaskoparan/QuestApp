package com.baskoparan.QuestApp.services.imp;

import com.baskoparan.QuestApp.business.requests.LikeCreateRequest;
import com.baskoparan.QuestApp.entities.Like;
import com.baskoparan.QuestApp.entities.Post;
import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.LikeRepository;
import com.baskoparan.QuestApp.services.LikeService;
import com.baskoparan.QuestApp.services.PostService;
import com.baskoparan.QuestApp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeServiceImpl(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else
            return likeRepository.findAll();
    }

    @Override
    public Like createLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.findById(likeCreateRequest.getUserId());
        Post post = postService.findById(likeCreateRequest.getPostId());

        if (user != null && post != null){
            Like like = new Like();
            like.setId(likeCreateRequest.getId());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            return like;
        }
        return null;
    }

    @Override
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    @Override
    public Like findById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }
}
