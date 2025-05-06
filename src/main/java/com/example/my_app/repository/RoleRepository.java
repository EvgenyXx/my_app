package com.example.my_app.repository;

import com.example.my_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    boolean existsByRoleName(String roleName);

    Optional<Role>findByRoleName(String roleName);

}
