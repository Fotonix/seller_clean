-- Create table invoice_item.
CREATE TABLE invoice_item
(
  id serial NOT NULL,
  invoice_id int NOT NULL,
  product_id int NOT NULL,
  quantity smallint NOT NULL,
  cost decimal(9, 0) NOT NULL,
  CONSTRAINT "PK_INVOICE_ITEM" PRIMARY KEY (id),
  CONSTRAINT "UQ_INVOICE_ITEM" UNIQUE (invoice_id, product_id),
  CONSTRAINT "CK_INVOICE_ITEM_QUANTITY" CHECK (quantity > 0),
  CONSTRAINT "CK_INVOICE_ITEM_COST" CHECK (cost > 0),
  CONSTRAINT "FK_INVOICE_ITEM_INVOICE" FOREIGN KEY (invoice_id)
      REFERENCES invoice (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_INVOICE_ITEM_PRODUCT" FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE invoice OWNER TO seller;
COMMENT ON TABLE invoice_item IS 'Позиция накладной';
COMMENT ON COLUMN invoice_item.id IS 'ID';
COMMENT ON COLUMN invoice_item.invoice_id IS 'ID накладной';
COMMENT ON COLUMN invoice_item.product_id IS 'ID товара';
COMMENT ON COLUMN invoice_item.quantity IS 'Количество';
COMMENT ON COLUMN invoice_item.cost IS 'Стоимость единицы';
