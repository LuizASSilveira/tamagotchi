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
    ultimoAcesso TIMESTAMP not null,
    felicidade integer not null,
    qtdToques integer not null,
    dono varchar(20) not null,
    lampada boolean not null,
    saude integer not null,
    vida boolean not null, -- CAMPO SE ELE ESTA VIVO OU MORTO
    fome integer not null,
    status STATUS,
    FOREIGN KEY (dono) REFERENCES usuario(usuario)
);