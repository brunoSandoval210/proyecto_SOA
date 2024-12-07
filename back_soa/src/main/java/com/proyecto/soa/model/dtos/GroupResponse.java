package com.proyecto.soa.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String name;
    private TableKanbanResponse tableKanbanResponse;
    private List<UserGroupResponse> users;

}
