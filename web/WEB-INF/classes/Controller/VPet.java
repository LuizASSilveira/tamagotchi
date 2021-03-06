package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

    private Timestamp ultimoAcesso;
    private Timestamp dataCriacao;
    private Timestamp timeMorte;
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
    private int tipo;
    private int id;

    public VPet(ResultSet result) throws SQLException, ClassNotFoundException {
        //System.out.println("Tempo de morte->" + result.getTimestamp(14) + "<--");
        this.timeMorte = result.getTimestamp(14);
        ultimoAcesso = result.getTimestamp(3);
        dataCriacao = result.getTimestamp(12);
        this.lampada = result.getBoolean(7);
        this.felicidade = result.getInt(4);
        this.status = result.getString(11);
        this.qtdToques = result.getInt(5);
        this.vida = result.getBoolean(9);
        this.dono = result.getString(6);
        this.nome = result.getString(2);
        this.saude = result.getInt(8);
        this.fome = result.getInt(10);
        this.id = result.getInt(1);
        tipo = result.getInt(13);

        dao = new Model.DAO("lp", "usuario", "pet", "postgres", "root");

        update();
    }

    private String trocaStatus(){
        if(status.equals("MORTO")){
            System.out.println("Morto tentando rescuscitar");
            return "MORTO";
        } else if(felicidade > 25 && saude > 25 && fome > 25){
            return "NORMAL";
        } else if(felicidade < 25){
            return "TRISTE";
        } else if(saude < 25){
            return "DOENTE";
        } else if(fome < 35){
            return "CANSADO";
        } return "MORTO";
    }

    public void update(){
        long agora = System.currentTimeMillis();
        
        System.out.println((agora - ultimoAcesso.getTime())/3000);
        
        long deltaTime = (int) (agora - ultimoAcesso.getTime())/3000;

        float saudeR;
        float felicidadeR;
        float fomeR;

        switch(trocaStatus()){
            case "NORMAL":
                saudeR = (float) 0.4;
                felicidadeR = (float) 1.5;
                fomeR = (float) 1.7;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "DOENTE":
                saudeR = (float) 0.8;
                felicidadeR = (float) 2;
                fomeR = (float) 1;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "CANSADO":
                saudeR = (float) 0.3;
                felicidadeR = (float) 1.5;
                fomeR = (float) 2.5;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "SUJO":
                saudeR = (float) 2;
                felicidadeR = (float) 1.5;
                fomeR = (float) 1.7;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "TRISTE":
                saudeR = (float) 0.8;
                felicidadeR = (float) 3;
                fomeR = (float) 1;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "DORMINDO":
                saudeR = (float) 0.2;
                felicidadeR = (float) 0.4;
                fomeR = (float) 0.8;

                fome -= (fomeR * deltaTime);
                saude -= (saudeR * deltaTime);
                felicidade -= (felicidadeR * deltaTime);
            break;

            case "MORTO":
                fome = 0;
                saude = 0;
                felicidade = 0;
            break;
        }

        if(lampada){
            if(felicidade < 25){
                status = "TRISTE";
                System.out.println("Trocou para triste");
            } else if(saude < 25){
                status = "DOENTE";
                System.out.println("Trocou para doente");
            } else if(fome < 35){
                status = "CANSADO";
                System.out.println("Trocou para cansado");
            }
        }

        if(felicidade <= 0 || saude <= 0 || fome <= 0){
            // se ele jah nao estiver morto
            if(timeMorte == null){
                timeMorte = new Timestamp(System.currentTimeMillis());
            }
            status = "MORTO";
        }

        // o timeMorte eh retornado null se nao tiver nada, mas para inserirmos tem que ser uma string vazia
        dao.update(fome, saude, felicidade, status, new Timestamp(agora), id, timeMorte);
    }   
}
