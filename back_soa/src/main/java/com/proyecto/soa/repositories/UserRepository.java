package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Trae todos los usuarios paginados
    Page<User> findAll(Pageable pageable);
    //Busca un usuario por su nombre de usuario
    Optional<User> findByEmail(String email);
    //Busca un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);
    //Actualizar una contrasena
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(String password, Long userId);

    boolean existsByEmail(String email);

}
