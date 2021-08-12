INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('PLAYER');

INSERT INTO users (first_name, last_name, email, username, password, role_id)
VALUES ('admin', 'admin', 'ectomorph312@gmail.com', 'admin',
        '$2a$12$P5MGO.rBPTAwmFUegOve7O8c7BdSuJS9GGscYw.HlME4z7C5CnLAG', 1);