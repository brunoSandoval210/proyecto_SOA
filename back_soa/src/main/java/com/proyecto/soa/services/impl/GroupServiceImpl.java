package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.*;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.model.entities.UserGroup;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.repositories.TableKanbanRepository;
import com.proyecto.soa.repositories.UserGroupRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.GroupService;
import com.proyecto.soa.services.TableKanbanService;
import com.proyecto.soa.validation.GroupValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupValid groupValid;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final TableKanbanService tableKanbanService;
    private final UserGroupRepository userGroupRepository;

    @Transactional
    @Override
    public List<GroupsByUser> getGroupsByUser(Long userId) {

        List<Group> groups = groupRepository.findByUserGroups_User_Id(userId);
        List<GroupsByUser> groupsByUsers = new ArrayList<>();
        groups.forEach(group -> {
            GroupsByUser groupsByUser = modelMapper.map(group, GroupsByUser.class);
            groupsByUser.setGroupId(group.getId());
            groupsByUser.setGroupName(group.getName());
            groupsByUsers.add(groupsByUser);
        });
        return groupsByUsers;
    }

    @Transactional
    @Override
    public GroupResponse createGroup(GroupRequest groupRequest) {

        Group group = groupValid.validGroup(groupRequest);
        group.setStatus(1);
        group = groupRepository.save(group);

        TableRequest tableKanban = new TableRequest();
        tableKanban.setName("Tablero de " + group.getName());
        tableKanban.setGroupId(group.getId());
        TableKanbanResponse tableKanbanResponse = tableKanbanService.save(tableKanban);

        GroupResponse groupResponse = modelMapper.map(group, GroupResponse.class);
        groupResponse.setTableKanbanResponse(tableKanbanResponse);

        for (UserGroup userGroup : group.getUserGroups()) {
            userGroup.setGroup(group);
            userGroupRepository.save(userGroup);
        }
        return groupResponse;
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);

    }
}
