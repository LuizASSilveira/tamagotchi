package Controller;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cadastro {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private Model.DAO dao;

    Cadastro(HttpServletRequest request, HttpServletResponse response, Model.DAO dao){
        this.response = response;
        this.request = request;
        this.dao = dao;
    }

    public boolean insertUsuario(){
        // recuperando as informacoes que o usuario passou pelo form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // inserindo no bd
        try{
            dao.insertUsuario(username, password);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean insertPet(){
        // recuperando as informacoes que o usuario passou pelo form
        //String username = request.getParameter("username");
        System.out.println("veio aqui");
        String dono = "luiz";
        String nomePet = request.getParameter("nomePet");

        try{
            System.out.println("INSERINDO LAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
            dao.insertPet(dono, nomePet);
            System.out.println("terminou");
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao inserir pet: " + ex);
            return false;
        }
    }
}
