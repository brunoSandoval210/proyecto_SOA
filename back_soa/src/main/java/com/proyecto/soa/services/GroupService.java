package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.GroupResponse;

import java.util.List;

public interface GroupService {

    List<GroupResponse> getGroupsByUser(Long userId);
    GroupResponse createGroup(GroupResponse groupResponse);
    void deleteGroup(Long groupId);
}
