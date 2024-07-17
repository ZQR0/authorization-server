package com.testing.auth_server.dao.repository;

import com.testing.auth_server.dao.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    Optional<AuthorityEntity> findByCode(String code);

    @Query(name = "AuthorityEntity.getReadOwnDataAuthority",
            value = "select a from authority_entity a where a.code='READ_OWN_DATA'")
    AuthorityEntity getReadOwnDataAuthority();

    @Query(name = "AuthorityEntity.getWriteAuthority",
            value = "select a from authority_entity a where a.code='WRITE'")
    AuthorityEntity getWriteAuthority();
}
