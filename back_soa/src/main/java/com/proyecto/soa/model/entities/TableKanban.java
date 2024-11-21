package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tableros")
@Data
public class TableKanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tableKanban", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColumnTable> columnsTable;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "grupo_id")
    private Group group;
}
