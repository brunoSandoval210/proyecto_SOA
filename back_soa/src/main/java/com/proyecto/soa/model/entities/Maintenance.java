package com.proyecto.soa.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Maintenance {
    @Column(name = "usuario_creacion")
    private String userCreate;

    @Column(name = "usuario_actualizacion")
    private String userUpdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime dateRegister;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;


    @Column(name = "estado", columnDefinition = "TINYINT DEFAULT 1")
    private Integer status;
}
