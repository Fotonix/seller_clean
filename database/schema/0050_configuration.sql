-- Create table configuration.
CREATE TABLE configuration
(
  "key" character varying(24) NOT NULL,
  type_id int NOT NULL,
  "value" character varying(64) NOT NULL,
  description character varying(128),
  CONSTRAINT "PK_CONFIGURATION" PRIMARY KEY ("key"),
  CONSTRAINT "CK_CONFIGURATION_KEY" CHECK (char_length(btrim("key")) > 0),
  CONSTRAINT "CK_CONFIGURATION_VALUE" CHECK (char_length(btrim("value")) > 0),
  CONSTRAINT "CK_CONFIGURATION_DESCRIPTION" CHECK (char_length(btrim(description)) > 0),
  CONSTRAINT "FK_CONFIGURATION_TYPE" FOREIGN KEY (type_id)
      REFERENCES configuration_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE configuration OWNER TO seller;
COMMENT ON TABLE configuration IS 'Настройки конфигурации';
COMMENT ON COLUMN configuration."key" IS 'Ключ';
COMMENT ON COLUMN configuration.type_id IS 'ID типа настройки';
COMMENT ON COLUMN configuration."value" IS 'Значение';
COMMENT ON COLUMN configuration.description IS 'Описание';
