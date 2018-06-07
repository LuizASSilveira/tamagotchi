package Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@WebServlet("/Requisicao")
public class Requisicao extends HttpServlet{

    Model.DAO dao;
    Acoes acoes;

    // recuperando as informações de banco do arquivo web.xml e criando uma instancia do DAO
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // recurando o contexto e os parametros de inicializacao
        ServletContext context = getServletContext();
        String dbUser = context.getInitParameter("user");
        String dbPasswd = context.getInitParameter("password");
        String database = context.getInitParameter("database");
        String tableUsuario = context.getInitParameter("tableUsuario");
        String tablePet = context.getInitParameter("tablePet");

        try {
            dao = new Model.DAO(database, tableUsuario, tablePet, dbUser, dbPasswd);
        } catch (Exception ex) {
            System.out.println("Erro ao criar conexao com o BD: " + ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public String getCookie(Cookie[] cookies, String nome){
        for(Cookie c : cookies){
            if(c.getName().equals(nome)){
                return c.getValue();
            }
        }

        return "-1";
    }

    /**
     *
     * @throws IOException
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
            if(new Login(request, response, dao).executa()){
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuario", login);
                response.sendRedirect("colecao.jsp");
            } else {
                // tem que retornar a pagina dizendo que o login nao esta correto
                response.sendRedirect("login.jsp");
            }
        // se tiver o nomePet, entao eh um cadastro de un novo pet
        } else if(request.getParameter("Alimentar") != null){
            new Acoes(dao, getCookie(request.getCookies(), "petId")).alimentar();
            response.sendRedirect("tamagotchi.jsp");
        // entao eh um cadastro de usuario
        } else if(request.getParameter("Banheiro") != null){
            new Acoes(dao, getCookie(request.getCookies(), "petId")).banheiro();
            response.sendRedirect("tamagotchi.jsp");
        // entao eh um cadastro de usuario
        } else if(request.getParameter("Jogar") != null){
            response.sendRedirect("game1.jsp");
        // entao eh um cadastro de usuario
        } else if(request.getParameter("Curar") != null){
            new Acoes(dao, getCookie(request.getCookies(), "petId")).curar();
            response.sendRedirect("tamagotchi.jsp");
        // entao eh um cadastro de usuario
        } else if(request.getParameter("Luzes") != null){
            new Acoes(dao, getCookie(request.getCookies(), "petId")).escuro();
            response.sendRedirect("tamagotchi.jsp");
        // entao eh um cadastro de usuario
        } else if(request.getParameter("nomePet") != null){
            if(new Cadastro(request, response, dao).insertPet()){
                response.sendRedirect("colecao.jsp");
                System.out.println("inseriu o pet");
            } else {
                System.out.println("Nao inseriu o pet");
                response.getOutputStream().print("Nome Pet invalido");
            }
        // se for para apagar o pet
        } else if(request.getParameter("Excluir") != null){
            dao.getCommand("delete from pet where id = " + getCookie(request.getCookies(), "morte") + ";");
            response.sendRedirect("colecao.jsp");
  
        // entao eh um cadastro de usuario
        } else if(request.getParameter("Reviver") != null){
            new Acoes(dao, getCookie(request.getCookies(), "petId")).reviver();
            response.sendRedirect("tamagotchi.jsp");
          
       
        } else {
            System.out.println("Tentando cadastro");
            if(new Cadastro(request, response, dao).insertUsuario()){
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("cadastro.jsp");
                // tem que retornar a pagina dizendo que o cadastro nao esta correto
            }
        }
    }
}
