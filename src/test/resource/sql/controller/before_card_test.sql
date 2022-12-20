-- SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE discount_card;

ALTER SEQUENCE discount_card_id_seq RESTART WITH 1;

INSERT INTO discount_card (id, percent)
VALUES (1, 5),
       (2, 10),
       (3, 15);

ALTER SEQUENCE discount_card_id_seq RESTART WITH 4;