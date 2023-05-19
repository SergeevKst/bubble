-- liquibase formatted sql
-- changeset Sokolin:1
INSERT bubbleshop.user(phoneNumber, login, first_name, last_name, role, password)
VALUES ('+3747562', 'TopUser', 'Anton', 'Abramovich', 'OWNER', '$2a$10$Di8CtR9YYJg9kkpKE19S4uxicTVy2WuolJFQX6evILmbTOYF18/5G');

-- changeset Sokolin:2
INSERT bubbleshop.user(phoneNumber, login, first_name, last_name, role, password)
VALUES ('+37475620', 'Manager1', 'Vika', 'Kiseleva', 'MANAGER', '$2a$10$Di8CtR9YYJg9kkpKE19S4uxicTVy2WuolJFQX6evILmbTOYF18/5G');

#password - 1111