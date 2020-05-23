/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JeisPostCode extends HttpServlet {

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
                try {
            // Response content type
            response.setContentType("text/html;charset=UTF-8");
            
            // Get shopping cart info
            HttpSession s = request.getSession (true);
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) s.getAttribute("cart");
            // cart = <Get Cart Data>;
            
            Connection conn = new SQLConnection().connect();
            
            // Shipping Address: Street \n City, State Zip
            String[] shippingInformation = {request.getParameter("street"),request.getParameter("city"),request.getParameter("state"),request.getParameter("zip"),request.getParameter("country"),request.getParameter("phoneNum"),request.getParameter("ccard"),request.getParameter("exMonth"),request.getParameter("exYr"),request.getParameter("cvv")};
            //int amount = Integer.parseInt(request.getParameter("quantity"));
            int amount = 2;
            String deliveryMethod = request.getParameter("deliv");
            String fullName = request.getParameter("fname");
            String email = request.getParameter("email");
            
            Connection conn2 = new SQLConnection().connect();
            Statement cartstmt = conn2.createStatement();
            String cartstr = new String();
            
            for (int itemId : cart.keySet()){
                String query = "SELECT * FROM items WHERE itemId = " + itemId;
                ResultSet rs = cartstmt.executeQuery(query);
                rs.next();
                String itemName = rs.getString("itemName");
                cartstr += "," + itemName;
                rs.close();
            }
            
            // Transaction architecture
            try {                
                String sql = "INSERT INTO orders (cart,amount,deliveryMethod,fullName,email,phoneNumber,streetAddress,city,stateName,zipCode,country,creditCardNumber,expirationMonth,expirationYear,cvv) VALUES(cart=?,amount=?,deliveryMethod=?,fullName=?,email=?,phoneNumber=?,streetAddress=?,city=?,stateName=?,zipCode=?,country=?,creditCardNumber=?,expirationMonth=?,expirationYear=?,cvv=?)";
                
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                prepStmt.setString(1,cartstr);
                prepStmt.setInt(2,amount);
                prepStmt.setString(3,deliveryMethod);
                prepStmt.setString(4,fullName);
                prepStmt.setString(5,email);
                prepStmt.setString(6,shippingInformation[5]); // phoneNumber
                prepStmt.setString(7,shippingInformation[0]); // streetAddress
                prepStmt.setString(8,shippingInformation[1]); // city
                prepStmt.setString(8,shippingInformation[2]); // stateName
                prepStmt.setInt(8,Integer.parseInt(shippingInformation[3])); // zipCode
                prepStmt.setString(8,shippingInformation[4]); // country
                prepStmt.setString(8,shippingInformation[6]); // creditCardNumber
                prepStmt.setInt(8,Integer.parseInt(shippingInformation[7])); // expMonth
                prepStmt.setInt(9,Integer.parseInt(shippingInformation[8])); //expYr
                prepStmt.setInt(10,Integer.parseInt(shippingInformation[9])); //cvv
                prepStmt.addBatch();
                int[] affectedRecords = prepStmt.executeBatch();
                
            } catch (Exception e) {
                // If query execution fails, rollback db changes
                conn.rollback();
                
            } finally {
                
                // Order Confirmations Page
                
                double totalItemCost = 0;
                // Form HTML
                try (PrintWriter out = response.getWriter()) {
                    out.println("<table>");
                        //out.println("<tr> <th style =\" color: #f0e6d2\">item</th>\n <th style =\" color: #f0e6d2\">amount</th> </tr>");
                        for (int itemId : cart.keySet()){
                            String query = "SELECT * FROM items WHERE itemId = " + itemId;
                            ResultSet rs = cartstmt.executeQuery(query);
                            rs.next();
                            String itemName = rs.getString("itemName");
                            String img = rs.getString("img");
                            int cost = rs.getInt("costs");
                            out.println("<h3 style =\"text-align: center; color: #f0e6d2\"> Cart </h3>");
                            out.println("<tr>");
                                out.println("<td> <p style=\"text-align: center; color: #f0e6d2\" >" + itemName + "</p>");
                                out.println("<img src=\"" + request.getContextPath() + "/products/" + img + "\"><td>");
                                out.println("<td><span id=\"product-cost\" style=\"color: white\">&nbsp; amount: " + cart.get(itemId) + "</span><td>");
                            out.println("</tr>");
                            totalItemCost += cost*cart.get(itemId);
                            rs.close();
                        }
                        out.println("</table>");
                }
                
                // close connection
                if(conn != null) {
                    conn.close();
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Order Form and Confirmation Servlet";
    }

}
