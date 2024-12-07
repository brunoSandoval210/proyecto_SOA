package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.GroupValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroupValidation implements GroupValid {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Group validGroup(GroupRequest groupRequest) {

        Group group = new Group();

        //Verificar que el nombre del grupo no esté vacio
        if (groupRequest.getName() == null || groupRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grupo no puede estar vacío");
        }

//        if (groupRepository.existsByName(groupRequest.getName())) {
//            throw new IllegalArgumentException("El nombre del grupo ya se encuentra registrado");
//        }

        List<User> users = new ArrayList<>();

        //buscar el usuario por id
        for (Long userId : groupRequest.getUsersId()) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new RuntimeException("User not found with id: " + userId);
            }
            users.add(user.get());
        }

        // Verificar si el usuario está presente y pasarlo a una lista
        group.setUsers(users);
        group.setName(groupRequest.getName());
        for (User userR: group.getUsers()) {
            System.out.println(userR.getName());
        }
        return group;
    }
}
