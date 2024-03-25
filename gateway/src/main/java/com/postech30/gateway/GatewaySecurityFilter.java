package com.postech30.gateway;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GatewaySecurityFilter extends OncePerRequestFilter {
    private final GatewayTokenService tokenService;

    public GatewaySecurityFilter(GatewayTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "É necessário um token de autenticação");
            return;
        }

        var login = tokenService.validateToken(token);
        if(login.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido ou expirado");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
