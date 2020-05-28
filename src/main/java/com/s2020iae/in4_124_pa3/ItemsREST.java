package com.s2020iae.in4_124_pa3;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/item")
public class ItemsREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getItemByID(@PathParam("id") int id){
        Items item = getItemById(id);
        if(item==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(item).build();
        }
    }
    
    
    public Items getItemById(int id){
        Connection conn = new SQLConnection().connect();
         Items i = null;
        try{
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM pa4items WHERE itemID = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            
            //TODO: EDIT SQL TO WHERE HAS STOCK 
           System.out.println("started");
            while(rs.next()){
                i = new Items(id, rs.getString("itemName"),rs.getString("img") ,rs.getInt("costs"), 
                        rs.getString("itemDescription"), rs.getString("category"), rs.getInt("stock"));
                System.out.println("there is next");
            }
            rs.close();
            stmt.close();
            
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            SQLConnection.disconnect(conn);
            return i;
        }
    }
    public Items updateStock(int id, int bought){
        Connection conn = new SQLConnection().connect();
        Items item= getItemById(id);
        int stock = item.getStock();
        int newStock = stock-bought;
        String sql = "UPDATE items SET stock = ? WHERE itemId = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newStock);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
             e.printStackTrace();
        }finally{
            SQLConnection.disconnect(conn);
        }
        return null;
    }
}


