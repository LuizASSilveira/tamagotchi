package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession sessao = request.getSession();

        String dono = (String) sessao.getAttribute("usuario");
        String nomePet = request.getParameter("nomePet");

        try{
            dao.insertPet(dono, nomePet);
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao inserir pet: " + ex);
            return false;
        }
    }
}
