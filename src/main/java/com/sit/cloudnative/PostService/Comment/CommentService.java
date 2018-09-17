package com.sit.cloudnative.PostService.Comment;

import java.util.List;

import javax.validation.Valid;

import com.sit.cloudnative.PostService.Post.Post;
import com.sit.cloudnative.PostService.Post.PostService;
import com.sit.cloudnative.PostService.User.User;
import com.sit.cloudnative.PostService.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public Comment create(@Valid Comment comment, long post_id, long user_id){
        Post post = postService.findPostById(post_id);
        User user = userService.getUserById(user_id);
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommetFromPostId(long post_id){
        return commentRepository.findByPostId(post_id);        
    }
}