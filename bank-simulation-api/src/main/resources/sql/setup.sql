INSERT INTO usuarios (id, email, password)
VALUES (999999, 'root', '$2a$10$OuBwXpODYwfStksmwdFpVeIuuxZ7u0QNL/tVyexrCzP56CxsPQexW');

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO USUARIOS_ROLES (user_id, roles_id)
VALUES (999999, 1);