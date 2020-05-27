/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.s2020iae.in4_124_pa3.SQLConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Mars
 */
@WebServlet(urlPatterns = {"/products"})
public class Products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        try {
         // Open a connection
         Connection conn = new SQLConnection().connect();
         Statement stmt = conn.createStatement();
         
         String sql;
         sql = "SELECT itemID, itemName, img, costs, category FROM items";
         ResultSet rs = stmt.executeQuery(sql);
         Map<Integer, HashMap<String, String>> itemList = new HashMap<Integer, HashMap<String, String>>();

         // Extract data from result set
         while(rs.next()){
            itemList.put(rs.getInt("itemID"), new HashMap<String, String>());

            itemList.get(rs.getInt("itemID")).put("itemName", rs.getString("itemName"));
            itemList.get(rs.getInt("itemID")).put("img", rs.getString("img"));
            itemList.get(rs.getInt("itemID")).put("costs", rs.getString("costs"));
            itemList.get(rs.getInt("itemID")).put("category", rs.getString("category"));
         }

         request.setAttribute("itemList", itemList);


         this.getServletContext().getRequestDispatcher("/products/index.jsp").forward(request, response);
        
         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
