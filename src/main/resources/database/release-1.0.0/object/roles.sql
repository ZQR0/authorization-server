--liquibase formatted sql

--changeset yaroslav:roles-1
CREATE TABLE IF NOT EXISTS auth_server.roles_table
(
    role_id         INTEGER         NOT NULL    GENERATED ALWAYS AS IDENTITY,
    role_name       VARCHAR(50)     NOT NULL    UNIQUE,
    description     VARCHAR(100)    NOT NULL,
    active          BOOLEAN         NOT NULL    DEFAULT TRUE,
    role_creation_date   TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    role_updating_date   TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    constraint      roles_pk        PRIMARY KEY (role_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_roles_u1 ON auth_server.roles_table(role_name);


-- updating function
CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS
$$
    BEGIN
        NEW.role_updating_date = now();
        RETURN NEW;
    END
$$ language 'plpgsql';


-- updating trigger
CREATE TRIGGER updating_date_trigger_roles_table BEFORE UPDATE ON auth_server.users_table FOR EACH ROW EXECUTE PROCEDURE update_modified_column();
