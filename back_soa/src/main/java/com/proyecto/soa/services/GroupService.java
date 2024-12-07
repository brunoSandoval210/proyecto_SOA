package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.dtos.GroupResponse;
import com.proyecto.soa.model.dtos.GroupsByUser;

import java.util.List;

public interface GroupService {

    List<GroupResponse> getGroupsById(Long groupId);
    List<GroupsByUser> getGroupsByUser(Long userId);
    GroupResponse createGroup(GroupRequest groupRequest);
    void deleteGroup(Long groupId);
}
