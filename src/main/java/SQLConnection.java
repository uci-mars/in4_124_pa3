


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
    
        
      // JDBC driver name and database URL
    final String DBURL="jdbc:mysql://circinus-39.ics.uci.edu:3306/s2020Iae";

      //  Database credentials
    final String NAME = "root";
    final String PASS = "bl0b-39";
        
        /*
hostname: circinus-39.ics.uci.edu
port: 3306
username: root
name: s2020Iae
password: bl0b-39
$conn = new PDO("mysql:host=$servername; dbname=$dbname", $username, $password);
    */
    public Connection connect()
    {
        Connection conn = null;
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DBURL, NAME, PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ASD");
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("eeeeeeee");
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
