-- tables
-- Table: Evento
CREATE TABLE evento (
                        id_evento char(36) NOT NULL,
                        nm_evento varchar(100) NOT NULL,
                        dt_evento date NOT NULL,
                        hr_evento time NOT NULL,
                        ds_local varchar(255) NOT NULL,
                        CONSTRAINT evento_pk PRIMARY KEY (id_evento)
);

-- Table: Venda
CREATE TABLE venda (
                       id_venda char(36) NOT NULL,
                       nm_vendedor varchar(100) NOT NULL,
                       dt_venda datetime NOT NULL,
                       qt_venda int NOT NULL,
                       id_evento char(36) NOT NULL,
                       CONSTRAINT venda_pk PRIMARY KEY (id_venda)
);

-- Table: Ingresso
CREATE TABLE ingresso (
                          id_ingresso char(36) NOT NULL,
                          nm_convidado VARCHAR(100) NOT NULL,
                          nr_cpf char(14) NOT NULL,
                          nr_telefone char(15) NOT NULL,
                          ds_email varchar(100) NULL,
                          ie_utilizado char(1) NOT NULL,
                          id_venda char(36) NOT NULL,
                          CONSTRAINT ingresso_pk PRIMARY KEY (id_ingresso)
);

-- foreign keys
-- Reference: Ingresso_Venda (table: Ingresso)
ALTER TABLE ingresso ADD CONSTRAINT ingresso_venda FOREIGN KEY (id_venda)
    REFERENCES venda (id_venda);

-- Reference: Venda_Evento (table: Venda)
ALTER TABLE venda ADD CONSTRAINT venda_evento FOREIGN KEY (id_evento)
    REFERENCES evento (id_evento);
