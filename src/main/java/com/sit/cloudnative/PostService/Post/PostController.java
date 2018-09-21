package com.sit.cloudnative.PostService.Post;

import java.util.List;

import javax.validation.Valid;

import com.sit.cloudnative.PostService.Comment.Comment;
import com.sit.cloudnative.PostService.Comment.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public ResponseEntity<String> getPostById(@Valid @PathVariable int id){
        Post post = postService.findPostById(id);
        List<Comment> comments = commentService.getCommetFromPostId(id);
        return new ResponseEntity<>(postService.getPostAndComment(post, comments), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost(){
        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/{user_id}/post")
    public Post createPost(@Valid@RequestBody Post post, @PathVariable long user_id){
        return postService.create(post, user_id);
    }

    @DeleteMapping("/post/{post_id}")
    public Post deletePost(@PathVariable long post_id){
        return postService.delete(post_id);
    }
    
}