package com.example.backend.controller;

import com.example.backend.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<UserDto> getUsers() {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser() {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login() {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout() {
        // coockie setup
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }
}
