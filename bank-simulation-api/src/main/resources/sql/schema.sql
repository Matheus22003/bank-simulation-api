create table IF NOT EXISTS roles
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);
create table IF NOT EXISTS usuarios
(
    id       bigint not null,
    password varchar(255),
    EMAIL varchar(255),
    primary key (id)
);
create table IF NOT EXISTS usuarios_roles
(
    user_id bigint not null,
    roles_id     bigint not null,
    primary key (user_id, roles_id));
