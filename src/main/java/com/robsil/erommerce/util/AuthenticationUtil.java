package com.robsil.erommerce.util;

import com.robsil.erommerce.model.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public class AuthenticationUtil {

    private AuthenticationUtil() {}

    public static String getNameFromAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }

        if (authentication.getPrincipal() instanceof Principal principal) {
            return principal.getName();
        }

        throw new UnauthorizedException("Failed to get name from authentication.");
    }

}
