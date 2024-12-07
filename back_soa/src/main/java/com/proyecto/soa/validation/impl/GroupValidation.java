package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.dtos.GroupResponse;
import com.proyecto.soa.model.dtos.UserGroupResponse;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.model.entities.UserGroup;
import com.proyecto.soa.model.entities.UserGroupId;
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

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public Group validCreateGroup(GroupRequest groupRequest) {

        Group group = new Group();
        if (groupRequest.getName() == null || groupRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grupo no puede estar vacío");
        }
        List<UserGroup> userGroups = new ArrayList<>();
        for (Long userId : groupRequest.getUsersId()) {
            UserGroup userGroup = new UserGroup();
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new RuntimeException("User not found with id: " + userId);
            }
            userGroup.setUser(user.get());
            userGroup.setGroup(group);
            userGroup.setId(new UserGroupId(userId, group.getId()));
            userGroups.add(userGroup);
        }
        group.setUserGroups(userGroups);
        group.setName(groupRequest.getName());
        return group;
    }
}
