package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.validation.GroupValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupValidation implements GroupValid {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    public Group validGroup(GroupRequest groupRequest) {

        if (groupRepository.existsByName(groupRequest.getName())) {
            throw new IllegalArgumentException("El nombre del grupo ya se encuentra registrado");
        }
        Group group = new Group();
        group.setName(groupRequest.getName());

        modelMapper.map(groupRequest, group);
        return group;
    }
}
