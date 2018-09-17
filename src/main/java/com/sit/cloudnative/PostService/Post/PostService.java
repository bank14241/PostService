package com.sit.cloudnative.PostService.Post;

import java.util.List;

import javax.validation.Valid;

import com.sit.cloudnative.PostService.Comment.Comment;
import com.sit.cloudnative.PostService.User.User;
import com.sit.cloudnative.PostService.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public String getPostAndComment(Post post, List<Comment> comments){
        String json =   "{"+
                        "   \"id\": \""+post.getId()+"\","+
                        "   \"create_at\": \""+post.getCreateAt()+"\","+
                        "   \"title\": \""+post.getTitle()+"\","+
                        "   \"description\": \""+post.getDescription()+"\","+
                        "   \"user_id\": \""+post.getUser().getFirstname()+" "+post.getUser().getLastname()+"\","+
                        "   \"comments\": [";
        for(int i = 0; i< comments.size(); i++){
            json += "{";
            json += "\"id\": \""+comments.get(i).getId()+"\",";
            json += "\"content\": \""+comments.get(i).getContent()+"\",";
            json += "\"create_at\": \""+comments.get(i).getCreateAt()+"\",";
            json += "\"user_id\": \""+comments.get(i).getUser().getId()+"\"},";
        }
        json = json.substring(0, json.length()-1);
        json += "]}";
        return json;
    }

    public Post create(@Valid Post post, long user_id){
        User user = userService.getUserById(user_id);
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post findPostById(long id){
        return postRepository.getOne(id);
    }


}