package com.robsil.erommerce.util;

import com.robsil.erommerce.model.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public class AuthenticationUtil {

    public static String getNameFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }

        throw new UnauthorizedException("Failed to get name from authentication.");
    }

}
