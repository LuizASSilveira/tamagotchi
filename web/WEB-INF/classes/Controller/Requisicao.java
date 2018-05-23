package Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;

@WebServlet("/Requisicao")
public class Requisicao extends HttpServlet{

    Model.DAO dao;

    // recuperando as informações de banco do arquivo web.xml e criando uma instancia do DAO
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // recurando o contexto e os parametros de inicializacao
        ServletContext context = getServletContext();
        String dbUser = context.getInitParameter("user");
        String dbPasswd = context.getInitParameter("password");
        String database = context.getInitParameter("database");
        String table = context.getInitParameter("table");

        try {
            dao = new Model.DAO(database, table, dbUser, dbPasswd);
        } catch (Exception ex) {
            System.out.println("Erro ao criar conexao com o BD: " + ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    /**
     *
     * @throws IOException
     * @throws SQLException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // instancia o model e verifica os dados: login bateu?
        // depois instancia o view para informar o vai acontecer: exibir qual pagina?

        // se a requisicao conter o parametro do input 'login',
        // entao envia a requisicao para a classe login
        String login = request.getParameter("login");

        if(login != null){
            // cria uma instancia de Login e executa o login
            // porem o login apenas escreve na pagina do usuario, ainda nao esta fazendo login
            try{
                if(new Login(request, response, dao).executa()){
                    System.out.println("retorna");
                    response.sendRedirect("tamagotchi.jsp");
                }
            } catch (Exception ex) {
                System.out.println("Erro ao realizar o login: " + ex);
            }
        } else {

            try{
                // cria uma instancia de cadastro e executa o cadastro
                // ja estah fazendo o cadastro
                if(new Cadastro(request, response, dao).executa()){
                    response.sendRedirect("login.jsp");
                }
            } catch (Exception ex) {
                System.out.println("Erro ao realizar o cadastro: " + ex);
            }
        }
    }
}
