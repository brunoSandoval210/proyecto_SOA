package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.dtos.UserUpdateRequest;
import com.proyecto.soa.model.entities.Role;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.RoleRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.UserService;
import com.proyecto.soa.validation.impl.UserValidation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final UserValidation userValidation;

    @Transactional(readOnly = true)
    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public User save(UserCreateRequest user) {

        User userSave = modelMapper.map(user, User.class);
        userSave.setUsername(user.getEmail());

        //Validaciones
        userValidation.validUserEmail(user.getEmail());

        Role roleSave = roleRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el rol"));

        userSave.setRole(roleSave);
        userSave.setStatus(1);
        userSave.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userSave);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<User> update(UserUpdateRequest user, Long id) {
        Optional<User> userOptional=userRepository.findById(id);

        if(userOptional.isPresent()){
            User userUpdate=userOptional.get();
            userUpdate.setName(user.getName());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setEmail(user.getEmail());
            return Optional.of(userRepository.save(userUpdate));
        }
        return Optional.empty();
    }

}
