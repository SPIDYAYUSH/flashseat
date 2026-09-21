package com.example.flashseat.flashseat.controller;

import com.example.flashseat.flashseat.model.User;
import com.example.flashseat.flashseat.service.JwtService;
import com.example.flashseat.flashseat.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;



    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

//    @GetMapping("/token")
//    public String generateToken() {
//        return jwtService.generateToken("ayush");
//    }

    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
}