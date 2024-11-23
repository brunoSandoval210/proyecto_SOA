package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdateRquest;
import com.proyecto.soa.services.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/cambiarContrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestBody PasswordUpdateRquest password) {
        return ResponseEntity.ok(authService.cambiarContrasena(
                password, password.getToken(), password.getCode()));
    }
}
