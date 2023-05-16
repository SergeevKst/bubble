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
    `login`       VARCHAR(45) NOT NULL,
    `first_Name`   VARCHAR(45) NOT NULL,
    `last_Name`    VARCHAR(45) NOT NULL,
    `role`        VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC) VISIBLE
);
-- rollback DROP TABLE `users` CASCADE;

-- changeset paramonov:3
CREATE TABLE IF NOT EXISTS bubbleshop.order
(
    `id`           INT  NOT NULL AUTO_INCREMENT,
    `deliveryDate` DATE NULL DEFAULT NULL,
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
CREATE TABLE IF NOT EXISTS bubbleshop.orderdetails
(
    `idorder_details` INT         NOT NULL AUTO_INCREMENT,
    `cost`            INT         NOT NULL,
    `bubble_count`    INT         NOT NULL,
    `status`          VARCHAR(45) NULL DEFAULT NULL,
    `adress`          VARCHAR(45) NULL DEFAULT NULL,
    `order_id`        INT         NOT NULL,
    PRIMARY KEY (`idorder_details`),
    INDEX `fk_orderdetails_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_orderdetails_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES bubbleshop.order (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `orderdetails` CASCADE;

-- changeset paramonov:5
CREATE TABLE IF NOT EXISTS bubbleshop.order_has_employee
(
    `order_id`    INT NOT NULL,
    `employee_id` INT NOT NULL,
    PRIMARY KEY (`order_id`, `employee_id`),
    INDEX `fk_order_has_employee_employee1_idx` (`employee_id` ASC) VISIBLE,
    INDEX `fk_order_has_employee_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_has_employee_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES bubbleshop.order (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_has_employee_employee1`
        FOREIGN KEY (`employee_id`)
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
    `amaterial_count` INT         NULL,
    PRIMARY KEY (`id`)
)
-- rollback DROP TABLE `storehouse` CASCADE;