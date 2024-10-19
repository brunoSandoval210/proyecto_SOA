package com.proyecto.soa.services.impl;

//import com.proyecto.soa.auth.service.CustomUserDetailService;
import com.proyecto.soa.auth.AuthResponse;
import com.proyecto.soa.auth.JwtService;
import com.proyecto.soa.model.dtos.LoginRequest;
import com.proyecto.soa.model.dtos.PasswordUpdate;
import com.proyecto.soa.model.dtos.UserDetailsDTO;
import com.proyecto.soa.model.entities.HistoryRecuperation;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.HistoryRecuperationRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.AuthService;
import com.proyecto.soa.services.EmailService;
import com.proyecto.soa.validation.PasswordValid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final HistoryRecuperationRepository historyRecuperationRepository;
    private final RandomCodeGenerator randomCodeGenerator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public String recuperarContrasena(String email) throws IOException, MessagingException {
        passwordValid.validUser(email);
        Optional<User> user = userRepository.findByEmail(email);
        String code = randomCodeGenerator.generateRandomCode(5);

        StringBuilder direccionRecuperar = new StringBuilder()
                //   .append("http://localhost:4200/index.html?token=")
                .append("google.com/index.html?email=")
                .append(user.get().getEmail());
//                .append();
        String content = stringHtml("src/main/resources/templates/EmailRecuperacion.html")
                .replace("/url/",direccionRecuperar.toString())
                .replace("{code}",code);


        HistoryRecuperation historyRecuperation = new HistoryRecuperation();
        historyRecuperation.setCode(passwordEncoder.encode(code));
        historyRecuperation.setUser(user.get());
        historyRecuperationRepository.save(historyRecuperation);


        emailService.sendMail(email,"Recuperar contraseña",content);

        return "Se ha enviado un correo a su dirección de correo electrónico";
    }

    @Override
    public String cambiarContrasena(PasswordUpdate passwordUpdate, String tokenUpdate){
        String email=passwordEncoder.encode(tokenUpdate);
        User userUpdatePassword=userRepository.findByEmail(email).get();
        passwordValid.isValidPassword(
                passwordUpdate.getPassword(), passwordUpdate.getValidPassword(),email);
        //falta validar que el token sea el mismo que el de la base de datos
        userRepository.updatePassword(passwordUpdate.getPassword(),userUpdatePassword.getId());
        return "Se ha actualizado la contraseña";
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails user=modelMapper.map()


                userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    private static String stringHtml(String ruta) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new FileReader(ruta));

        in.lines().forEach(line -> builder.append(line));
        in.close();

        return builder.toString();
    }
}
