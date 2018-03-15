-- Create table sale_item.
CREATE TABLE sale_item
(
  id serial NOT NULL,
  sale_id int NOT NULL,
  product_id int NOT NULL,
  quantity smallint NOT NULL,
  price decimal(9, 0) NOT NULL,
  CONSTRAINT "PK_SALE_ITEM" PRIMARY KEY (id),
  CONSTRAINT "UQ_SALE_ITEM" UNIQUE (sale_id, product_id, price),
  CONSTRAINT "CK_SALE_ITEM_QUANTITY" CHECK (quantity > 0),
  CONSTRAINT "CK_SALE_ITEM_PRICE" CHECK (price > 0),
  CONSTRAINT "FK_SALE_ITEM_SALE" FOREIGN KEY (sale_id)
      REFERENCES sale (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT "FK_INVOICE_ITEM_PRODUCT" FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE sale OWNER TO seller;
COMMENT ON TABLE sale_item IS 'Позиция реализации';
COMMENT ON COLUMN sale_item.id IS 'ID';
COMMENT ON COLUMN sale_item.sale_id IS 'ID реализации';
COMMENT ON COLUMN sale_item.product_id IS 'ID товара';
COMMENT ON COLUMN sale_item.quantity IS 'Количество';
COMMENT ON COLUMN sale_item.price IS 'Цена реализованной единицы';
