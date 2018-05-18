/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Daniel
 * Created: 17/05/2018
 */
CREATE DATABASE lp
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS egg (
    login varchar(20) not null PRIMARY KEY,
    senha varchar(20) not null ,
    hunger integer not null ,
    healt integer not null ,
    happy integer not null ,
    state integer not null ,
    lastTime timestamp not null ,
    gameTouch integer not null
);