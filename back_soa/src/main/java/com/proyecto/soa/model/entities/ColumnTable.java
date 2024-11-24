package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "column_tableros")
public class ColumnTable extends Maintenance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "tablero_id", nullable = false)
    private TableKanban tableKanban;

    @OneToMany(mappedBy = "columnsTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
