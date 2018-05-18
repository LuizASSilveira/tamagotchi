/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Venturini
 */

public class DAO{

    private static Connection con;
    private static Statement comando;

    public DAO(String db, String user, String password) throws SQLException, ClassNotFoundException{
        this.connect(db, user, password);
    }

    // faço a conecção passando os parâmetros necessários para tal operação
    private static void  connect(String database,String user, String password) throws SQLException, ClassNotFoundException{
       ConnectionFactory fact = new ConnectionPsql(database, user, password);
       fact.getConnection();
       //System.out.println(fact.statusConnection()); 
   }
}