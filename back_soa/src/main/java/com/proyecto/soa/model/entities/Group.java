package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "grupo")
@Entity
public class Group extends Maintenance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private TableKanban tableKanban;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}
