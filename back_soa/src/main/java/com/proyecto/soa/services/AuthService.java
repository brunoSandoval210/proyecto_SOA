package com.proyecto.soa.services;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface AuthService {
    String recuperarContrasena(String email) throws IOException, MessagingException;
    String cambiarContrasena(String contrasena);
}
