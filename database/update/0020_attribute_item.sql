-- Create table attrubute_item.
CREATE TABLE attribute_item
(
  id serial NOT NULL,
  "name" character varying(64) NOT NULL,
  attribute_id int NOT NULL,
  CONSTRAINT "PK_ATTRIBUTE_ITEM" PRIMARY KEY (id),
  CONSTRAINT "UQ_ATTRIBUTE_ITEM_NAME" UNIQUE (name, attribute_id),
  CONSTRAINT "CK_ATTRIBUTE_ITEM_NAME" CHECK (char_length(btrim(name)) > 0),
  CONSTRAINT "FK_ATTRIBUTE_ITEM_GROUP_ATTRIBUTE" FOREIGN KEY (attribute_id)
      REFERENCES group_attribute (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE attribute_item OWNER TO seller;
COMMENT ON TABLE attribute_item IS 'Позиция признака группы товаров';
COMMENT ON COLUMN attribute_item.id IS 'ID';
COMMENT ON COLUMN attribute_item.name IS 'Наименование';
COMMENT ON COLUMN attribute_item.attribute_id IS 'ID признака группы товаров';
