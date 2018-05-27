package Controller;

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

                if(felicidade < 25){
                    status = "TRISTE";
                } else if(saude < 25){
                    status = "DOENTE";
                } else if(fome < 35){
                    status = "CANSADO";
                } else if(felicidade <= 0 || saude <= 0 || fome <= 0){
                    status = "MORTO";
                }
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

        dao.update(fome, saude, felicidade, status, agora, id);
    }
}
