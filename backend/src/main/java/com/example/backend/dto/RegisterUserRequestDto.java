package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterUserRequestDto {
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "nickname should not be empty")
    private String nickname;

    @NotEmpty(message = "password should not be empty")
    private String password;
}
