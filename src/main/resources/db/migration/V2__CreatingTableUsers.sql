CREATE TABLE usuarios (
    id_usuarios char(36) NOT NULL,
    nm_login varchar(100) NOT NULL UNIQUE,
    ds_senha varchar(100) NOT NULL,
    ds_funcao char(3) NOT NULL,
    CONSTRAINT usuarios_pk PRIMARY KEY (id_usuarios)
);