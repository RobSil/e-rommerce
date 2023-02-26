package com.robsil.erommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.exception.UnauthorizedException;
import com.robsil.erommerce.service.UserService;
import com.robsil.erommerce.service.dtoMapper.UserDtoMapper;
import com.robsil.erommerce.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final UserDtoMapper userDtoMapper;

    @Value("${spring.authentication.unauthorized-message}")
    private String unauthorizedMessage;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                return userService.findByEmail(username);
            } catch (EntityNotFoundException e) {
                throw new UsernameNotFoundException(e.getMessage());
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http.csrf().disable();

//        http.authorizeHttpRequests()
//                .requestMatchers("/**")
//                .permitAll();

        http.userDetailsService(userDetailsService);
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

//        http
//                .exceptionHandling()
//                    .authenticationEntryPoint((request, response, authException) -> {
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.getWriter().write(unauthorizedMessage);
//                    response.getWriter().flush();
//                });

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(5);

        http
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(((request, response, authentication) -> {
                    response.setStatus(HttpStatus.OK.value());

                    try {
                        var user = userService.findByEmail(AuthenticationUtil.getNameFromAuthentication(authentication));

                        response.getWriter().write(objectMapper.writeValueAsString(userDtoMapper.apply(user)));
                        response.getWriter().flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new UnauthorizedException("Occurred unexpectable thing during fetching user.", e);
                    }
                }))
                .failureHandler(((request, response, exception) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write(unauthorizedMessage);
                    response.getWriter().flush();
                }))
                ;

        http
                .logout()
                .logoutUrl("/api/logout")
                .invalidateHttpSession(true)
                .deleteCookies("SESSION", "JSESSIONID")
                .logoutSuccessHandler(((request, response, authentication) -> response.setStatus(HttpStatus.OK.value())));

//        http
//                .authorizeHttpRequests()
//                .anyRequest()
//                .permitAll();

        http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/login",
                        "/login"
                )
                .permitAll();

        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/**")
                .authenticated();

        return http.build();
    }

}
