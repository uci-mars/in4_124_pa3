


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhiyi
 */
public class SQLConnection {
    
        
    String driver = "com.sql.cj.jdbc.Driver";
    String url = "jdbc:mysql://circinus-39.ics.uci.edu:3306/s2020Iae?serverTimezone=UTC";
    String username = "root";
    String pass = "bl0b-39";
    Connection conn = null;
    public Connection connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,pass);
        } catch (Exception ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static void disconnect(Connection conn)
    {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 

    
    
}
