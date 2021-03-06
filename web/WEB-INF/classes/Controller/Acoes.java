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
 * @author luiz_
 */

public class Acoes {

    String nomePet;
    Model.DAO dao;
    final int id;

    Timestamp timeMorte;
    boolean lampada;
    int felicidade;
    String status;
    int saude;
    int fome;

    public Acoes(Model.DAO dao, String id){
        this.id = Integer.parseInt(id);

        try{
            ResultSet rs = dao.getCommand("SELECT nome from pet where id = " + id + ";");
            rs.next();

            nomePet = rs.getString("nome");
            this.dao = dao;
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar busca pelo nome na classe Acoes: " + nomePet);
        }
    }

    private void obterDados(){
        try {
            ResultSet rs = dao.getCommand("SELECT fome, saude, felicidade, status, timeMorte, lampada from pet where nome = '" + nomePet + "';");
            rs.next();

            timeMorte = rs.getTimestamp("timeMorte");
            felicidade = rs.getInt("felicidade");
            lampada = rs.getBoolean("lampada");
            status = rs.getString("status");
            saude = rs.getInt("saude");
            fome = rs.getInt("fome");
            atualizaStatus();

        } catch (Exception ex) {
            System.out.println("Erro ao obter dados: " + ex);
        }
    }

    public void atualizaStatus(){
        if(!lampada){
            return;
        }

        if(status.equals("MORTO")){
            return;
        } else if(felicidade > 25 && saude > 25 && fome > 25){
            status = "NORMAL";
        } else if(felicidade < 25){
            status = "TRISTE";
        } else if(saude < 25){
            status = "DOENTE";
        } else if(fome < 35){
            status = "CANSADO";
        }
    }

    public void escuro(){
        String sql = "";
            try {
            sql = "select lampada, status from pet where id = " + id + ";";
            ResultSet rs = dao.getCommand(sql);
            rs.next();

            boolean luz = rs.getBoolean("lampada");
            status = rs.getString("status");
            atualizaStatus();
            sql = "update pet set lampada = " + (luz?"false":"true") + ", status = '" + (luz?"DORMINDO":"NORMAL") + "' where id = " + id + ";";
            dao.getCommand(sql);
        } catch (Exception ex) {
            System.out.println("Erro ao apgar luz: " + ex + " string: " + sql);
        }
    }

    public void alimentar(){
        try {
            obterDados();

            if(!lampada){
                return;
            }

            System.out.println("Fome" + fome);

            fome += 10;

            dao.update(fome>100?100:fome, saude, felicidade, status, new Timestamp(System.currentTimeMillis()), id, timeMorte);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }
    
    public void reviver(){
        try {
            obterDados();

            Timestamp timeNow = new Timestamp(System.currentTimeMillis());
            String sql = "update pet set lampada = true, felicidade = 100, saude = 100, fome = 100, status = 'NORMAL', ultimoacesso = '" + timeNow + "', dataCriacao= '"+ timeNow +"', timeMorte =  NULL  ,  qtdToques = 1000000 where id = "+ id +";";
            dao.getCommand(sql);

        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }

    public void banheiro(){

        try {
            obterDados();

            if(!lampada){
                return;
            }

            System.out.println("Banheiro: " + saude);

            saude += 5;
            fome -= 10;

            dao.update(fome<0?0:fome, saude>100?100:saude, felicidade, status, new Timestamp(System.currentTimeMillis()), id, timeMorte);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }

    public void curar(){

        try {
            obterDados();

            if(!lampada){
                return;
            }

            System.out.println("Curar: " + saude);

            saude += 15;
            fome -= 5;

            dao.update(fome<0?0:fome, saude>100?100:saude, felicidade, status, new Timestamp(System.currentTimeMillis()), id, timeMorte);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }
}
