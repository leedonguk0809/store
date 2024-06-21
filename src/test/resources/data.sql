INSERT INTO user (name, email, password, tel_number, zipcode, main_address, detail_address, user_status)
VALUES ('김도도', 'kimdodo@naver.com' ,'1234', '123456789', '12345', '123 Main St', 'Apt 101', 'BRONZE');

INSERT INTO user_roles (user_id,role)
VALUES (1,'COMMON');

INSERT INTO item (name, price, info, item_image)
VALUES ('Sample Item1', 10000,'This is a sample item', NULL);

INSERT INTO item (name, price, info, item_image)
VALUES ('Sample Item2', 20000,'This is a sample item', NULL);


INSERT INTO item (name, price, info, item_image)
VALUES ('Sample Item3', 30000,'This is a sample item', NULL);

INSERT INTO item (name, price, info, item_image)
VALUES ('Sample Item4', 30000,'This is a sample item', NULL);

INSERT INTO carts (user_id,total_price)
VALUES (1,0);

INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (1,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (2,,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (3,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (4,1,1);


-- INSERT INTO orders (user_id, total_price, order_enroll, zipcode, main_address, detail_address, tid, order_status)
-- VALUES (1, 60000,'2024-06-21 14:30:00', '12345', '123 Main St', 'Apt 101', NULL, 'READY');
--
-- INSERT INTO order_item (order_id, item_id,count)
-- VALUES (1, 1,1), (1, 2,1), (1, 3,1);

--