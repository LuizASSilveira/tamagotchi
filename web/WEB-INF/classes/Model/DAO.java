/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Venturini
 */

public class DAO {

    private String status = "Não conectou!!";
    private final String database;
    private final String username;
    private final String password;

    public DAO(String database, String username, String password) throws SQLException, ClassNotFoundException{
        this.database = database;
        this.password = password;
        this.username = username;

        createdbs();
   }

    public String getDatabase(){
        return database;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432";// + getDatabase();   // "//localhost:5432" porta padão de instalação do postgre
            connection = DriverManager.getConnection(url, getUsername(), getPassword());

            if(connection != null){
                status =  ("STATUS--->Conectado com sucesso!");
            }else{
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
        } catch (SQLException e){
           System.out.println("erro ao se conectar");
           System.out.println(e);
        }

        return connection;
    }

    public String statusConnection() {
        return status;
    }

    private void createdbs() throws SQLException, ClassNotFoundException{
        ResultSet rs = getConnection().createStatement().executeQuery("\\i firstsOperations.sql");
        closeConnection();
    }

    public boolean closeConnection() throws ClassNotFoundException {
        try {
            getConnection().close();
            return true;
        }catch (SQLException e){
            return false;
        }
    }
}