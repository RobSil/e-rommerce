package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.repository.UserRepository;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.info("getByEmail: user not found. Email: %s".formatted(email));
                    return new EntityNotFoundException("User not found");
                });

        return user;
    }

}