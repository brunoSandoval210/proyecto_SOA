package com.proyecto.soa.services;

import com.proyecto.soa.model.IUser;
import com.proyecto.soa.model.dtos.UserUpdateDTO;
import com.proyecto.soa.model.entities.Role;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.RolRepository;
import com.proyecto.soa.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RolRepository rolRepository;
//    private PasswordEncoder passwordEncoder;

    //Se injecta las dependencias
    public UserServiceImpl(
            UserRepository userRepository,
            RolRepository rolRepository
//            PasswordEncoder passwordEncoder
    ){
        this.userRepository=userRepository;
        this.rolRepository=rolRepository;
//        this.passwordEncoder=passwordEncoder;
    }

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
        user.setRoles(getRoles(user));
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
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isPresent()){
            //se inicializa el objeto userUpdate con el objeto userOptional
            User userUpdate=userOptional.get();
            userUpdate.setName(user.getName());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setRoles(getRoles(user));
            //se retorna el objeto userUpdate
            return Optional.of(userRepository.save(userUpdate));
        }
        //se retorna un objeto vacio
        return Optional.empty();
    }

//    public com.proyecto.soa.userService.User registerUser(UserRequest userRequest){
//
//        List<String> roleRequest = userRequest.roles();
//
//        //Validar si el usuario ya existe
//        if (userRepository.existsByDni(userRequest.dni())){
//            throw new IllegalArgumentException("El usuario ya existe");
//        }
//
//        //Asignar el rol al usuario
//        Set<com.proyecto.soa.userService.Role> rolesList = roleRepository.findRolesByRoleEnumIn(roleRequest)
//                .stream().collect(Collectors.toSet());
//
//        if (rolesList.isEmpty()) {
//            throw new IllegalArgumentException("Los roles especificados no existen");
//        }
//
//        //Crear el usuario
//        com.proyecto.soa.userService.User user = com.proyecto.soa.userService.User.builder()
//                .dni(userRequest.dni())
//                .name(userRequest.name())
//                .lastname(userRequest.lastname())
//                .phone(userRequest.phone())
//                .email(userRequest.email())
//                .password(userRequest.password())
//                .roles(rolesList)
//                .build();
//
//        return userRepository.save(user);
//    }

    private List<Role> getRoles(IUser user){
        List<Role> roles=new ArrayList<>();
        //Se busca el rol por nombre
        Optional<Role> optionalRoleUser=rolRepository.findByName("ROLE_USER");
        //Si se encuentra el rol se agrega a la lista de roles
        optionalRoleUser.ifPresent(role->roles.add(role));
        if(user.isAdmin()){
            //Se busca el rol por nombre
            Optional<Role>optionalRoleAdmin=rolRepository.findByName("ROLE_ADMIN");
            //Si se encuentra el rol se agrega a la lista de roles
            optionalRoleAdmin.ifPresent(role->roles.add(role));
        }
        if(user.isDoctor()){
            //Se busca el rol por nombre
            Optional<Role>optionalRoleDoctor=rolRepository.findByName("ROLE_DOCTOR");
            //Si se encuentra el rol se agrega a la lista de roles
            optionalRoleDoctor.ifPresent(role->roles.add(role));
        }
        return roles;
    }
}
