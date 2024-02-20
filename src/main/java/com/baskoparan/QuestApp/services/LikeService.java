package com.baskoparan.QuestApp.services;

import com.baskoparan.QuestApp.business.requests.LikeCreateRequest;
import com.baskoparan.QuestApp.entities.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like createLike(LikeCreateRequest likeCreateRequest);

    void deleteLike(Long likeId);

    Like findById(Long likeId);
}
