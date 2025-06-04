package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.AuthRequestDTO;
import com.global.FloodWatch.dto.AuthResponseDTO;
import com.global.FloodWatch.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        if(authRequestDTO.email() == null || authRequestDTO.password() == null) {
            return ResponseEntity.badRequest().build();
        }
        var response = authService.authenticate(authRequestDTO.email(), authRequestDTO.password());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refreshToken(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        if(authRequestDTO.refreshToken() == null) {
            return ResponseEntity.badRequest().build();
        }
        var response = authService.refreshToken(authRequestDTO.refreshToken());
        return ResponseEntity.ok(response);
    }
}
