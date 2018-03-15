-- Create table product_group.
CREATE TABLE product_group
(
  id serial NOT NULL,
  "name" character varying(64) NOT NULL,
  CONSTRAINT "PK_PRODUCT_GROUP" PRIMARY KEY (id),
  CONSTRAINT "UQ_PRODUCT_GROUP_NAME" UNIQUE (name),
  CONSTRAINT "CK_PRODUCT_GROUP_NAME" CHECK (char_length(btrim(name)) > 0)
);
ALTER TABLE product_group OWNER TO seller;
COMMENT ON TABLE product_group IS 'Группа товаров';
COMMENT ON COLUMN product_group.id IS 'ID';
COMMENT ON COLUMN product_group.name IS 'Наименование';
