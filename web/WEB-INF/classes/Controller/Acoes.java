package Controller;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

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

    HttpServletRequest request;
    String nomePet;
    Model.DAO dao;
    final int id;

    int fome;
    int saude;
    int felicidade;
    String status;

    public Acoes(Model.DAO dao, HttpServletRequest request){
        this.id = Integer.parseInt(request.getCookies()[1].getValue());

        try{
            ResultSet rs = dao.getCommand("SELECT nome from pet where id = " + id + ";");
            rs.next();

            nomePet = rs.getString("nome");
            this.request = request;
            this.dao = dao;
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar busca pelo nome na classe Acoes: " + nomePet);
        }
    }

    private void obterDados(){
        try {
            ResultSet rs = dao.getCommand("SELECT fome, saude, felicidade, status from pet where nome = '" + nomePet + "';");
            rs.next();

            fome = rs.getInt("fome");
            saude = rs.getInt("saude");
            felicidade = rs.getInt("felicidade");
            status = rs.getString("status");

        } catch (Exception ex) {
            System.out.println("Erro ao obter dados: " + ex);
        }
    }

    public void alimentar(){
        try {
            obterDados();
            System.out.println("Fome" + fome);

            fome += 10;

            dao.update(fome>100?100:fome, saude, felicidade, status, System.currentTimeMillis(), id);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }

    public void banheiro(){
        try {
            obterDados();
            System.out.println("Banheiro: " + saude);

            saude += 5;
            fome -= 10;

            dao.update(fome<0?0:fome, saude>100?100:saude, felicidade, status, System.currentTimeMillis(), id);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }

    public void curar(){
        try {
            obterDados();
            System.out.println("Curar: " + saude);

            saude += 15;
            fome -= 5;

            dao.update(fome<0?0:fome, saude>100?100:saude, felicidade, status, System.currentTimeMillis(), id);
        } catch (Exception ex) {
            System.out.println("Erro ao realizaar a alimentacao: " + ex);
        }
    }

    public void luzes(){
        System.out.println("Apagando as luzes do: " + nomePet);
    }
}
