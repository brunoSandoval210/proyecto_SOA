package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    //Listar grupos por usuario
    List<Group> findByUserGroups_User_Id(Long userId);

    //verificar si existe un grupo por nombre
    boolean existsByName(String name);

    //Verificar si el usuario pertenece a un grupo
    @Query("SELECT g FROM Group g JOIN UserGroup ug ON g.id = ug.id.groupId WHERE ug.id.userId = :userId")
    List<Group> findByUserGroups_UserId(Long userId);

    boolean existsById(Long groupId);

}
