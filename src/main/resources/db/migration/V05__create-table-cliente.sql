CREATE TABLE
  public.cliente (
    id uuid NOT NULL,
    nome_completo character varying(255) NOT NULL,
    cpf character varying(255) NOT NULL,
    sexo character varying(1) NULL,
    data_nascimento date NOT NULL,
    foto_perfil character varying(255) NOT NULL,
    login character varying(255) NOT NULL
  );

ALTER TABLE
  public.cliente
ADD
  CONSTRAINT cliente_pkey PRIMARY KEY (id);

ALTER TABLE
    public.cliente
  ADD
    CONSTRAINT cliente_fkey_login FOREIGN KEY (login) REFERENCES public.usuario(login);