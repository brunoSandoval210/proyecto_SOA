package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.UserValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation implements UserValid {

    private final UserRepository userRepository;

    @Override
    public void validUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario ingresado no existe"));
    }

    @Override
    public User validUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El email ingresado ya existe"));

        return user;
    }
}
