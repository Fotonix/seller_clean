-- Create table sale.
CREATE TABLE sale
(
  id serial NOT NULL,
  "date" date NOT NULL,
  held boolean NOT NULL DEFAULT FALSE,
  CONSTRAINT "PK_SALE" PRIMARY KEY (id),
  CONSTRAINT "UQ_SALE_DATE" UNIQUE (date)
);
ALTER TABLE sale OWNER TO seller;
COMMENT ON TABLE sale IS 'Реализация';
COMMENT ON COLUMN sale.id IS 'ID';
COMMENT ON COLUMN sale."date" IS 'Дата';
COMMENT ON COLUMN sale.held IS 'Признак проведения';
