package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<LoginRequestDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(loginRequestDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout() {
        // coockie setup
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }
}
