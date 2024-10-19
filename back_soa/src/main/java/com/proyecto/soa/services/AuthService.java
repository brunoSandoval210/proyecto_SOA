package com.proyecto.soa.services;

import com.proyecto.soa.auth.AuthResponse;
import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdate;
import jakarta.mail.MessagingException;

import javax.naming.AuthenticationException;
import java.io.IOException;

public interface AuthService {
    String recuperarContrasena(String email) throws IOException, MessagingException;
    String cambiarContrasena(PasswordUpdate passwordUpdate, String tokenUpdate);
    AuthResponse login(LoginRequest  loginRequest);
}
