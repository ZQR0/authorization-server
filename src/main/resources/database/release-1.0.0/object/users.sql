--liquibase formatted sql

--changeset yaroslav:users-1
CREATE TABLE IF NOT EXISTS auth_server.users_table
(
    user_id         INTEGER         NOT NULL    GENERATED ALWAYS AS IDENTITY,
    email           VARCHAR(100)    NOT NULL    UNIQUE,
    username        VARCHAR(100)    NOT NULL    UNIQUE,
    password_hash   VARCHAR         NOT NULL,
    birthday        DATE            NOT NULL,
    user_creation_date   TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    user_updating_date   TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    active          BOOLEAN         NOT NULL,
    constraint      users_pk        PRIMARY KEY (user_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_user_u1 ON auth_server.users_table(email);

-- updating function
CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS
$$
    BEGIN
        NEW.user_updating_date = now();
        RETURN NEW;
    END
$$ language 'plpgsql';


-- updating trigger
CREATE TRIGGER updating_date_trigger_users_table BEFORE UPDATE ON auth_server.users_table FOR EACH ROW EXECUTE PROCEDURE update_modified_column();

