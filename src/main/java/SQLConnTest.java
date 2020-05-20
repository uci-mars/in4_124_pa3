import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnTest {
    
    public static void main(String [] args){
        String driver = "com.sql.cj.jdbc.Driver";
        String url = "jdbc:mysql://circinus-39.ics.uci.edu:3306/s2020Iae?serverTimezone=UTC";
        String username = "root";
        String pass = "bl0b-39";
        try{
            //Class.forName(driver);
            int itemId = 1;
            Connection conn = DriverManager.getConnection(url,username,pass);
            System.out.println("succ");
            
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM items WHERE itemId = " + itemId;
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            String itemName = rs.getString("itemName");
            String img = rs.getString("img");
            int cost = rs.getInt("costs");
            String description = rs.getString("itemDescription");
            
            System.out.println("itemName: " + itemName + " " + img + " $" +cost +" \n" + description);
        }catch (Exception e){
            System.out.println("ADSADAS");
            e.printStackTrace();
        }
        
        
    }
    
    
}