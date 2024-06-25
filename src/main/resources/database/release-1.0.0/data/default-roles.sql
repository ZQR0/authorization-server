--liquibase formatted sql

--changeset yaroslav:default-roles-2
INSERT INTO auth_server.roles_table(role_name, description, active)
VALUES ('USER', 'Роль простого пользователя', TRUE);


INSERT INTO auth_server.roles_table(role_name, description, active)
VALUES ('ADMIN', 'Роль администратора', TRUE);

