INSERT INTO user (user_id,name, email, password, tel_number, zipcode, main_address, detail_address, user_status)
VALUES (10,'김도도', 'kimdodo@naver.com' ,'1234', '123456789', '12345', '123 Main St', 'Apt 101', 'BRONZE');

INSERT INTO user_roles (user_id,role)
VALUES (10,'COMMON');