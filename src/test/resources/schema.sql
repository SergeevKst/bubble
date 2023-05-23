-- liquibase formatted sql
CREATE DATABASE IF NOT EXISTS bubbleshop;
-- changeset sokolin:1
CREATE TABLE IF NOT EXISTS bubbleshop.order
(
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `order` CASCADE;

-- changeset sokolin:2
CREATE TABLE IF NOT EXISTS bubbleshop.order_details
(
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `cost`             DECIMAL(10, 2) NOT NULL,
    `bubble_count`     INT            NOT NULL,
    `delivery_date`    DATE           NULL DEFAULT NULL,
    `create_date`      DATE           NULL DEFAULT NULL,
    `status`           VARCHAR(45)    NULL DEFAULT NULL,
    `order_id`         INT            NOT NULL,
    `city`             VARCHAR(45)    NULL,
    `street`           VARCHAR(45)    NULL,
    `house_number`     INT            NULL,
    `apartment_number` INT            NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_orderdetails_order1_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `fk_orderdetails_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES `bubbleshop`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `orderdetails` CASCADE;

-- changeset sokolin:3
CREATE TABLE IF NOT EXISTS bubbleshop.user
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `phoneNumber` VARCHAR(45)  NOT NULL,
    `login`       VARCHAR(45)  NOT NULL,
    `first_name`  VARCHAR(45)  NOT NULL,
    `last_name`   VARCHAR(45)  NOT NULL,
    `role`        VARCHAR(45)  NOT NULL,
    `password`    VARCHAR(300) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
);
-- rollback DROP TABLE `user` CASCADE;

-- changeset sokolin:4
CREATE TABLE IF NOT EXISTS bubbleshop.user_order
(
    `user_id`  INT NOT NULL,
    `order_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `order_id`),
    INDEX `fk_users_has_order_order1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_users_has_order_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_order_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `bubbleshop`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_order_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES `bubbleshop`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
-- rollback DROP TABLE `user_order` CASCADE;

-- changeset sokolin:5
CREATE TABLE IF NOT EXISTS bubbleshop.storehouse
(
    `id`             INT            NOT NULL,
    `material_count` INT            NULL,
    `material_name`  VARCHAR(45)    NULL,
    `balance`        DECIMAL(10, 2) NULL,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `storehouse` CASCADE;




