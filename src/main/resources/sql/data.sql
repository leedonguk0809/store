INSERT INTO item (item_id,name, price, info, item_image)
VALUES (1,'Sample Item1', 10000,'This is a sample item', '/assets/item.png');

INSERT INTO stock (item_id,quantity)
VALUES (1,10);

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (2,'Sample Item2', 20000,'This is a sample item', '/assets/item.png');

INSERT INTO stock (item_id,quantity)
VALUES (2,10);

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (3,'Sample Item3', 30000,'This is a sample item', '/assets/item.png');

INSERT INTO stock (item_id,quantity)
VALUES (3,10);

INSERT INTO item (item_id,name, price, info, item_image)
VALUES (4,'Sample Item4', 30000,'This is a sample item', NULL);

INSERT INTO stock (item_id,quantity)
VALUES (4,10);

INSERT INTO carts (user_id,total_price)
VALUES (1,0);

INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (1,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (2,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (3,1,1);
INSERT INTO cart_item (item_id,cart_id,item_count)
VALUES (4,1,1);


INSERT INTO orders (user_id, total_price, order_enroll, zipcode, main_address, detail_address, tid, order_status)
VALUES (1, 60000,'2024-06-21 14:30:00', '12345', '123 Main St', 'Apt 101', NULL, 'READY');

INSERT INTO order_item (order_id, item_id,item_count)
VALUES (1, 1,1), (1, 2,1), (1, 3,1);
--
-- --
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 1', 10000, '내용1', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 2', 10000, '내용2', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 3', 10000, '내용3', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 4', 10000, '내용4', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 5', 10000, '내용5', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 6', 10000, '내용6', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 7', 10000, '내용7', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 8', 10000, '내용8', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 9', 10000, '내용9', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 10', 10000, '내용10', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 11', 10000, '내용11', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 12', 10000, '내용12', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 13', 10000, '내용13', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 14', 10000, '내용14', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 15', 10000, '내용15', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 16', 10000, '내용16', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 17', 10000, '내용17', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 18', 10000, '내용18', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 19', 1900, '내용19', '/assets/item.png');
-- INSERT INTO item (name, price, info, item_image) VALUES ('상품 20', 10000, '내용20', '/assets/item.png');
--
--
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-1', 100, '내용1', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-2', 200, '내용2', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-3', 300, '내용3', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-4', 400, '내용4', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-5', 500, '내용5', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-6', 600, '내용6', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-7', 700, '내용7', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-8', 800, '내용8', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-9', 900, '내용9', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-10', 1000, '내용10', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-11', 1100, '내용11', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-12', 1200, '내용12', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-13', 1300, '내용13', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-14', 1400, '내용14', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-15', 1500, '내용15', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-16', 1600, '내용16', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-17', 1700, '내용17', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-18', 1800, '내용18', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-19', 1900, '내용19', NULL);
-- INSERT INTO item (name, price, info, item_image) VALUES ('제목-20', 2000, '내용20', NULL);
