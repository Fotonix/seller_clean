-- Create table group_attrubute.
CREATE TABLE group_attribute
(
  id serial NOT NULL,
  "name" character varying(64) NOT NULL,
  group_id int NOT NULL,
  CONSTRAINT "PK_GROUP_ATTRIBUTE" PRIMARY KEY (id),
  CONSTRAINT "UQ_GROUP_ATTRIBUTE_NAME" UNIQUE (name, group_id),
  CONSTRAINT "CK_GROUP_ATTRIBUTE_NAME" CHECK (char_length(btrim(name)) > 0),
  CONSTRAINT "FK_GROUP_ATTRIBUTE_GROUP" FOREIGN KEY (group_id)
      REFERENCES product_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE group_attribute OWNER TO seller;
COMMENT ON TABLE group_attribute IS 'Признак группы товаров';
COMMENT ON COLUMN group_attribute.id IS 'ID';
COMMENT ON COLUMN group_attribute.name IS 'Наименование';
COMMENT ON COLUMN group_attribute.group_id IS 'ID группы товаров';
