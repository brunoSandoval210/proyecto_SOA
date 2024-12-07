package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.dtos.GroupResponse;
import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;
import com.proyecto.soa.model.entities.Group;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.repositories.TableKanbanRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.GroupService;
import com.proyecto.soa.services.TableKanbanService;
import com.proyecto.soa.validation.GroupValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupValid groupValid;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final TableKanbanService tableKanbanService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public GroupResponse getGroupsByUser(Long userId) {

        Optional<Group> groups = groupRepository.findByUserId(userId);

        return groups.map(value -> modelMapper.map(value, GroupResponse.class)).orElse(null);
    }

    @Transactional
    @Override
    public GroupResponse createGroup(GroupRequest groupRequest) {

        Group group = groupValid.validGroup(groupRequest);
        group.setStatus(1);
        group = groupRepository.save(group);

        //Crear tabla Kanban para el grupo
        TableRequest tableKanban = new TableRequest();
        //tableRequest.setUserId(groupRequest.getUserId()); //Asignar el primer usuario del grupo
        tableKanban.setName("Tablero de " + group.getName());
        tableKanban.setGroupId(group.getId());
        TableKanbanResponse tableKanbanResponse = tableKanbanService.save(tableKanban);

        GroupResponse groupResponse = modelMapper.map(group, GroupResponse.class);
        groupResponse.setTableKanbanResponse(tableKanbanResponse);
        return groupResponse;
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);

    }
}
