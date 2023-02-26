package com.robsil.erommerce.controller;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.user.UserRegistrationRequest;
import com.robsil.erommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegistrationRequest dto) {
        userService.register(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
