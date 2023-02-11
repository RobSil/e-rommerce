package com.robsil.erommerce.config;

import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    var user = userService.findByEmail(username);

                    return new User(user.getEmail(),
                            user.getPassword(),
                            user.isEnabled(),
                            true,
                            true,
                            true,
                            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList()));
                } catch (EntityNotFoundException e) {
                    throw new UsernameNotFoundException(e.getMessage());
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll();

        return http.build();
    }

}
