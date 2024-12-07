package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    //Listar grupos por usuario
    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.id = :userId")
    Optional<Group> findByUserId(Long userId);

    //verificar si existe un grupo por nombre
    boolean existsByName(String name);

    boolean existsById(Long groupId);

    //Verificar si un usuario pertenece a un grupo
    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.id = :userId AND g.id = :groupId")
    Optional<Group> existsUserInGroup(Long userId, Long groupId);
}
