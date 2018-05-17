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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Requisicao extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // instancia o model e verifica os dados: login bateu?
        // depois instancia o view para informar o vai acontecer: exibir qual pagina?
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/html");
        out.println("<html><head>title>Respondeu corretamente</title></head>");
        out.println("<body>Deu certo</body></html>");
    }
    
}
