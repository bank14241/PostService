package com.sit.cloudnative.PostService.Comment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{post_id}/comment")
    public Comment postComment(@Valid@RequestBody Comment comment, @Valid@RequestParam("id") long user_id, @PathVariable long post_id){
        return commentService.create(comment, post_id, user_id);
    }

    @GetMapping("/{post_id}/comment")
    public ResponseEntity<List<Comment>> getCommentFromPostId(@PathVariable("post_id") long id){
        List<Comment> comments = commentService.getCommetFromPostId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}