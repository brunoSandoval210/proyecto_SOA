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

@Component
@RequiredArgsConstructor
public class GroupValidation implements GroupValid {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public Group validGroup(GroupRequest groupRequest) {

        if (groupRepository.existsByName(groupRequest.getName())) {
            throw new IllegalArgumentException("El nombre del grupo ya se encuentra registrado");
        }
        Group group = new Group();
        modelMapper.map(groupRequest, group);
        group.setName(groupRequest.getName());
        group.setStatus(1);
        List<User> userList=userRepository.findUsersByIds(groupRequest.getUserId());
        group.setUsers(userList);
        return group;
    }
}
