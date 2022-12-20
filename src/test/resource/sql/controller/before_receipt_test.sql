TRUNCATE TABLE discount_card;
TRUNCATE TABLE settings;
TRUNCATE TABLE product;
TRUNCATE TABLE receipt_header CASCADE;
TRUNCATE TABLE receipt_item CASCADE;
TRUNCATE TABLE receipt_footer CASCADE;
TRUNCATE TABLE receipt CASCADE;

ALTER SEQUENCE discount_card_id_seq RESTART WITH 1;
ALTER SEQUENCE settings_id_seq RESTART WITH 1;
ALTER SEQUENCE product_id_seq RESTART WITH 1;
ALTER SEQUENCE receipt_id_seq RESTART WITH 1;
ALTER SEQUENCE receipt_header_id_seq RESTART WITH 1;
ALTER SEQUENCE receipt_item_id_seq RESTART WITH 1;
ALTER SEQUENCE receipt_footer_id_seq RESTART WITH 1;

INSERT INTO settings (id, skey, value)
VALUES (1, 'title', 'receipt title'),
       (2, 'store', 'store name'),
       (3, 'address', 'store address'),
       (4, 'phone', 'store phone');

INSERT INTO discount_card (id, percent)
VALUES (1, 5),
       (2, 10),
       (3, 15);

INSERT INTO product (id, description, price, action)
VALUES (1, 'goods_1', 1.00, true),
       (2, 'goods_2', 1.01, true),
       (3, 'goods_3', 1.02, true),
       (4, 'goods_4', 1.03, true),
       (5, 'goods_5', 1.04, true),
       (6, 'goods_6', 1.05, true),
       (7, 'goods_7', 1.06, true),
       (8, 'goods_8', 1.07, true),
       (9, 'goods_9', 1.08, true),
       (10, 'goods_10', 1.09, false),
       (11, 'goods_11', 1.10, false),
       (12, 'goods_12', 1.11, false),
       (13, 'goods_13', 1.12, false),
       (14, 'goods_14', 1.13, false),
       (15, 'goods_15', 1.14, false),
       (16, 'goods_16', 1.15, false),
       (17, 'goods_17', 1.16, false),
       (18, 'goods_18', 1.17, false),
       (19, 'goods_19', 1.18, false),
       (20, 'goods_20', 1.19, false),
       (21, 'goods_21', 1.20, false),
       (22, 'goods_22', 1.21, false),
       (23, 'goods_23', 1.22, false),
       (24, 'goods_24', 1.23, false),
       (25, 'goods_25', 1.24, false),
       (26, 'goods_26', 1.25, false),
       (27, 'goods_27', 1.26, false),
       (28, 'goods_28', 1.27, false),
       (29, 'goods_29', 1.28, false),
       (30, 'goods_30', 1.29, false),
       (31, 'goods_31', 1.30, false),
       (32, 'goods_32', 1.31, false),
       (33, 'goods_33', 1.32, false),
       (34, 'goods_34', 1.33, false),
       (35, 'goods_35', 1.34, false),
       (36, 'goods_36', 1.35, false),
       (37, 'goods_37', 1.36, false),
       (38, 'goods_38', 1.37, false),
       (39, 'goods_39', 1.38, false),
       (40, 'goods_40', 1.39, false),
       (41, 'goods_41', 1.40, false),
       (42, 'goods_42', 1.41, false),
       (43, 'goods_43', 1.42, false),
       (44, 'goods_44', 1.43, false),
       (45, 'goods_45', 1.44, false),
       (46, 'goods_46', 1.45, false),
       (47, 'goods_47', 1.46, false),
       (48, 'goods_48', 1.47, false),
       (49, 'goods_49', 1.48, false),
       (50, 'goods_50', 1.49, false);

INSERT INTO receipt_header (id, date_time)
VALUES (1, NOW()),
       (2, NOW()),
       (3, NOW()),
       (4, NOW()),
       (5, NOW()),
       (6, NOW()),
       (7, NOW()),
       (8, NOW()),
       (9, NOW()),
       (10, NOW()),
       (11, NOW()),
       (12, NOW()),
       (13, NOW()),
       (14, NOW()),
       (15, NOW()),
       (16, NOW()),
       (17, NOW()),
       (18, NOW()),
       (19, NOW()),
       (20, NOW()),
       (21, NOW()),
       (22, NOW()),
       (23, NOW()),
       (24, NOW()),
       (25, NOW()),
       (26, NOW()),
       (27, NOW()),
       (28, NOW()),
       (29, NOW()),
       (30, NOW());

INSERT INTO receipt_footer
VALUES (1, 0, 1.00, 1.00),
       (2, 0, 1.00, 1.00),
       (3, 0, 1.00, 1.00),
       (4, 0, 1.00, 1.00),
       (5, 0, 1.00, 1.00),
       (6, 0, 1.00, 1.00),
       (7, 0, 1.00, 1.00),
       (8, 0, 1.00, 1.00),
       (9, 0, 1.00, 1.00),
       (10, 0, 1.00, 1.00),
       (11, 0, 1.00, 1.00),
       (12, 0, 1.00, 1.00),
       (13, 0, 1.00, 1.00),
       (14, 0, 1.00, 1.00),
       (15, 0, 1.00, 1.00),
       (16, 0, 1.00, 1.00),
       (17, 0, 1.00, 1.00),
       (18, 0, 1.00, 1.00),
       (19, 0, 1.00, 1.00),
       (20, 0, 1.00, 1.00),
       (21, 0, 1.00, 1.00),
       (22, 0, 1.00, 1.00),
       (23, 0, 1.00, 1.00),
       (24, 0, 1.00, 1.00),
       (25, 0, 1.00, 1.00),
       (26, 0, 1.00, 1.00),
       (27, 0, 1.00, 1.00),
       (28, 0, 1.00, 1.00),
       (29, 0, 1.00, 1.00),
       (30, 0, 1.00, 1.00);

INSERT INTO receipt (id, header_id, footer_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5),
       (6, 6, 6),
       (7, 7, 7),
       (8, 8, 8),
       (9, 9, 9),
       (10, 10, 10),
       (11, 11, 11),
       (12, 12, 12),
       (13, 13, 13),
       (14, 14, 14),
       (15, 15, 15),
       (16, 16, 16),
       (17, 17, 17),
       (18, 18, 18),
       (19, 19, 19),
       (20, 20, 20),
       (21, 21, 21),
       (22, 22, 22),
       (23, 23, 23),
       (24, 24, 24),
       (25, 25, 25),
       (26, 26, 26),
       (27, 27, 27),
       (28, 28, 28),
       (29, 29, 29),
       (30, 30, 30);

INSERT INTO receipt_item (id, receipt_id, description, discount, price, quantity, total, action)
VALUES (1, 1, 'goods_1', 0, 1.00, 1, 1.00, true),
       (2, 2, 'goods_1', 0, 1.00, 1, 1.00, true),
       (3, 3, 'goods_1', 0, 1.00, 1, 1.00, true),
       (4, 4, 'goods_1', 0, 1.00, 1, 1.00, true),
       (5, 5, 'goods_1', 0, 1.00, 1, 1.00, true),
       (6, 6, 'goods_1', 0, 1.00, 1, 1.00, true),
       (7, 7, 'goods_1', 0, 1.00, 1, 1.00, true),
       (8, 8, 'goods_1', 0, 1.00, 1, 1.00, true),
       (9, 9, 'goods_1', 0, 1.00, 1, 1.00, true),
       (10, 10, 'goods_1', 0, 1.00, 1, 1.00, true),
       (11, 11, 'goods_1', 0, 1.00, 1, 1.00, true),
       (12, 12, 'goods_1', 0, 1.00, 1, 1.00, true),
       (13, 13, 'goods_1', 0, 1.00, 1, 1.00, true),
       (14, 14, 'goods_1', 0, 1.00, 1, 1.00, true),
       (15, 15, 'goods_1', 0, 1.00, 1, 1.00, true),
       (16, 16, 'goods_1', 0, 1.00, 1, 1.00, true),
       (17, 17, 'goods_1', 0, 1.00, 1, 1.00, true),
       (18, 18, 'goods_1', 0, 1.00, 1, 1.00, true),
       (19, 19, 'goods_1', 0, 1.00, 1, 1.00, true),
       (20, 20, 'goods_1', 0, 1.00, 1, 1.00, true),
       (21, 21, 'goods_1', 0, 1.00, 1, 1.00, true),
       (22, 22, 'goods_1', 0, 1.00, 1, 1.00, true),
       (23, 23, 'goods_1', 0, 1.00, 1, 1.00, true),
       (24, 24, 'goods_1', 0, 1.00, 1, 1.00, true),
       (25, 25, 'goods_1', 0, 1.00, 1, 1.00, true),
       (26, 26, 'goods_1', 0, 1.00, 1, 1.00, true),
       (27, 27, 'goods_1', 0, 1.00, 1, 1.00, true),
       (28, 28, 'goods_1', 0, 1.00, 1, 1.00, true),
       (29, 29, 'goods_1', 0, 1.00, 1, 1.00, true),
       (30, 30, 'goods_1', 0, 1.00, 1, 1.00, true);

ALTER SEQUENCE discount_card_id_seq RESTART WITH 4;
ALTER SEQUENCE settings_id_seq RESTART WITH 5;
ALTER SEQUENCE product_id_seq RESTART WITH 31;
ALTER SEQUENCE receipt_id_seq RESTART WITH 31;
ALTER SEQUENCE receipt_header_id_seq RESTART WITH 31;
ALTER SEQUENCE receipt_item_id_seq RESTART WITH 31;
ALTER SEQUENCE receipt_footer_id_seq RESTART WITH 31;

