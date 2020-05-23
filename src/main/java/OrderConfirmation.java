/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raiki
 */
public class OrderConfirmation extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conn = new SQLConnection().connect();
        
        HttpSession s = request.getSession (true);
        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) s.getAttribute("cart");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset=\"UTF-8\">");
                    out.println(" <title>Checkout</title>");
                    out.println("<script type=\"text/javascript\" src=\"" + request.getContextPath() + "/order/form.js" + "\"></script>");
                    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/order/order.css" + "\">");
                    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/products/product.css" + "\">");
                    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/navbar.css" + "\">");
                    out.println("</head>");

                out.println("<div class=\"navbar\">");
                    out.println("<a href=\""+ request.getContextPath() + "\">");
                        out.println("<div class=\"logo-container\">");
                        out.println("<img id=\"logo\" src=\" " + request.getContextPath() + "/assets/images/ornn-logo.png\">");
                        out.println("<h1 id=\"text-logo\">ORNN'S<br/>");
                        out.println("<span style=\"font-size: 18px\">WORKSHOP</span></h1>");
                        out.println("</div>");
                    out.println("</a>");

                    out.println("<div class=\"nav-container\">");
                        out.println("<a class=\"active\" href=\"" + request.getContextPath() + "/products" + "\">Our Products</a>");
                        out.println("<a href=\"" + request.getContextPath() + "/about"  + "\">About us</a>");
                        out.println("<a href=\"" + request.getContextPath() + "/about#team" + "\">Our Team</a>");
                        out.println("<a href=\"" + request.getContextPath() + "/Checkout" + "\"><img style=\"height: 20px\" src=\"" +request.getContextPath() + "/assets/images/cart.svg\"></a>");
                    out.println("</div>");

                    out.println("</div>     "
                            + "<div style=\"margin-top: 120px\">");
                    out.println("<h2 style=\"width: 100%; text-align: center; color: #f0e6d2; margin-bottom: 8px\">ORDER CONFIRMATION</h2>\n" + "        </div>");

                    if(cart.isEmpty()){
                    out.println("<h3 style =\"text-align: center; color: #f0e6d2\"> Your cart is empty </h3>");
                    }else{
                    out.println("<table style=\"margin-top: 60px\">");
                    //out.println("<tr> <th style =\" color: #f0e6d2\">item</th>\n <th style =\" color: #f0e6d2\">amount</th> </tr>");
                    Statement cartstmt = conn.createStatement();
                    int totalcost = 0;
                    for (int itemId : cart.keySet()){
                        String query = "SELECT * FROM items WHERE itemId = " + itemId;
                        ResultSet rs = cartstmt.executeQuery(query);
                        rs.next();
                        String itemName = rs.getString("itemName");
                        String img = rs.getString("img");
                        int cost = rs.getInt("costs");
                        totalcost += cost * cart.get(itemId);
                        out.println("<tr>");
                            out.println("<td> <p style=\"text-align: center; color: #f0e6d2\" >" + itemName + "</p>");
                            out.println("<img src=\"" + request.getContextPath() + "/products/" + img + "\"><td>");
                            out.println("<td><span id=\"product-cost\" style=\"color: white\">&nbsp; amount: " + cart.get(itemId) + "</span><td>");
                        out.println("</tr>");
                        rs.close();
                    }
                    out.println("<tr><td><span style =\"color: white\">Total Cost: $"+ totalcost +"</span></td></tr>");
                    //out.println("<tr><td></td></tr>");
                    out.println("</table>");
                    
                }
                if(conn != null) {
                    conn.close();
                }
                s.setAttribute("cart", new HashMap<String,Integer>());
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
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
