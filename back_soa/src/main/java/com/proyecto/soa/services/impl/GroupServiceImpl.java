package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.GroupResponse;
import com.proyecto.soa.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Override
    public List<GroupResponse> getGroupsByUser(Long userId) {
        return List.of();
    }

    @Override
    public GroupResponse createGroup(GroupResponse groupResponse) {
        return null;
    }

    @Override
    public void deleteGroup(Long groupId) {

    }
}
