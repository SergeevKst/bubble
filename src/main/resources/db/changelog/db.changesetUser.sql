-- liquibase formatted sql
-- changeset Sokolin:1
INSERT bubbleshop.users(phone_Number, login, first_Name, last_Name, role)
VALUES ('+3747562', 'TopUser', 'Anton', 'Abramovich', 'OWNER');

-- changeset Sokolin:2
INSERT bubbleshop.users(phone_Number, login, first_Name, last_Name, role)
VALUES ('+37475620', 'Manager1', 'Vika', 'Kiseleva', 'MANAGER');