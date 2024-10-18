package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.AuthService;
import com.proyecto.soa.services.EmailService;
import com.proyecto.soa.validation.PasswordValid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordValid passwordValid;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public String recuperarContrasena(String email) throws IOException, MessagingException {
        passwordValid.validUser(email);
        Optional<User> user = userRepository.findByEmail(email);
        StringBuilder direccionRecuperar = new StringBuilder()
                .append("http://localhost:4200/recuperarContrasena/");
//                .append();
        String content = stringHtml("src/main/resources/templates/email.html")
                .replace("/url/",direccionRecuperar.toString());

        emailService.sendMailWithImage(email,"Recuperar contraseña",content);

        return "Se ha enviado un correo a su dirección de correo electrónico";
    }

    @Override
    public String cambiarContrasena(String contrasena) {
        return "";
    }

    private static String stringHtml(String ruta) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(ruta));

        in.lines().forEach(line -> builder.append(line));
        in.close();

        return builder.toString();
    }
}
