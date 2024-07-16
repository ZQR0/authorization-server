package com.testing.auth_server.dao.repository;


import com.testing.auth_server.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);

    @Query(name = "RoleEntity.getDefaultRole",
            value = "select r from role_entity r where r.roleName='USER'")
    RoleEntity getDefaultRole();
}
