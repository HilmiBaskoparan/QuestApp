package com.baskoparan.QuestApp.controllers;

import com.baskoparan.QuestApp.business.requests.CommentCreateRequest;
import com.baskoparan.QuestApp.business.requests.CommentUpdateRequest;
import com.baskoparan.QuestApp.entities.Comment;
import com.baskoparan.QuestApp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @GetMapping("/{commentId}")
    public Comment findById(@PathVariable Long commentId) {
        return commentService.findById(commentId);
    }
}
