package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.*;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.model.entities.UserGroup;
import com.proyecto.soa.repositories.GroupRepository;
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
    private final UserRepository userRepository;

    @Transactional
    @Override
    public GroupResponse getGroupById(Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);

        if (group.isPresent()){
            GroupResponse groupResponse = modelMapper.map(group.get(), GroupResponse.class);

            //Mapear la tabla kanban
            if (group.get().getTableKanban() != null){
                TableKanbanResponse tableKanbanResponse = modelMapper.map(group.get().getTableKanban(), TableKanbanResponse.class);
                groupResponse.setTableKanbanResponse(tableKanbanResponse);
            }

            //Mapear los usuarios
            List<UserGroupResponse> userGroupResponses = new ArrayList<>();
            for (UserGroup userGroup : group.get().getUserGroups()){
                UserGroupResponse userGroupResponse = modelMapper.map(userGroup.getUser(), UserGroupResponse.class);
                userGroupResponses.add(userGroupResponse);
            }
            groupResponse.setUsers(userGroupResponses);

            return groupResponse;
        }
        return null;
    }

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

        Group group = groupValid.validCreateGroup(groupRequest);
        group.setStatus(1);
        group = groupRepository.save(group);

        TableRequest tableKanban = new TableRequest();
        tableKanban.setName("Tablero de " + group.getName());
        tableKanban.setGroupId(group.getId());
        TableKanbanResponse tableKanbanResponse = tableKanbanService.save(tableKanban);

        // Mapear el grupo a GroupResponse
        GroupResponse groupResponse = modelMapper.map(group, GroupResponse.class);
        List<UserGroupResponse> userGroupResponses = new ArrayList<>();
        for (Long userId : groupRequest.getUsersId()) {
            Optional<User> user = userRepository.findById(userId);
            UserGroupResponse userGroupResponse = modelMapper.map(user, UserGroupResponse.class);
            userGroupResponses.add(userGroupResponse);
        }
        groupResponse.setUsers(userGroupResponses);
        groupResponse.setTableKanbanResponse(tableKanbanResponse);

        for (UserGroup userGroup : group.getUserGroups()) {
            userGroup.setGroup(group);
            userGroupRepository.save(userGroup);
        }
        return groupResponse;
    }

    @Override
    public void AddUserToGroup(AddUserGroup addUserGroup) {
        Group group = groupValid.validAddUserToGroup(addUserGroup);
        groupRepository.save(group);

    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);

    }
}
