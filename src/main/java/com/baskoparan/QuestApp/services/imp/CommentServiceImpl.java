package com.baskoparan.QuestApp.services.imp;

import com.baskoparan.QuestApp.business.requests.CommentCreateRequest;
import com.baskoparan.QuestApp.business.requests.CommentUpdateRequest;
import com.baskoparan.QuestApp.entities.Comment;
import com.baskoparan.QuestApp.entities.Post;
import com.baskoparan.QuestApp.entities.User;
import com.baskoparan.QuestApp.repos.CommentRepository;
import com.baskoparan.QuestApp.services.CommentService;
import com.baskoparan.QuestApp.services.PostService;
import com.baskoparan.QuestApp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    @Override
    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.findById(commentCreateRequest.getUserId());
        Post post = postService.findById(commentCreateRequest.getPostId());

        if (user != null && post != null){
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setUser(user);
            comment.setPost(post);
            comment.setText(commentCreateRequest.getText());
            commentRepository.save(comment);
            return comment;
        } else
            return null;
    }

    @Override
    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment updateComment = comment.get();
            updateComment.setText(commentUpdateRequest.getText());
            commentRepository.save(updateComment);
            return updateComment;
        } else
            return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
