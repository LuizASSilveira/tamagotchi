package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Cadastro {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private Model.DAO dao;

    Cadastro(HttpServletRequest request, HttpServletResponse response, Model.DAO dao){
        this.response = response;
        this.request = request;
        this.dao = dao;
    }

    public void executa() throws IOException, SQLException{
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/html");

        // recuperando as informacoes que o usuario passou pelo form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        

        // inserindo no bd
        dao.insert(username, password);

        // escrevendo um html para o usuario
        out.println("<html><head><title>Foi</title></head>");
        out.println("<body>Voce fez um CADASTRO com os seguitnes dados:<br>");
        out.println("Username: " + username + " <br>");
        out.println("Senha: " + password + " <br>");
        out.println("</body></html>");
    }
}
