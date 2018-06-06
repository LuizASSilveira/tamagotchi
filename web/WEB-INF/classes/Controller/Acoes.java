package Controller;

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

    Model.DAO dao;
    HttpServletRequest request;
    String nomePet;

    public Acoes(Model.DAO dao, HttpServletRequest request){
        nomePet = request.getCookies()[1].getValue();
        this.request = request;
        this.dao = dao;
    }

    public void alimentar(){
        System.out.println("Pet escolhido: " + nomePet);
    }

    public void banheiro(){
        System.out.println("Clicou no banheiro com o: " + nomePet);
    }

    public void curar(){
        System.out.println("Curando o bicho: " + nomePet);
    }

    public void luzes(){
        System.out.println("Apagando as luzes do: " + nomePet);
    }
}
