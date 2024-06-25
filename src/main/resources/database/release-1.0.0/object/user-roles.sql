--liquibase formatted sql

--changeset yaroslav:user-roles-2
CREATE TABLE IF NOT EXISTS auth_server.users_roles
(
      users_roles_id    INTEGER     NOT NULL    GENERATED ALWAYS AS IDENTITY,
      user_id           INTEGER     NOT NULL,
      role_id           INTEGER     NOT NULL,
      constraint    user_roles_pk   PRIMARY KEY(users_roles_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_user_roles_u1 ON auth_server.users_roles(user_id, role_id);

