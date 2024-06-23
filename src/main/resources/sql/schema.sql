CREATE TABLE IF NOT EXISTS carts
(
    cart_id     BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL   ,
    total_price INT NOT NULL DEFAULT 0,
    PRIMARY KEY (cart_id)
    ) ;

CREATE TABLE  IF NOT EXISTS cart_item
(
    cart_item_id     BIGINT NOT NULL AUTO_INCREMENT,
    item_id      BIGINT NOT NULL ,
    cart_id      BIGINT NOT NULL ,
    item_count   INT    NOT NULL,
    PRIMARY KEY (item_id,cart_id)
    )  ;

CREATE TABLE IF NOT EXISTS item
(
    item_id BIGINT      NOT NULL AUTO_INCREMENT  ,
    name    VARCHAR(50) NOT NULL  ,
    price   INT      NOT NULL  ,
    info    TEXT        NULL      ,
    item_image VARCHAR(300) NULL,
    PRIMARY KEY (item_id)
    )  ;

CREATE TABLE IF NOT EXISTS stock
(
    stock_id BIGINT     NOT NULL AUTO_INCREMENT  ,
    item_id  BIGINT     NOT NULL  ,
    quantity   INT      NOT NULL  ,
    PRIMARY KEY (stock_id)
    )  ;

CREATE TABLE IF NOT EXISTS orders
(
    order_id       BIGINT      NOT NULL AUTO_INCREMENT  ,
    user_id        BIGINT      NOT NULL  ,
    total_price    BIGINT      NOT NULL  ,
    order_enroll   DATETIME        NULL      ,
    zipcode        VARCHAR(20) NOT NULL  ,
    main_address   VARCHAR(50) NOT NULL  ,
    detail_address VARCHAR(50) NOT NULL  ,
    tid             VARCHAR(255)  NULL,
    order_status   VARCHAR(50) NOT NULL  ,
    PRIMARY KEY (order_id)
    )  ;

CREATE TABLE IF NOT EXISTS order_item
(
    order_item_id BIGINT NOT NULL AUTO_INCREMENT  ,
    order_id      BIGINT NOT NULL  ,
    item_id       BIGINT NOT NULL   ,
    item_count INT NOT NULL,
    PRIMARY KEY (order_item_id)
    )  ;

CREATE TABLE IF NOT EXISTS payment
(
    payment_id     BIGINT      NOT NULL AUTO_INCREMENT COMMENT '결제 ID',
    order_id       BIGINT      NOT NULL COMMENT '주문 ID',
    user_id        BIGINT      NULL     COMMENT '사용자 ID',
    item_name      VARCHAR(255) NOT NULL,
    tid            VARCHAR(50)        NULL     COMMENT '결제 준비 UUID',
    aid            VARCHAR(50)        NULL     COMMENT '결제 UUID',
    total_amount   INT      NULL     COMMENT '총결제 금액',
    quantity       INT      NULL     COMMENT '총 구매 fid',
    vat            INT         NULL     COMMENT '세금액',
    payment_method VARCHAR(50) NULL     COMMENT '결제 방법',
    card_info      VARCHAR(50) NULL     COMMENT '결제 카드',
    created_at     DATETIME        NULL     COMMENT '결제 신청 시간',
    approved_at    DATETIME        NULL     COMMENT '결제 완료 시간',
    PRIMARY KEY (payment_id)
    ) ;

CREATE TABLE IF NOT EXISTS user
(
    user_id        BIGINT      NOT NULL AUTO_INCREMENT  ,
    name           VARCHAR(30) NOT NULL  ,
    email          VARCHAR(30) NOT NULL  ,
    password       TEXT NOT NULL  ,
    tel_number     VARCHAR(20) NOT NULL  ,
    zipcode        VARCHAR(50) NULL      ,
    main_address   VARCHAR(50) NULL      ,
    detail_address VARCHAR(50) NULL      ,
    user_status VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
    ) ;

CREATE TABLE IF NOT EXISTS user_roles (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          user_id BIGINT NOT NULL,
                                          role VARCHAR(50) NOT NULL
    );

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
            REFERENCES carts (cart_id);

ALTER TABLE carts
    ADD CONSTRAINT FK_user_TO_carts
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

ALTER TABLE stock
    ADD CONSTRAINT FK_item_TO_stock
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);


ALTER TABLE user_roles
    ADD CONSTRAINT FK_user_TO_user_roles
        FOREIGN KEY (user_id) REFERENCES user(user_id)
            ON DELETE CASCADE;