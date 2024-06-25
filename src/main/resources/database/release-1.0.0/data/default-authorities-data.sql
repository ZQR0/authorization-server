--liquibase formatted sql

--changeset yaroslav:default-authorities-data-2
INSERT INTO auth_server.authorities_table (code, description, system_code, active)
VALUES ('READ_OWN_DATA', 'read own data', 'SSO', TRUE);


INSERT INTO auth_server.authorities_table (code, description, system_code, active)
VALUES ('WRITE', 'write messages', 'SSO', TRUE);

