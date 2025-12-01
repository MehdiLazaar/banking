package org.appynov.banking.infrastructure.application.driving.web.security;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.appynov.banking.infrastructure.driven.jpa.JpaUserSpringRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final JpaUserSpringRepository userRepo;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, JpaUserSpringRepository userRepo) {
        this.jwtTokenService = jwtTokenService;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        String queryAuth = request.getParameter("authorization");
        if ((auth != null && auth.startsWith("Bearer ")) || queryAuth != null) {
            String token = auth != null ? auth.substring(7) : queryAuth;
            try {
                Map<String, Object> claims = jwtTokenService.parseClaims(token);
                String login = (String) claims.getOrDefault("sub", claims.get("username"));
                String clientIdStr = (String) claims.get("clientId");
                if (login != null && clientIdStr != null) {
                    var opt = userRepo.findByUsernameIgnoreCase(login);
                    if (opt.isPresent()) {
                        var principal = new MyBankPrincipal(login, clientIdStr);
                        var authToken = new UsernamePasswordAuthenticationToken(principal, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception ignored) {
            }
        }
        filterChain.doFilter(request, response);
    }

    public record MyBankPrincipal(String login, String clientId) {
    }
}