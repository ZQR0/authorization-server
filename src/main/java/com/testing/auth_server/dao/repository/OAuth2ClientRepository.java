package com.testing.auth_server.dao.repository;

import com.testing.auth_server.dao.entity.OAuth2ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2ClientRepository extends JpaRepository<OAuth2ClientEntity, Long>
{
    Optional<OAuth2ClientEntity> findByClientId(String clientId);
}
