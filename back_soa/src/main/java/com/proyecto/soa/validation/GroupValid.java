package com.proyecto.soa.validation;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.entities.Group;

public interface GroupValid {

    Group validGetGroupById(Long groupId);
    Group validCreateGroup(GroupRequest groupRequest);

}
