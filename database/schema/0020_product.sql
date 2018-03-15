-- Create table product.
CREATE TABLE product
(
  id serial NOT NULL,
  "name" character varying(64) NOT NULL,
  group_id int NOT NULL,
  price decimal(9, 0),
  CONSTRAINT "PK_PRODUCT" PRIMARY KEY (id),
  CONSTRAINT "UQ_PRODUCT_NAME" UNIQUE (name, group_id),
  CONSTRAINT "CK_PRODUCT_NAME" CHECK (char_length(btrim(name)) > 0),
  CONSTRAINT "FK_PRODUCT_GROUP" FOREIGN KEY (group_id)
      REFERENCES product_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "CK_PRODUCT_PRICE" CHECK (price > 0)
);
ALTER TABLE product OWNER TO seller;
COMMENT ON TABLE product IS 'Товар';
COMMENT ON COLUMN product.id IS 'ID';
COMMENT ON COLUMN product.name IS 'Наименование';
COMMENT ON COLUMN product.group_id IS 'ID группы товаров';
COMMENT ON COLUMN product.price IS 'Цена за единицу';

