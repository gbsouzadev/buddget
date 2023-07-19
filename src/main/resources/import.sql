INSERT INTO tb_user (id, first_name, last_name, email, password, date_created, locked, enabled) VALUES ('5b54f031-48ed-4bda-b3f9-82b50d0f92c1', 'John', 'Doe', 'johndoe@somemail.com', '$2a$12$AqkqmPS2xHOZAGAbTGucDecaYcZisLBScQT6Jk84D1zXoptVm1nzW', TIMESTAMP WITH TIME ZONE '2023-07-05T13:40:00Z', 'f', 't');
INSERT INTO tb_user (id, first_name, last_name, email, password, date_created, locked, enabled) VALUES ('5c63bafb-76a7-42cd-9ae1-76b683f0b8e7', 'Jane', 'Doe', 'janedoe@somemail.com', '$2a$12$JdlLCFtWkZ5OY83pCtHo2OdiSBfemOMUltAGhum3U4ErG17OOecie', TIMESTAMP WITH TIME ZONE '2023-07-05T13:47:00Z', 'f', 't');

INSERT INTO tb_role (id, authority) VALUES ('8b41bade-362b-4fb2-9250-ee9f42f8f29a', 'ROLE_OPERATOR');
INSERT INTO tb_role (id, authority) VALUES ('9c6f764d-4e14-4d2c-8f01-56750d889d3f','ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES ('5b54f031-48ed-4bda-b3f9-82b50d0f92c1', '8b41bade-362b-4fb2-9250-ee9f42f8f29a');
INSERT INTO tb_user_role (user_id, role_id) VALUES ('5c63bafb-76a7-42cd-9ae1-76b683f0b8e7', '8b41bade-362b-4fb2-9250-ee9f42f8f29a');
INSERT INTO tb_user_role (user_id, role_id) VALUES ('5c63bafb-76a7-42cd-9ae1-76b683f0b8e7', '9c6f764d-4e14-4d2c-8f01-56750d889d3f');