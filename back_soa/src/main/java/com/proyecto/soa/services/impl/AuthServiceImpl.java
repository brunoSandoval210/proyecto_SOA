package com.proyecto.soa.services.impl;

//import com.proyecto.soa.auth.service.CustomUserDetailService;
import com.proyecto.soa.auth.AuthResponse;
import com.proyecto.soa.auth.JwtService;
import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdateRquest;
import com.proyecto.soa.model.entities.HistoryRecuperation;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.HistoryRecuperationRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.AuthService;
import com.proyecto.soa.services.EmailService;
import com.proyecto.soa.utilitarian.RandomCodeGenerator;
import com.proyecto.soa.validation.PasswordValid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordValid passwordValid;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final HistoryRecuperationRepository historyRecuperationRepository;
    private final RandomCodeGenerator randomCodeGenerator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Transactional
    @Override
    public Map<String, String> recuperarContrasena(String email) throws IOException, MessagingException {
        passwordValid.validUser(email);
        Optional<User> user = userRepository.findByEmail(email);
        String code = randomCodeGenerator.generateRandomCode(5);

        String content = stringHtml("templates/EmailRecuperacion.html")
                .replace("{code}",code);


        HistoryRecuperation historyRecuperation = new HistoryRecuperation();
        historyRecuperation.setCode(code);
        historyRecuperation.setUser(user.get());
        historyRecuperationRepository.save(historyRecuperation);

        emailService.sendMail(email, "Recuperar contraseña", content);


        Map<String, String> response = new HashMap<>();
        response.put("message", "Se ha enviado un correo a su dirección de correo electrónico");
        return response;
    }

    @Transactional
    @Override
    public ResponseEntity<?> cambiarContrasena(PasswordUpdateRquest passwordUpdate) {
        HistoryRecuperation codeData = historyRecuperationRepository.findByCode(passwordUpdate.getCode());
        Optional<User> userUpdatePassword = userRepository.findByEmail(codeData.getUser().getEmail());

        Map<String, String> response = new HashMap<>();
        if (codeData == null || !codeData.getCode().equals(passwordUpdate.getCode())) {
            response.put("message", "El código no es correcto");
            return ResponseEntity.status(400).body(response);
        }

        passwordValid.isValidPassword(
                passwordUpdate.getPassword(), passwordUpdate.getValidPassword(), codeData.getUser().getEmail());
        userRepository.updatePassword(
                passwordEncoder.encode(passwordUpdate.getPassword()), userUpdatePassword.get().getId());
        response.put("message", "Se ha actualizado la contraseña");

        return ResponseEntity.ok(response);
    }

    @Transactional
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
        User user=userRepository.findByEmail(loginRequest.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .message("Las credenciales son correctas")
                .token(token)
                .build();
    }

    private static String stringHtml(String resourcePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
