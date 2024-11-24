package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.dtos.GroupResponse;

import java.util.List;

public interface GroupService {

    GroupResponse getGroupsByUser(Long userId);
    GroupResponse createGroup(GroupRequest groupRequest);
    void deleteGroup(Long groupId);
}
