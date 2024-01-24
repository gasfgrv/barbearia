CREATE TABLE
  public.usuario (
    login character varying (255) NOT NULL,
    senha character varying (255) NOT NULL,
    perfil_id integer NOT NULL
  );

ALTER TABLE
  public.usuario
ADD
  CONSTRAINT usuario_pkey PRIMARY KEY (login);

ALTER TABLE
    public.usuario
  ADD
    CONSTRAINT usuario_fkey_perfil FOREIGN KEY (perfil_id) REFERENCES public.perfil(id);