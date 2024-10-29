-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-10-28 18:38:10.082

-- tables
-- Table: Evento
CREATE TABLE Evento (
                        id_evento char(36)  NOT NULL,
                        nm_evento varchar(100)  NOT NULL,
                        dt_evento date  NOT NULL,
                        hr_evento time  NOT NULL,
                        ds_local varchar(255)  NOT NULL,
                        CONSTRAINT Evento_pk PRIMARY KEY (id_evento)
);

-- Table: Ingresso
CREATE TABLE Ingresso (
                          id_ingresso char(36)  NOT NULL,
                          nm_convidado int  NOT NULL,
                          nr_cpf char(14)  NOT NULL,
                          nr_telefone char(15)  NOT NULL,
                          ds_email varchar(100)  NULL,
                          ie_utilizado char(1)  NOT NULL,
                          id_venda char(36)  NOT NULL,
                          CONSTRAINT Ingresso_pk PRIMARY KEY (id_ingresso)
);

-- Table: Venda
CREATE TABLE Venda (
                       id_venda char(36)  NOT NULL,
                       nm_vendedor varchar(100)  NOT NULL,
                       dt_venda datetime  NOT NULL,
                       qt_venda int  NOT NULL,
                       id_evento char(36)  NOT NULL,
                       CONSTRAINT Venda_pk PRIMARY KEY (id_venda)
);

-- foreign keys
-- Reference: Ingresso_Venda (table: Ingresso)
ALTER TABLE Ingresso ADD CONSTRAINT Ingresso_Venda FOREIGN KEY Ingresso_Venda (id_venda)
    REFERENCES Venda (id_venda);

-- Reference: Venda_Evento (table: Venda)
ALTER TABLE Venda ADD CONSTRAINT Venda_Evento FOREIGN KEY Venda_Evento (id_evento)
    REFERENCES Evento (id_evento);

-- End of file.

