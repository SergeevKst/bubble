-- liquibase formatted sql
-- changeset AddOrder:1
INSERT INTO bubbleshop.`orders`(id) value (1);

-- changeSet AddOrderDetails:1
insert into bubbleshop.order_details(apartment_number, bubble_count, cost, create_date, delivery_date, house_number, id, city, status, street)
values (1,3040, 500, null, null,1,1,'gom',null,'street1');

-- changeset addOrder:2
insert into bubbleshop.`orders`(id)value (2);

-- changeset addOrderDetails:2
insert into bubbleshop.order_details(apartment_number, bubble_count, cost, create_date, delivery_date, house_number, id, city, status, street)
values (2,300, 5600, null, null,2,2,'mog',null,'street2');

-- changeset addOrder:3
insert into bubbleshop.`orders`(id)value (3);

-- changeset addOrderDetails:3
insert into bubbleshop.order_details(apartment_number, bubble_count, cost, create_date, delivery_date, house_number, id, city, status, street)
values (3,300, 500, null, null,3,3,'gom',null,'street3');

-- changeset addOrder_User:1
insert into bubbleshop.user_order(user_id, order_id) VALUES (1,1);

-- changeset addOrder_User:2
insert into bubbleshop.user_order(user_id, order_id) VALUES (1,2);

-- changeset addOrder_User:3
insert into bubbleshop.user_order(user_id, order_id) VALUES (2,3);
