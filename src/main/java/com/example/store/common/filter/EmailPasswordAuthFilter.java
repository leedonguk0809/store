package com.example.store.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailPasswordAuthFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;
    public EmailPasswordAuthFilter(String loginUrl, ObjectMapper objectMapper) {
        super(loginUrl);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException, IOException {
        EmailPassword emailPassword = objectMapper.readValue(request.getInputStream(), EmailPassword.class);
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                emailPassword.email,
                emailPassword.password
        );
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }

    @Getter
    public static class EmailPassword{
        private String email;
        private String password;
    }
}
