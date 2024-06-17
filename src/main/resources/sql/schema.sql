CREATE TABLE cart
(
    cart_id     BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL   ,
    total_price BIGINT NOT NULL,
    PRIMARY KEY (cart_id)
) ;

CREATE TABLE cart_item
(
    cart_item_id BIGINT NOT NULL AUTO_INCREMENT  ,
    item_id      BIGINT NOT NULL  ,
    cart_id      BIGINT NOT NULL   ,
    PRIMARY KEY (cart_item_id)
)  ;

CREATE TABLE item
(
    item_id BIGINT      NOT NULL AUTO_INCREMENT  ,
    name    VARCHAR(50) NOT NULL  ,
    price   BIGINT      NOT NULL  ,
    stock   BIGINT      NOT NULL  ,
    info    TEXT        NULL      ,
    PRIMARY KEY (item_id)
)  ;

CREATE TABLE orders
(
    order_id       BIGINT      NOT NULL AUTO_INCREMENT  ,
    user_id        BIGINT      NOT NULL  ,
    total_price    BIGINT      NOT NULL  ,
    order_enroll   DATE        NULL      ,
    zipcode        VARCHAR(20) NOT NULL  ,
    main_address   VARCHAR(50) NOT NULL  ,
    detail_address VARCHAR(50) NOT NULL  ,
    order_status   VARCHAR(50) NOT NULL  ,
    PRIMARY KEY (order_id)
)  ;

CREATE TABLE order_item
(
    order_item_id BIGINT NOT NULL AUTO_INCREMENT  ,
    order_id      BIGINT NOT NULL  ,
    item_id       BIGINT NOT NULL   ,
    PRIMARY KEY (order_item_id)
)  ;

CREATE TABLE payment
(
    payment_id    BIGINT      NOT NULL AUTO_INCREMENT  ,
    order_id      BIGINT      NOT NULL  ,
    payment_price BIGINT      NOT NULL  ,
    payment_date  DATE        NOT NULL  ,
    bank          VARCHAR(50) NULL      ,
    PRIMARY KEY (payment_id)
)  ;

CREATE TABLE user
(
    user_id        BIGINT      NOT NULL AUTO_INCREMENT  ,
    name           VARCHAR(30) NOT NULL  ,
    email          VARCHAR(30) NOT NULL  ,
    password       VARCHAR(50) NOT NULL  ,
    tel_number     VARCHAR(20) NOT NULL  ,
    zipcode        VARCHAR(50) NULL      ,
    main_address   VARCHAR(50) NULL      ,
    detail_address VARCHAR(50) NULL      ,
    PRIMARY KEY (user_id)
) ;

ALTER TABLE order_item
    ADD CONSTRAINT FK_order_TO_order_item
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id);

ALTER TABLE order_item
    ADD CONSTRAINT FK_item_TO_order_item
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_item_TO_cart_item
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_cart_TO_cart_item
        FOREIGN KEY (cart_id)
            REFERENCES cart (cart_id);

ALTER TABLE cart
    ADD CONSTRAINT FK_user_TO_cart
        FOREIGN KEY (user_id)
            REFERENCES user (user_id);

ALTER TABLE orders
    ADD CONSTRAINT FK_user_TO_order
        FOREIGN KEY (user_id)
            REFERENCES user (user_id);

ALTER TABLE payment
    ADD CONSTRAINT FK_order_TO_payment
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id);


