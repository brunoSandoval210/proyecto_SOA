package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_grupo")
public class UserGroup {
    @EmbeddedId
    private UserGroupId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "grupo_id")
    private Group group;

}
