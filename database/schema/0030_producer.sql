-- Create table producer.
CREATE TABLE producer
(
  id serial NOT NULL,
  "name" character varying(64) NOT NULL,
  CONSTRAINT "PK_PRODUCER" PRIMARY KEY (id),
  CONSTRAINT "UQ_PRODUCER_NAME" UNIQUE (name),
  CONSTRAINT "CK_PRODUCER_NAME" CHECK (char_length(btrim(name)) > 0)
);
ALTER TABLE producer OWNER TO seller;
COMMENT ON TABLE producer IS 'Поставщик';
COMMENT ON COLUMN producer.id IS 'ID';
COMMENT ON COLUMN producer.name IS 'Наименование';
