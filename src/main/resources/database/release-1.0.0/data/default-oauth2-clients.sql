--liquibase formatted sql

--changeset yaroslav:default-oauth2-clients-data-2
INSERT INTO auth_server.oauth2_clients_table
(
    client_id,
    client_secret,
    client_name,
    client_secret_expires_at,
    client_authentication_methods,
    authorization_grant_types,
    redirect_uris,
    scopes
)
VALUES (
    'test-client',
    '$2y$10$IH0W0WjL1Qu2y/shf9s5fOFaXzvEkTnEQrpDlnwNWt6WuaQK/VzM2',
    'Test Client',
    '2024-08-20',
    ARRAY['client_secret_basic'],
    ARRAY['authorization_code', 'refresh_token', 'client_credentials'],
    ARRAY['http://localhost:3333/code'],
    ARRAY['openid', 'profile']
);

