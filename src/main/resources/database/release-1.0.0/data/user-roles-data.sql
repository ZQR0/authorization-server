--liquibase formatted sql

--changeset yaroslav:user-roles-data-2
INSERT INTO auth_server.users_roles (user_id, role_id)
SELECT user_id, (SELECT role_id FROM auth_server.roles_table WHERE role_name = 'USER')
FROM auth_server.users_table
ON CONFLICT DO NOTHING;


INSERT INTO auth_server.users_roles (user_id, role_id)
SELECT user_id, (SELECT role_id FROM auth_server.roles_table WHERE role_name = 'ADMIN')
FROM auth_server.users_table
ON CONFLICT DO NOTHING;
