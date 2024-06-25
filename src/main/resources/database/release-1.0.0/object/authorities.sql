--liquibase formatted sql

--changeset yaroslav:authorities-1
CREATE TABLE auth_server.authorities_table
(
    authority_id    INTEGER         NOT NULL    GENERATED ALWAYS AS IDENTITY,
    code            VARCHAR(100)    NOT NULL    UNIQUE,
    description     VARCHAR(100)    NOT NULL,
    system_code     VARCHAR         NOT NULL,
    active          BOOLEAN         NOT NULL    DEFAULT TRUE,
    constraint      authorities_pk  PRIMARY KEY (authority_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_authorities_u1 ON auth_server.authorities_table(code);

