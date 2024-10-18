package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.UserUpdateDTO;
import com.proyecto.soa.model.entities.Role;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.RolRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.UserService;
import com.proyecto.soa.validation.UserValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValid userValid;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserValid userValid, UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userValid = userValid;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    //Se injecta las dependencias

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
    public User save(User user) {
//        user.setRoles(getRoles(user));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<User> update(UserUpdateDTO user, Long id) {
        userValid.validUser(id);
        User userUpdate = userRepository.findById(id).orElse(null);
        modelMapper.map(user,userUpdate);
        return Optional.of(userRepository.save(userUpdate));
    }

//    private List<Role> getRoles(IUser user){
//        List<Role> roles=new ArrayList<>();
//        //Se busca el rol por nombre
//        Optional<Role> optionalRoleUser=rolRepository.findByName("ROLE_USER");
//        //Si se encuentra el rol se agrega a la lista de roles
//        optionalRoleUser.ifPresent(role->roles.add(role));
//        if(user.isAdmin()){
//            //Se busca el rol por nombre
//            Optional<Role>optionalRoleAdmin=rolRepository.findByName("ROLE_ADMIN");
//            //Si se encuentra el rol se agrega a la lista de roles
//            optionalRoleAdmin.ifPresent(role->roles.add(role));
//        }
//        if(user.isDoctor()){
//            //Se busca el rol por nombre
//            Optional<Role>optionalRoleDoctor=rolRepository.findByName("ROLE_DOCTOR");
//            //Si se encuentra el rol se agrega a la lista de roles
//            optionalRoleDoctor.ifPresent(role->roles.add(role));
//        }
//        return roles;
//    }
}
