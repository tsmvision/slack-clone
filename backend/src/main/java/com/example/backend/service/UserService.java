package com.example.backend.service;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.RegisterUserRequestDto;
import com.example.backend.dto.UserResponseDto;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream().map(
                user -> UserResponseDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .build()
        ).collect(Collectors.toList());
    }

    public UserResponseDto loginUser(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> foundUserOptional = userRepository.findByEmail(loginRequestDto.getEmail());

        User foundUser = validateFoundUser(foundUserOptional);
        validatePassword(foundUser, loginRequestDto);

        return UserResponseDto
                .builder()
                .id(foundUser.getId())
                .email(foundUser.getEmail())
                .nickname(foundUser.getNickname())
                .build();
    }

    private User validateFoundUser(Optional<User> foundUser) throws Exception {
        if (foundUser.isEmpty()) {
            throw new Exception("USER_NOT_FOUND");
        }
        return foundUser.get();
    }

    private void validatePassword(User foundUser, LoginRequestDto loginRequestDto) throws Exception {
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), foundUser.getPassword())) {
            throw new Exception("Password_NOT_MATCHED");
        }
    }

    public UserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto) throws Exception {
        Optional<User> foundUser = userRepository.findByEmail(registerUserRequestDto.getEmail());

        hasUser(foundUser);

        // TODO: refactor with more simple code here
        Optional<User> user = saveUser(registerUserRequestDto);
        return user.isPresent() ? generateUserResponseDto(user.get()) : new UserResponseDto();
    }

    private void hasUser(Optional<User> user) throws Exception {
        if (user.isPresent()) {
            throw new Exception("USER_ALREADY_EXISTS");
        }
    }

    private Optional<User> saveUser(RegisterUserRequestDto registerUserRequestDto) {
        return Optional.of(
                userRepository.saveAndFlush(
                        new User(
                                registerUserRequestDto.getEmail(),
                                registerUserRequestDto.getNickname(),
                                passwordEncoder.encode(
                                        registerUserRequestDto.getPassword()
                                )
                        )
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
