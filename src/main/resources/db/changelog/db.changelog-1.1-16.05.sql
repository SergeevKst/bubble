-- liquibase formatted sql
CREATE SCHEMA IF NOT EXISTS bubbleshop;
-- changeset Sokolin:1
CREATE TABLE IF NOT EXISTS bubbleshop.order (
    `id` INT NOT NULL AUTO_INCREMENT,
    `deliveryDate` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- rollback DROP TABLE `order' CASCADE;

-- changeset Sokolin:2
CREATE TABLE IF NOT EXISTS bubbleshop.orderdetails (
    `idorder_details` INT NOT NULL AUTO_INCREMENT,
    `cost` INT NOT NULL,
    `bubble_count` INT NOT NULL,
    `status` VARCHAR(45) NULL DEFAULT NULL,
    `adress` VARCHAR(45) NULL DEFAULT NULL,
    `order_id` INT NOT NULL,
    PRIMARY KEY (`idorder_details`),
    INDEX `fk_orderdetails_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_orderdetails_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `bubbleshop`.`order` (`id`)
);
-- rollback DROP TABLE `orderdetails` CASCADE;

-- changeset Sokolin:3
CREATE TABLE IF NOT EXISTS bubbleshop.storehouse (
    `id` INT NOT NULL,
    `material_name` VARCHAR(45) NULL DEFAULT NULL,
    `material_count` INT NULL DEFAULT NULL,
    `cash` DECIMAL(10,5) NULL,
    PRIMARY KEY (`id`)
    );
-- rollback DROP TABLE `storehouse` CASCADE;

-- changeset Sokolin:4
CREATE TABLE IF NOT EXISTS bubbleshop.users (
    `id` INT NOT NULL AUTO_INCREMENT,
    `phone_Number` VARCHAR(45) NOT NULL,
    `login` VARCHAR(45) NOT NULL,
    `first_Name` VARCHAR(45) NOT NULL,
    `last_Name` VARCHAR(45) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_Number` ASC) VISIBLE
);
-- rollback DROP TABLE `users` CASCADE;

-- changeset Sokolin:5
CREATE TABLE IF NOT EXISTS bubbleshop.order_has_users (
    `order_id` INT NOT NULL,
    `users_id` INT NOT NULL,
    PRIMARY KEY (`order_id`, `users_id`),
    INDEX `fk_order_has_users_users1_idx` (`users_id` ASC) VISIBLE,
    INDEX `fk_order_has_users_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_has_users_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES `bubbleshop`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_has_users_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `bubbleshop`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `order_has_users` CASCADE;

