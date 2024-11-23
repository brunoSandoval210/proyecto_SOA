package com.proyecto.soa.services;

import com.proyecto.soa.auth.AuthResponse;
import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdateRquest;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface AuthService {
    Map<String, String> recuperarContrasena(String email) throws IOException, MessagingException;
    ResponseEntity<?> cambiarContrasena(PasswordUpdateRquest passwordUpdate);
    AuthResponse login(LoginRequest  loginRequest);
}
