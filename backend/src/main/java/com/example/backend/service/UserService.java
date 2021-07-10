package com.example.backend.service;

import com.example.backend.dto.RegisterUserRequestDto;
import com.example.backend.dto.UserResponseDto;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto) throws Exception {
        Optional<User> foundUser = userRepository.findByEmail(registerUserRequestDto.getEmail());

        hasUser(foundUser);

        // TODO: refactor with more simple code here
        Optional<User> user = saveUser(registerUserRequestDto);
        return user.isPresent() ? generateUserResponseDto(user.get()) : new UserResponseDto();
    }

    private void hasUser(Optional<User> user) throws Exception {
        if (user.isPresent()) {
            throw new Exception("EMAIL_ALREADY_EXISTS");
        }
    }

    private Optional<User> saveUser(RegisterUserRequestDto registerUserRequestDto) {
        return Optional.of(userRepository.saveAndFlush(
                User.builder()
                        .email(registerUserRequestDto.getEmail())
                        .nickname(registerUserRequestDto.getNickname())
                        .password(passwordEncoder.encode(registerUserRequestDto.getPassword()))
                        .build()
                )
        );
    }

    private UserResponseDto generateUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .id(user.getId())
                .build();
    }
}
