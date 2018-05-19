package Controller;

import java.io.IOException;
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
public class Login {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private Model.DAO dao;

    public Login(HttpServletRequest request, HttpServletResponse response, Model.DAO dao) {
        this.response = response;
        this.request = request;
        this.dao = dao;
    }

    public void executa() throws IOException{
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        out.println("<html><head><title>Foi</title></head>");
        out.println("<body>Voce fez um LOGIN com os seguitnes dados:<br>");
        out.println("Login: " + login + " <br>");
        out.println("Senha: " + password + " <br>");
        out.println("</body></html>");
    }
}
