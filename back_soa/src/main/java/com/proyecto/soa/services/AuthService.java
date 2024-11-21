package com.proyecto.soa.services;

import com.proyecto.soa.auth.AuthResponse;
import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdateRquest;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface AuthService {
    String recuperarContrasena(String email) throws IOException, MessagingException;
    String cambiarContrasena(PasswordUpdateRquest passwordUpdate, String tokenUpdate, String code);
    AuthResponse login(LoginRequest  loginRequest);
}
