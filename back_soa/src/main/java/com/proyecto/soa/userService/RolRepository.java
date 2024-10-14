package com.proyecto.soa.userService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.name IN :roleNames")
    List<Role> findRolesByRoleEnumIn(List<String> roleNames);
}
