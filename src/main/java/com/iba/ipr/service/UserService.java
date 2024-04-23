package com.iba.ipr.service;

import com.iba.ipr.dto.request.LoginAndPasswordDto;
import com.iba.ipr.dto.request.UserPasswordResetRequest;
import com.iba.ipr.dto.request.UserRequestDto;
import com.iba.ipr.dto.response.UserDto;
import com.iba.ipr.entity.User;
import com.iba.ipr.exception.badrequest.UserNotFoundException;
import com.iba.ipr.exception.forbidden.IncorrectCredentialsException;
import com.iba.ipr.mapper.UserMapper;
import com.iba.ipr.repository.UserRepository;
import com.iba.ipr.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User validateUser(LoginAndPasswordDto loginAndPasswordDto) {
        User user = userRepository.findUserByEmail(loginAndPasswordDto.email()).orElseThrow(() -> new IncorrectCredentialsException());
        boolean validPassword = new BCryptPasswordEncoder().matches(loginAndPasswordDto.password(), user.getPassword());
        if (!validPassword) {
            throw new IncorrectCredentialsException();
        }
        return user;
    }

    public UserDto updateUser(UserRequestDto request) {
        User user = userRepository.findById(request.id()).orElseThrow(() -> new UserNotFoundException(request.id()));
        setUserUpdatedInfo(request, user);
        userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    @Transactional
    public void changeUserPassword(
            Long id,
            UserPasswordResetRequest userPasswordResetRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IncorrectCredentialsException());
        if (!isPasswordMatches(userPasswordResetRequest.oldPassword(), user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        updatePassword(userPasswordResetRequest.newPassword(), user);
    }

    private void setUserUpdatedInfo(UserRequestDto request, User user) {
        if (StringUtils.isNotEmpty(request.email()) && !request.email().equals(user.getEmail())) {
            user.setEmail(request.email());
        }

        if (StringUtils.isNotEmpty(request.name()) && !request.name().equals(user.getName())) {
            user.setName(request.name());
        }
    }

    private boolean isPasswordMatches(char[] rawPassword, String passwordHash) {
        StringBuilder passwordBuilder = new StringBuilder().append(rawPassword);
        return passwordEncoder.matches(passwordBuilder, passwordHash);
    }

    private void updatePassword(char[] password, User user) {
        String newEncodedPassword = getEncodedPassword(password);
        user.setPassword(newEncodedPassword);
        userRepository.save(user);
    }

    private String getEncodedPassword(char[] password) {
        StringBuilder builder = new StringBuilder().append(password);
        return passwordEncoder.encode(builder);
    }
}