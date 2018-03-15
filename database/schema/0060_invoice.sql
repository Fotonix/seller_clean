-- Create table invoice.
CREATE TABLE invoice
(
  id serial NOT NULL,
  "date" date NOT NULL,
  series character varying(64),
  producer_id int NOT NULL,
  held boolean NOT NULL DEFAULT FALSE,
  CONSTRAINT "PK_INVOICE" PRIMARY KEY (id),
  CONSTRAINT "CK_INVOICE_DATE" CHECK (date >= '2013-01-01' and date <= now()),
  CONSTRAINT "UQ_INVOICE_SERIES" UNIQUE (series),
  CONSTRAINT "CK_INVOICE_SERIES" CHECK (char_length(btrim(series)) > 0),
  CONSTRAINT "FK_INVOICE_PRODUCER" FOREIGN KEY (producer_id)
      REFERENCES producer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE invoice OWNER TO seller;
COMMENT ON TABLE invoice IS 'Накладная';
COMMENT ON COLUMN invoice.id IS 'ID';
COMMENT ON COLUMN invoice."date" IS 'Дата накладной';
COMMENT ON COLUMN invoice.series IS 'Серия накладной';
COMMENT ON COLUMN invoice.producer_id IS 'ID поставщика';
COMMENT ON COLUMN invoice.held IS 'Признак проведения';
