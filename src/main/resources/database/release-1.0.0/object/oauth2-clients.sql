--liquibase formatted sql


--changeset yaroslav:oauth2-clients-6
CREATE TABLE IF NOT EXISTS auth_server.oauth2_clients_table
(
    in_system_client_id             INTEGER            NOT NULL GENERATED ALWAYS AS IDENTITY,
    client_id                       VARCHAR            NOT NULL,
    client_secret                   VARCHAR            NOT NULL,
    client_name                     VARCHAR            NOT NULL,
    client_secret_expires_at        TIMESTAMP          NOT NULL,
    client_authentication_methods   TEXT[]             NOT NULL,
    authorization_grant_types       TEXT[]             NOT NULL,
    redirect_uris                   TEXT[]             NOT NULL,
    scopes                          TEXT[]             NOT NULL,
    constraint oauth2_client_pk     PRIMARY KEY (in_system_client_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_oauth2_clients_u1 ON auth_server.oauth2_clients_table(in_system_client_id);
