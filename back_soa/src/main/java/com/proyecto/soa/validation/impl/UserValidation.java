package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.entities.Role;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.RoleRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.UserValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation implements UserValid {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public User validUser(UserCreateRequest userCreateRequest) {

        if(userRepository.existsByEmail(userCreateRequest.getEmail())){
            throw new IllegalArgumentException("El email ya se encuentra registrado");
        }
        User user = new User();
        user.setEmail(userCreateRequest.getEmail());
        user.setUsername(userCreateRequest.getEmail());

        Role roleSave = roleRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el rol"));
        user.setRole(roleSave);

        modelMapper.map(userCreateRequest, user);
        return user;
    }
}
