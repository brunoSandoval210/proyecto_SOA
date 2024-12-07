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

    boolean existsById(Long groupId);

}
