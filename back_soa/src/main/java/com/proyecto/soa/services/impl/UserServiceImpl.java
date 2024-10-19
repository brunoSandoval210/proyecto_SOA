package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.UserCreate;
import com.proyecto.soa.model.dtos.UserUpdateDTO;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.model.enums.RoleEnum;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.UserService;
import lombok.RequiredArgsConstructor;
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
    public User save(UserCreate user) {
        User userSave=new User();
        userSave.setName(user.getName());
        userSave.setLastname(user.getLastname());
        userSave.setEmail(user.getEmail());
        userSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userSave.setDni(user.getDni());
        userSave.setRole(RoleEnum.valueOf(user.getRole()));
        return userRepository.save(userSave);
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
//            userUpdate.setRoles(getRoles(user));
            //se retorna el objeto userUpdate
            return Optional.of(userRepository.save(userUpdate));
        }
        //se retorna un objeto vacio
        return Optional.empty();
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
