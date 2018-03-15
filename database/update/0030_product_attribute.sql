-- Create table product_attrubute.
CREATE TABLE product_attribute
(
  product_id int NOT NULL,
  attribute_item_id int NOT NULL,
  CONSTRAINT "PK_PRODUCT_ATTRIBUTE" PRIMARY KEY (product_id, attribute_item_id),
  CONSTRAINT "UQ_PRODUCT_ATTRIBUTE" UNIQUE (product_id, attribute_item_id),
  CONSTRAINT "FK_PRODUCT_ATTRIBUTE_PRODUCT" FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT "FK_PRODUCT_ATTRIBUTE_GROUP" FOREIGN KEY (attribute_item_id)
      REFERENCES attribute_item (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);
ALTER TABLE product_attribute OWNER TO seller;
COMMENT ON TABLE product_attribute IS 'Связь товара с позицией признака группы товаров';
COMMENT ON COLUMN product_attribute.product_id IS 'ID группы товаров';
COMMENT ON COLUMN product_attribute.attribute_item_id IS 'ID позиции признака группы товаров';
