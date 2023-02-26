package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.repository.UserRepository;
import com.robsil.erommerce.model.ERole;
import com.robsil.erommerce.model.Gender;
import com.robsil.erommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InitService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void checkIfAdminUserCreatedIfNotCreateThen() {
        if (userRepository.count() > 0) {
            return;
        }

        var user = User.builder()
                        .firstName("admin")
                        .lastName("admin")
                        .dateOfBirth(LocalDateTime.of(2005, 12, 18, 12, 23, 1))
                        .gender(Gender.MALE)
                        .email("robsil@romest.com")
                        .emailConfirmed(true)
                        .password(passwordEncoder.encode("1414"))
                        .isEnabled(true)
                        .roles(List.of(ERole.SUPERADMIN))
                        .build();

        userService.saveEntity(user);
    }

}
