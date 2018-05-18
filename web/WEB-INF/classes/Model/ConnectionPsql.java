/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author gabriel
 */
public class ConnectionPsql implements ConnectionFactory {

    private String status = "Não conectou!!";
    private final String database;
    private final String username;
    private final String password;

    public ConnectionPsql(String database,String username, String password){
        this.database = database;
        this.password = password;
        this.username = username;
    }

    @Override
    public String getDatabase(){
        return database;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
       Connection connection = null;
        try{
            String url = "jdbc:postgresql://localhost:5432"+getDatabase(); // "//localhost:5432" porta padão de instalação do postgre
            connection = DriverManager.getConnection(url,getUsername(),getPassword());

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

    @Override
    public String statusConnection() {
        return status;
    }

    @Override
    public boolean closeConnection() throws ClassNotFoundException {
        try {
            getConnection().close();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public Connection restartConnection() throws ClassNotFoundException, SQLException {
        closeConnection();
        return getConnection();
    }
}
