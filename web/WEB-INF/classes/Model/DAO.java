package Model;
import java.sql.*;

public class DAO {

    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String tableUsuario;
    private final String tablePet;
    private final String password;
    private Connection connection;
    private final String database;
    private final String user;
    private Statement command;

    public DAO (String database, String tableUsuario, String tablePet, String user, String password) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");

        this.tableUsuario = tableUsuario;
        this.tablePet = tablePet;
        this.password = password;
        this.database = database;
        this.user = user;
    }

    public void insertUsuario(String usuario, String senha) throws SQLException{
        getConnection();
        String sql = "insert into " + tableUsuario + " (usuario, senha) VALUES (?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, usuario);
        stm.setString(2, senha);

        stm.executeUpdate();
        closeConnection();
    }

    public void insertPet(String dono, String nome) throws SQLException{
        // este comando eh o insert, so vamos dar um insert quando for um cadastro
        // entao os outros parametros - hunger, healt ... - passa tudo como 100%.
        System.out.println("Vai inserir aquiiiiiiiiiiiiiiiiiii");
        Timestamp timestampj = new Timestamp(System.currentTimeMillis());

        try{
            insertPet(dono, nome, 100, 1000000, 100, 100, "NORMAL", timestampj);
        } catch (Exception ex) {
            System.out.println("Nao inseriuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu: " + ex);
        }
    }

    private void insertPet(String dono, String nome, int felicidade, int qtdToques, int saude, int fome, String status, Timestamp ultimoAcesso) throws SQLException{
        getConnection();

        String sql = "insert into " + tablePet + " (nome, ultimoacesso, felicidade, qtdtoques, dono, lampada, saude, vida, fome, dataCriacao, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stm = connection.prepareStatement(sql);

        System.out.println("String original:");
        System.out.println(stm.toString());
        System.out.println("Jah travou");

        stm.setString(1, nome);
        stm.setTimestamp(2, ultimoAcesso);
        stm.setInt(3, felicidade);
        stm.setInt(4, qtdToques);
        stm.setString(5, dono);
        stm.setBoolean(6, true);    // lampada
        stm.setInt(7, saude);
        stm.setBoolean(8, true);    // vida
        System.out.println(stm.toString());
        stm.setInt(9, fome);
        System.out.println(stm.toString());
        System.out.println(stm.toString());
        stm.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
        System.out.println(stm.toString());
        stm.setInt(11, 1);

        // insere no banco
        stm.executeUpdate();
        closeConnection();
    }

    private void getConnection() throws SQLException{
        connection = DriverManager.getConnection(url+database, user, password);
        command = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    private void closeConnection() {
        try{
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexao: " + ex);
        }
    }

    public ResultSet getCommand(String s) {
        try{
            getConnection();
            return command.executeQuery(s);
        } catch (Exception ex) {
            System.out.println("Erro ao executar comando: " + ex + " Comando: " + s);
            return null;
        } finally{
            closeConnection();
        }
    }

    public boolean login(String user, String pass) throws SQLException{

        String comand = "select usuario, senha from " + tableUsuario + " where usuario = '" + user + "' and senha = '" + pass + "';";
        ResultSet res = getCommand(comand);

        return res.next();
    }

    public void update(int fome, int saude, int felicidade, String status, Timestamp agora, int id){
        try{
            getConnection();

            String sql = "update pet set fome = " + fome + ", saude = " + saude + ", felicidade = " + felicidade + ", status = '" + status + "', ultimoAcesso = '" + agora + "' where id = " + id + ";";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();

            closeConnection();
        } catch (Exception ex) {
            System.out.println("Erro ao atualizar no banco: " + ex);
        }
    }
}