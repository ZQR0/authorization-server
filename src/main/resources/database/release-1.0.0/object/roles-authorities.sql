--liquibase formatted sql

--changeset yaroslav:roles-authorities-2
CREATE TABLE IF NOT EXISTS auth_server.roles_authorities
(
    role_authority_id   INTEGER     NOT NULL    GENERATED ALWAYS AS IDENTITY,
    role_id             INTEGER     NOT NULL,
    authority_id        INTEGER     NOT NULL,
    constraint          role_authorities_pk     PRIMARY KEY(role_authority_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_roles_authorities_u1 ON auth_server.roles_authorities(role_id, authority_id);
