--liquibase formatted sql

--changeset yaroslav:def-default-users-2
INSERT INTO auth_server.users_table (email, username, password_hash, birthday, active)
VALUES ('user@gmail.com', 'user', '$2y$10$rVcRFIBjOALtLGB0UdIR4OkoCYIthWeml0DBV/5H2MX/LlCThJBEu', '2006-04-21', TRUE);


INSERT INTO auth_server.users_table (email, username, password_hash, birthday, active)
VALUES ('admin@gmail.com', 'admin', '$2y$10$deGmCR0wT1A9xrD/AyfNpOjDJxLxZf6JTeckkT.w0EsPoH5jv4Vze', '2006-04-21', TRUE);

