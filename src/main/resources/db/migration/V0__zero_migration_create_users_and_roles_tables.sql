CREATE TABLE roles
(
    id   BIGSERIAL UNIQUE PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE          NOT NULL
);

CREATE TABLE users
(
    id         BIGSERIAL UNIQUE PRIMARY KEY NOT NULL,
    first_name VARCHAR(255)                 NOT NULL,
    last_name  VARCHAR(255)                 NOT NULL,
    email      VARCHAR(255) UNIQUE          NOT NULL,
    username   VARCHAR(255)                 NOT NULL,
    password   VARCHAR(255) UNIQUE          NOT NULL,
    is_active  BOOLEAN DEFAULT TRUE         NOT NULL,
    role_id    BIGINT REFERENCES roles (id)
);