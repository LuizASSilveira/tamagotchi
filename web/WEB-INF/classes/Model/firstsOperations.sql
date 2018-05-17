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

create table egg (login varchar(20) primary key, senha varchar(20), hunger integer, healt integer, happy integer, state integer, lastTime timestamp, gameTouch integer)