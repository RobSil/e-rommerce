package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.user.UserRegistrationRequest;

import java.security.Principal;

public interface UserService {

    User findById(Long userId);
    User findByPrincipal(Principal principal);
    User findByEmail(String email);
    User saveEntity(User user);
    void register(UserRegistrationRequest dto);
    boolean existsByEmail(String email);
}
