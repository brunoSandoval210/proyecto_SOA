package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdateRquest;
import com.proyecto.soa.services.AuthService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Authcontroller {

    private final AuthService authService;

    @PostMapping("/recuperarContrasena/{email}")
    public ResponseEntity<?> recuperarContrasena(@PathVariable String email)
        throws MessagingException, IOException {

        return ResponseEntity.ok(authService.recuperarContrasena(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/cambiarContrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestBody PasswordUpdateRquest password) {
        System.out.println("tokenUpdate: " + password.getToken());
        return ResponseEntity.ok(authService.cambiarContrasena(
                password, password.getToken(), password.getCode()));
    }
}
