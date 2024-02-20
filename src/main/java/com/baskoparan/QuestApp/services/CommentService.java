package com.baskoparan.QuestApp.services;

import com.baskoparan.QuestApp.business.requests.CommentCreateRequest;
import com.baskoparan.QuestApp.business.requests.CommentUpdateRequest;
import com.baskoparan.QuestApp.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment createComment(CommentCreateRequest commentCreateRequest);

    Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest);

    void deleteComment(Long commentId);

    Comment findById(Long commentId);
}
