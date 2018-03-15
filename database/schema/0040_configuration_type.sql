-- Create table configuration_type.
CREATE TABLE configuration_type
(
  id int NOT NULL,
  "name" character varying(16) NOT NULL,
  CONSTRAINT "PK_CONFIGURATION_TYPE" PRIMARY KEY (id),
  CONSTRAINT "UQ_CONFIGURATION_TYPE_NAME" UNIQUE (name),
  CONSTRAINT "CK_CONFIGURATION_TYPE_NAME" CHECK (char_length(btrim(name)) > 0)
);
ALTER TABLE configuration_type OWNER TO seller;
COMMENT ON TABLE configuration_type IS 'Тип настроек конфигурации';
COMMENT ON COLUMN configuration_type.id IS 'ID';
COMMENT ON COLUMN configuration_type.name IS 'Наименование';
