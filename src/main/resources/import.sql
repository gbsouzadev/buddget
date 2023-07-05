INSERT INTO tb_user (first_name, last_name, email, password, date_created) VALUES ('John', 'Doe', 'johndoe@somemail.com', '$2a$12$AqkqmPS2xHOZAGAbTGucDecaYcZisLBScQT6Jk84D1zXoptVm1nzW', TIMESTAMP WITH TIME ZONE '2023-07-05T13:40:00Z');
INSERT INTO tb_user (first_name, last_name, email, password, date_created) VALUES ('Jane', 'Doe', 'janedoe@somemail.com', '$2a$12$JdlLCFtWkZ5OY83pCtHo2OdiSBfemOMUltAGhum3U4ErG17OOecie', TIMESTAMP WITH TIME ZONE '2023-07-05T13:47:00Z');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);