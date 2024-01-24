CREATE TABLE
  public.servico (
    id uuid NOT NULL,
    nome character varying(100) NOT NULL,
    preco numeric(12, 2) NOT NULL,
    descricao character varying(255) NULL,
    duracao integer NOT NULL,
    ativo boolean NOT NULL DEFAULT false
  );

ALTER TABLE
  public.servico
ADD
  CONSTRAINT servico_pkey PRIMARY KEY (id);