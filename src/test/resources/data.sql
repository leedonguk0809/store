INSERT INTO user (user_id,name, email, password, tel_number, zipcode, main_address, detail_address, user_status)
VALUES (1,'김도도', 'kimdodo@naver.com' ,'1234', '123456789', '12345', '123 Main St', 'Apt 101', 'BRONZE');

INSERT INTO user_roles (user_id,role)
VALUES (1,'COMMON');

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (1,'Sample Item1', 10000,'This is a sample item', NULL);

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (2,'Sample Item2', 20000,'This is a sample item', NULL);


INSERT INTO item (item_id,name, price, info, item_image)
VALUES (3,'Sample Item3', 30000,'This is a sample item', NULL);

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (4,'Sample Item4', 30000,'This is a sample item', NULL);

INSERT INTO carts (cart_id,user_id,total_price)
VALUES (1, 1,0);

INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (1,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (2,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (3,1,1);



-- INSERT INTO orders (user_id, total_price, order_enroll, zipcode, main_address, detail_address, tid, order_status)
-- VALUES (1, 60000,'2024-06-21 14:30:00', '12345', '123 Main St', 'Apt 101', NULL, 'READY');
--
-- INSERT INTO order_item (order_id, item_id,count)
-- VALUES (1, 1,1), (1, 2,1), (1, 3,1);

--