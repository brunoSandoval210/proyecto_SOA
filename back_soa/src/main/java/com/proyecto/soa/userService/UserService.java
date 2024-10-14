package com.proyecto.soa.userService;

import com.proyecto.soa.userService.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository roleRepository;

    //Metodo para logear un usuario

    //metodo para verificar las credenciales de un usuario

    //Metodo para registrar un usuario
    public User registerUser(UserRequest userRequest){

        List<String> roleRequest = userRequest.roles();

        //Validar si el usuario ya existe
        if (userRepository.existsByDni(userRequest.dni())){
            throw new IllegalArgumentException("El usuario ya existe");
        }

        //Asignar el rol al usuario
        Set<Role> rolesList = roleRepository.findRolesByRoleEnumIn(roleRequest)
                .stream().collect(Collectors.toSet());

        if (rolesList.isEmpty()) {
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        //Crear el usuario
        User user = User.builder()
                .dni(userRequest.dni())
                .name(userRequest.name())
                .lastname(userRequest.lastname())
                .phone(userRequest.phone())
                .email(userRequest.email())
                .password(userRequest.password())
                .roles(rolesList)
                .build();

        return userRepository.save(user);
    }

    //Metodo para actualizar un usuario

    //Metodo para eliminar un usuario

}
