package com.proyecto.soa.validation;

import com.proyecto.soa.model.dtos.AddUserGroup;
import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.entities.Group;

public interface GroupValid {

    Group validCreateGroup(GroupRequest groupRequest);
    Group validAddUserToGroup(AddUserGroup addUserGroup);
}
