package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;

import java.security.Principal;

public interface UserService {

    User findById(String userId);
    User findByPrincipal(Principal principal);
    User findByEmail(String email);

}
