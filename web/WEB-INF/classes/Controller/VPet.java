package Controller;

import Model.DAO;
import java.sql.Timestamp;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class VPet {

    private boolean lampada;
    private int felicidade;
    private int qtdToques;
    private String status;
    private Model.DAO dao;
    private boolean vida;
    private String dono;
    private String nome;
    private int saude;
    private int fome;
    private int id;

    public VPet(boolean lampada, int felicidade, int qtdToques, String status, DAO dao, boolean vida, String dono, String nome, int saude, int fome, int id) {
        this.lampada = lampada;
        this.felicidade = felicidade;
        this.qtdToques = qtdToques;
        this.status = status;
        this.dao = dao;
        this.vida = vida;
        this.dono = dono;
        this.nome = nome;
        this.saude = saude;
        this.fome = fome;
        this.id = id;
    }
    
    public void update(){
        long ultimoAcesso = dao.getLastTime(id);
        long agora = System.currentTimeMillis();
        long deltaTime = agora - ultimoAcesso;

        int saudeR;
        int felicidadeR;
        int fomeR;
        Random gerador = new Random();

        switch(status){
            case "NORMAL":
                saudeR = 1;
                felicidadeR = 4;
                fomeR = 3;

                fome -= (fomeR * (gerador.nextInt((int)(1.2-0.8)) + 0.8) + deltaTime);
                saude -= (saudeR * (gerador.nextInt((int)(1.2-0.8)) + 0.8) + deltaTime);
                felicidade -= (felicidadeR * (gerador.nextInt((int)(1.2-0.8)) + 0.8) + deltaTime);

               
            break;

            case "DOENTE":
            break;
            case "CANSADO":
            break;
            case "SUJO":
            break;
            case "TRISTE":
            break;
            case "DORMINDO":
            break;
            case "MORTO":
            break;
        }

        if(felicidade < 25){
            status = "TRISTE";
        } else if(saude < 25){
            status = "DOENTE";
        } else if(fome < 35){
            status = "CANSADO";
        } else if(felicidade <= 0 || saude <= 0 || fome <= 0){
            status = "MORTO";
        }
        
        dao.update(fome, saude, felicidade, status, agora, id);
    }

   
}
