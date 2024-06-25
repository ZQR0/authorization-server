--liquibase formatted sql

--changeset yaroslav:roles-authorities-data-2
INSERT INTO auth_server.roles_authorities(role_id, authority_id)
VALUES ((SELECT role_id FROM auth_server.roles_table WHERE role_name = 'USER'),
         (SELECT authority_id FROM auth_server.authorities_table WHERE code = 'READ_OWN_DATA'));


INSERT INTO auth_server.roles_authorities(role_id, authority_id)
VALUES ((SELECT role_id FROM auth_server.roles_table WHERE role_name = 'ADMIN'),
         (SELECT authority_id FROM auth_server.authorities_table WHERE code = 'READ_OWN_DATA'));


--changeset yaroslav:roles-authorities-data-3
INSERT INTO auth_server.roles_authorities(role_id, authority_id)
VALUES ((SELECT role_id FROM auth_server.roles_table WHERE role_name = 'ADMIN'),
         (SELECT authority_id FROM auth_server.authorities_table WHERE code = 'WRITE'));