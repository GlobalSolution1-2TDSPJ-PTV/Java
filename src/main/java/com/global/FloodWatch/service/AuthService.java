package com.global.FloodWatch.service;

import com.global.FloodWatch.config.security.AuthorizationService;
import com.global.FloodWatch.config.security.TokenService;
import com.global.FloodWatch.dto.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthorizationService authorizationService, PasswordEncoder passwordEncoder, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenService.generateToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails);

        return new AuthResponseDTO(token, refreshToken);

    }

    public AuthResponseDTO refreshToken(String refreshToken) {
        var username = tokenService.extractUsername(refreshToken);
        UserDetails usuario = authorizationService.loadUserByUsername(username);
        if (tokenService.isTokenValid(refreshToken, usuario)) {
            String newToken = tokenService.generateToken(usuario);
            return new AuthResponseDTO(newToken, refreshToken);
        }
        throw new UsernameNotFoundException("Refresh token inválido ou expirado.");
    }

}
