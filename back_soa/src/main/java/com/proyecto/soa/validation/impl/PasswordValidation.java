package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.PasswordValid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordValidation implements PasswordValid {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void validUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El usuario ingresado no existe"));
    }

    @Override
    public void isValidPassword(String password, String validPassword, String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!password.equals(validPassword)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        boolean isPasswordExist=passwordEncoder.matches(password,user.get().getPassword());

        if (isPasswordExist) {
            throw new IllegalArgumentException("La contraseña ingresada es igual a la anterior");
        }
    }
}
