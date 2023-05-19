-- liquibase formatted sql
CREATE DATABASE IF NOT EXISTS bubbleshop;
-- changeset paramonov:1
CREATE TABLE IF NOT EXISTS bubbleshop.employee
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `employee` CASCADE;

-- changeset paramonov:2
CREATE TABLE IF NOT EXISTS bubbleshop.users
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `phoneNumber` VARCHAR(45) NOT NULL,
    `login`       VARCHAR(45) UNIQUE NOT NULL,
    `first_Name`   VARCHAR(45) NOT NULL,
    `last_Name`    VARCHAR(45) NOT NULL,
    `role`        VARCHAR(45) NOT NULL,
    `password`      VARCHAR(350) NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC) VISIBLE
);
-- rollback DROP TABLE `users` CASCADE;

-- changeset paramonov:3
CREATE TABLE IF NOT EXISTS bubbleshop.order
(
    `id`           INT  NOT NULL AUTO_INCREMENT,
    `user_id`      INT  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_user_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_user`
        FOREIGN KEY (`user_id`)
            REFERENCES bubbleshop.users (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `order` CASCADE;

-- changeset paramonov:4
CREATE TABLE IF NOT EXISTS bubbleshop.order_details
(
    `id` INT         NOT NULL AUTO_INCREMENT,
    `cost`            DECIMAL         NOT NULL,
    `bubble_count`    INT         NOT NULL,
    `delivery_date` DATE NULL DEFAULT NULL,
    `status`          VARCHAR(45) NULL DEFAULT NULL,
    `city`          VARCHAR(45) NULL DEFAULT NULL,
    `street`          VARCHAR(45) NULL DEFAULT NULL,
    `house_number`          INT NULL DEFAULT NULL,
    `apartment_number`          INT NULL DEFAULT NULL,
    `order_id`        INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_orderdetails_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_orderdetails_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES bubbleshop.order (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `orderdetails` CASCADE;

-- changeset paramonov:5
CREATE TABLE IF NOT EXISTS bubbleshop.order_users
(
    `order_id`    INT NOT NULL,
    users_id INT NOT NULL,
    PRIMARY KEY (`order_id`, users_id),
    INDEX `fk_order_has_employee_employee1_idx` (users_id ASC) VISIBLE,
    INDEX `fk_order_has_employee_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_has_employee_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES bubbleshop.order (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_has_employee_employee1`
        FOREIGN KEY (users_id)
            REFERENCES bubbleshop.employee (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `order_has_employee` CASCADE;

-- changeset paramonov:6
CREATE TABLE IF NOT EXISTS bubbleshop.storehouse
(
    `id`              INT         NOT NULL,
    `material_name`   VARCHAR(45) NULL,
    `material_count` INT         NULL,
    `balance`       DECIMAL NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `storehouse` CASCADE;
