-- liquibase formatted sql
CREATE DATABASE IF NOT EXISTS bubbleshop;
-- changeset sokolin:1
CREATE TABLE IF NOT EXISTS bubbleshop.orders
(
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `orders` CASCADE;

-- changeset sokolin:2
CREATE TABLE IF NOT EXISTS bubbleshop.order_details
(
    `apartment_number` INT            NULL DEFAULT NULL,
    `bubble_count`     INT            NULL DEFAULT NULL,
    `cost`             DECIMAL(38, 2) NULL DEFAULT NULL,
    `create_date`      DATE           NULL DEFAULT NULL,
    `delivery_date`    DATE           NULL DEFAULT NULL,
    `house_number`     INT            NULL DEFAULT NULL,
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `city`             VARCHAR(255)   NULL DEFAULT NULL,
    `status`           VARCHAR(255)   NULL DEFAULT NULL,
    `street`           VARCHAR(255)   NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `order_details` CASCADE;

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
CREATE TABLE IF NOT EXISTS bubbleshop.`user_order`
(
    `order_id` INT NOT NULL,
    `user_id`  INT NOT NULL,
    PRIMARY KEY (`order_id`, `user_id`),
    INDEX `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKj86u1x7csa8yd68ql2y1ibrou`
        FOREIGN KEY (`user_id`)
            REFERENCES bubbleshop.`user` (`id`),
    CONSTRAINT `FKrlglekn12wx5o456laekbv32u`
        FOREIGN KEY (`order_id`)
            REFERENCES bubbleshop.`orders` (`id`)
);
-- rollback DROP TABLE `user_order` CASCADE;

-- changeset sokolin:5
CREATE TABLE IF NOT EXISTS bubbleshop.storehouse
(
    `id`             INT            NOT NULL AUTO_INCREMENT,
    `material_count` INT            NULL,
    `material_name`  VARCHAR(45)    NULL,
    `balance`        DECIMAL(10, 2) NULL,
    PRIMARY KEY (`id`)
);
-- rollback DROP TABLE `storehouse` CASCADE;

