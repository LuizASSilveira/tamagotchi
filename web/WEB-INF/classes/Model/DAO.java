package Model;

import java.sql.*;

/**
 *
 * @author Vinicius
 */
public class DAO {

    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String password;
    private Connection connection;
    private final String database;
    private final String table;
    private final String user;
    private Statement command;
    private ResultSet data;

    public DAO (String database, String table, String user, String password) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");

        this.password = password;
        this.database = database;
        this.table = table;
        this.user = user;
    }

    public void insert(String login, String senha) throws SQLException{
        // este comando eh o insert, so vamos dar um insert quando for um cadastro
        // entao os outros parametros - hunger, healt ... - passa tudo como 100%.
        Timestamp timestampj = new Timestamp(System.currentTimeMillis());;
        insert(login, senha, 100, 100, 100, 100, timestampj, 1000000);
    }

    private void insert(String login, String senha, int hunger, int healt, int happy, int state, Timestamp lastTime, int gameTouch) throws SQLException{
        getConnection();

        String sql = "insert into " + table + " (login, senha, hunger, healt, happy, state, lastTime, gameTouch) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, login);
        stm.setString(2, senha);
        stm.setInt(3, hunger);
        stm.setInt(4, healt);
        stm.setInt(5, happy);
        stm.setInt(6, state);
        stm.setTimestamp(7, lastTime);
        stm.setInt(8, gameTouch);

        // insere no banco
        stm.executeUpdate();
        System.out.println("Executado o insert");
        closeConnection();
    }

    private void getConnection() throws SQLException{
        connection = DriverManager.getConnection(url+database, user, password);
        command = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }

    public ResultSet getCommand(String s) throws SQLException {
        return command.executeQuery(s);
    }
}