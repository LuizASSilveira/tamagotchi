package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import javax.servlet.ServletContext;

@WebServlet("/Requisicao")
public class Requisicao extends HttpServlet{

    Model.DAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // recurando o contexto e os parametros de inicializacao
        ServletContext context = getServletContext();
        String dbUser = context.getInitParameter("user");
        String dbPasswd = context.getInitParameter("password");

        System.out.println("Nome de usuario: " + dbUser);
        System.out.println("Senha de usuario: " + dbPasswd);

        try {
            dao = new Model.DAO("", dbUser, dbPasswd);
        } catch (Exception ex) {
            System.out.println("Erro ao criar conexao com o BD: " + ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // instancia o model e verifica os dados: login bateu?
        // depois instancia o view para informar o vai acontecer: exibir qual pagina?
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/html");
        out.println("<html><head><title>Foi</title></head>");
        out.println("<body>Deu certo. Operacao executada com sucessos</body></html>");
    }
}
