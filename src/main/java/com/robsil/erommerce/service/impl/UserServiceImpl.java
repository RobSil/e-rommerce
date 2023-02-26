package com.robsil.erommerce.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.repository.UserRepository;
import com.robsil.erommerce.model.ERole;
import com.robsil.erommerce.model.Gender;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.exception.HttpConflictException;
import com.robsil.erommerce.model.exception.UnauthorizedException;
import com.robsil.erommerce.model.user.UserRegistrationRequest;
import com.robsil.erommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.info("getByEmail: user not found. UserID: %s".formatted(userId));
                    return new EntityNotFoundException("User not found");
                });

        return user;
    }

    @Override
    public User findByPrincipal(Principal principal) {
        if (principal == null) throw new UnauthorizedException("UNAUTHORIZED");
        return findByEmail(principal.getName());
    }

    @Override
    public User findByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.info("getByEmail: user not found. Email: %s".formatted(email));
                    return new EntityNotFoundException("User not found");
                });

        return user;
    }

    @Override
    @Transactional
    public User saveEntity(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void register(UserRegistrationRequest dto) {

        if (existsByEmail(dto.getEmail())) {
            throw new HttpConflictException("Email is already occupied.");
        }

        var user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .password(dto.getPassword())
                .build();

        user.getRoles().add(ERole.USER);

        user = saveEntity(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
