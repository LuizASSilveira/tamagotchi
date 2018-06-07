    /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Daniel
 * Created: 17/05/2018
 */

psql -U postgres

DROP DATABASE lp;

CREATE DATABASE lp
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

\q
psql -U postgres lp

CREATE TABLE IF NOT EXISTS usuario (
    usuario varchar(20) not null PRIMARY KEY,
    senha varchar(20) not null
);

CREATE TYPE STATUS AS ENUM('NORMAL', 'DOENTE', 'CANSADO', 'SUJO', 'TRISTE', 'DORMINDO', 'MORTO');

CREATE TABLE IF NOT EXISTS pet (
    id SERIAL NOT NULL,
    nome varchar(20) not null,
    ultimoAcesso timestamp not null,
    felicidade integer not null,
    qtdToques integer not null,
    dono varchar(20) not null,
    lampada boolean not null,
    saude integer not null,
    vida boolean not null, -- CAMPO SE ELE ESTA VIVO OU MORTO
    fome integer not null,
    status STATUS default 'NORMAL',
    dataCriacao timestamp not null,
    tipo integer not null,  -- acho q eh o tipo da foto do pet
    timeMorte timestamp,
    FOREIGN KEY (dono) REFERENCES usuario(usuario)
);

insert into pet (nome, ultimoacesso, felicidade, qtdtoques, dono, lampada, saude, vida, fome, status, dataCriacao, tipo) values ('PET2', 1527302714691, 100, 100, 'luiz', true, 100, true, 100, 'NORMAL', 1527302714691, 1);
update pet set felicidade = 50, saude = 50, fome = 50, status = 'NORMAL', ultimoacesso = 1527465573961 where id = 1;