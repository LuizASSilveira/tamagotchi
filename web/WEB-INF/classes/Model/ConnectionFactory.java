/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
interface  ConnectionFactory {

    public String getDatabase();
    public String getUsername();
    public String getPassword();

    /// metodo de conexão padrão 
    public Connection getConnection() throws SQLException, ClassNotFoundException;

    // metodo para retorno do status da conexão
    public String statusConnection();

    // fecha a conexão, acho que ja percebeu!
    public boolean closeConnection() throws ClassNotFoundException;

    // reiniciar conexão \o
    public Connection restartConnection() throws ClassNotFoundException, SQLException;    
}
