-- SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE product;

ALTER SEQUENCE product_id_seq RESTART WITH 1;

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

ALTER SEQUENCE product_id_seq RESTART WITH 51;