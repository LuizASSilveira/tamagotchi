package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private Model.DAO dao;

    public Login(HttpServletRequest request, HttpServletResponse response, Model.DAO dao) {
        this.response = response;
        this.request = request;
        this.dao = dao;
    }

    public boolean executa() throws IOException, SQLException{
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        return dao.login(login, password);
    }
}
