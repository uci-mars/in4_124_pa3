package com.s2020iae.in4_124_pa3;




import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
    
    public ResultSet query(Connection conn, String sql)
    {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;      
    }
    /*
    public void Insert(Connection conn, String sql)
    {
        
    }
    public void update(Connection conn, String sql, )
    {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, (Integer) param);
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
*/
    
    
}
