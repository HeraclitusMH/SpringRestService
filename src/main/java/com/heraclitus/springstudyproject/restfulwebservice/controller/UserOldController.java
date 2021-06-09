package com.heraclitus.springstudyproject.restfulwebservice.controller;

import com.heraclitus.springstudyproject.restfulwebservice.Exceptions.UserNotFoundException;
import com.heraclitus.springstudyproject.restfulwebservice.beans.User;
import com.heraclitus.springstudyproject.restfulwebservice.dao.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserOldController {

    @Autowired
    private UserDaoService service;

    @GetMapping("old/users")
    public List<User> getAllUsers(){
        return service.findAllUsers();
    }

    @GetMapping("old/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
        User user = service.findUserById(id);
        if (user == null){
            throw new UserNotFoundException("id not found: " + id);
        }

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users"));

        return model;
    }

    @PostMapping("old/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.saveUser(user);
        /* Retrieve in the header link to get user created */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("old/users/{id}")
    public void deleteUserById(@PathVariable Integer id){
        User user = service.deleteUserById(id);
        if (user == null){
            throw new UserNotFoundException("id not found: " + id);
        }
    }
}
