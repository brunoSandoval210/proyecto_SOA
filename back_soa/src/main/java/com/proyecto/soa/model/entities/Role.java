package com.proyecto.soa.model.entities;

import com.proyecto.soa.model.enums.RoleEnum;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rol")
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
}
