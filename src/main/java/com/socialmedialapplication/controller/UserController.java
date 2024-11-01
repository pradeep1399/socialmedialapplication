package com.socialmedialapplication.controller;

import com.socialmedialapplication.exception.UserNotFoundException;
import com.socialmedialapplication.model.User;
import com.socialmedialapplication.service.UserDeoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDeoService userDeoService;

    public UserController(UserDeoService userDeoService){
        this.userDeoService = userDeoService;
    }

    //Get /users
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userDeoService.findAll();
    }

    //Get /users/{id}
    @GetMapping("/users/{id}")
    public User retriveUsers(@PathVariable int id){
        User user = userDeoService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id: "+id);
        }
        return user;
    }

    //Post /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody  User user){
       User saveduser =  userDeoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
