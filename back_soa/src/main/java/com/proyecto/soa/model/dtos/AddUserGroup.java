package com.proyecto.soa.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddUserGroup {
    private Long groupId;
    private List<Long> usersId;
}
