package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.RegisterUserRequestDto;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.UserResponseDto;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) throws Exception {
        return new ResponseEntity<>(userService.createUser(registerUserRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        return new ResponseEntity<>(userService.loginUser(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout() {
        // coockie setup
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }
}
