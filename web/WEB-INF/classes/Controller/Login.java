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

    public boolean executa(){
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try{
            return dao.login(login, password);
        } catch (Exception ex) {
            return false;
        }
    }
}
