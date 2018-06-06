package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private long ultimoAcesso;
    private long dataCriacao;
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
        this.lampada = result.getBoolean(7);
        this.felicidade = result.getInt(4);
        this.status = result.getString(11);
        this.qtdToques = result.getInt(5);
        ultimoAcesso = result.getLong(3);
        dataCriacao = result.getLong(12);
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
    
    public void update(){
        long agora = System.currentTimeMillis();
        long deltaTime = (int) (agora - ultimoAcesso)/3000;

        float saudeR;
        float felicidadeR;
        float fomeR;

        switch(status){
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

        if(felicidade <= 0 || saude <= 0 || fome <= 0){
            status = "MORTO";
        }

        dao.update(fome, saude, felicidade, status, agora, id);
    }   
}
