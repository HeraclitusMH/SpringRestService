package com.heraclitus.springstudyproject.restfulwebservice.controller;

import com.heraclitus.springstudyproject.restfulwebservice.Exceptions.PostNotFoundException;
import com.heraclitus.springstudyproject.restfulwebservice.Exceptions.UserNotFoundException;
import com.heraclitus.springstudyproject.restfulwebservice.beans.Post;
import com.heraclitus.springstudyproject.restfulwebservice.beans.User;
import com.heraclitus.springstudyproject.restfulwebservice.repository.PostRepository;
import com.heraclitus.springstudyproject.restfulwebservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostController(UserRepository userRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUserPosts(@PathVariable Integer id){
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("id not found: " + id);
        }
        return user.get().getPosts();
    }

    @GetMapping("/jpa/posts/{id}")
    public Post getPostById(@PathVariable Integer id){
       Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()){
            throw new PostNotFoundException("id not found: " + id);
        }
        return post.get();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @Valid @RequestBody Post post){
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("id not found: " + id);
        }
        post.setUser(user.get());
        postRepository.save(post);

        /* Retrieve in the header link to get user created */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
