-- SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE settings;

ALTER SEQUENCE settings_id_seq RESTART WITH 1;

INSERT INTO settings (id, skey, value)
VALUES (1, 'title', 'receipt title'),
       (2, 'store', 'store name'),
       (3, 'address', 'store address'),
       (4, 'phone', 'store phone');

ALTER SEQUENCE settings_id_seq RESTART WITH 5;