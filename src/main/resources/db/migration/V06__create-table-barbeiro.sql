CREATE TABLE
  public.barbeiro (
    id uuid NOT NULL,
    nome_completo character varying(255) NOT NULL,
    cpf character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    sexo character varying(1) NULL,
    data_nascimento date NOT NULL,
    foto_perfil character varying(255) NOT NULL
  );

ALTER TABLE
  public.barbeiro
ADD
  CONSTRAINT barbeiro_pkey PRIMARY KEY (id);

ALTER TABLE
    public.barbeiro
  ADD
    CONSTRAINT barbeiro_fkey_login FOREIGN KEY (login) REFERENCES public.usuario(login);