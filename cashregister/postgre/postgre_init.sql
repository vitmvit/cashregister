-- Adminer 4.8.1 PostgreSQL 15.1 (Debian 15.1-1.pgdg110+1) dump

-- connect "cash_register";

CREATE SEQUENCE discount_card_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."discount_card"
(
    "id"      bigint DEFAULT nextval('discount_card_id_seq') NOT NULL,
    "percent" integer                                        NOT NULL,
    CONSTRAINT "discount_card_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."product"
(
    "id"          bigint DEFAULT nextval('product_id_seq') NOT NULL,
    "description" character varying(255),
    "price"       numeric(10, 2),
    "action"      boolean,
    CONSTRAINT "product_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE receipt_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."receipt"
(
    "id"        bigint DEFAULT nextval('receipt_id_seq') NOT NULL,
    "header_id" bigint,
    "footer_id" bigint,
    CONSTRAINT "receipt_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE receipt_footer_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."receipt_footer"
(
    "id"        bigint DEFAULT nextval('receipt_footer_id_seq') NOT NULL,
    "total"     numeric(10, 2),
    "discount"  numeric(10, 2),
    "tax_total" numeric(10, 2),
    CONSTRAINT "receipt_footer_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE receipt_header_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."receipt_header"
(
    "id"        bigint DEFAULT nextval('receipt_header_id_seq') NOT NULL,
    "date_time" timestamp(6),
    CONSTRAINT "receipt_header_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE receipt_item_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."receipt_item"
(
    "id"          bigint DEFAULT nextval('receipt_item_id_seq') NOT NULL,
    "receipt_id"  bigint,
    "description" character varying(255),
    "price"       numeric(10, 2),
    "quantity"    integer,
    "total"       numeric(10, 2),
    "discount"    numeric(10, 2),
    "action"      boolean,
    CONSTRAINT "receipt_item_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE settings_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."settings"
(
    "id"    bigint DEFAULT nextval('settings_id_seq') NOT NULL,
    "skey"  character varying(255)                    NOT NULL,
    "value" character varying(255),
    CONSTRAINT "settings_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_eyrdo9je94ajt0n2kls6rmm29" UNIQUE ("skey")
) WITH (oids = false);


ALTER TABLE ONLY "public"."receipt" ADD CONSTRAINT "fk90uescsqxpxecsatlc82x8orh" FOREIGN KEY (header_id) REFERENCES receipt_header(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."receipt" ADD CONSTRAINT "fkbh8x87i0ljhlq4lsvlyjnrnwh" FOREIGN KEY (footer_id) REFERENCES receipt_footer(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."receipt_item" ADD CONSTRAINT "fk_item_to_receipt" FOREIGN KEY (receipt_id) REFERENCES receipt(id) NOT DEFERRABLE;

-- 2022-12-16 22:41:10.277421+00