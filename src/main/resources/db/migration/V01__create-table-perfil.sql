CREATE TABLE
  public.perfil (
    id integer NOT NULL,
    nome character varying (8) NOT NULL
  );

ALTER TABLE
  public.perfil
ADD
  CONSTRAINT perfil_pkey PRIMARY KEY (id);
