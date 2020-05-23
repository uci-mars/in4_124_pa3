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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mars
 */
public class RecentItem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
               
        HttpSession s = request.getSession (true);
        System.out.println(s.getId());
        System.out.println(s.getCreationTime());
        ArrayList<String> recent = (ArrayList<String>) s.getAttribute("recent"); //adds id, amount to cart

        if(recent == null){
            recent = new ArrayList<String>();
        }

        if (request.getParameter("id") != null) {
            recent.add(request.getParameter("id"));

            s.setAttribute("recent", recent);
            response.sendRedirect("item?id=" + request.getParameter("id"));
        } else {
         Connection conn = new SQLConnection().connect();
         Statement stmt = conn.createStatement();
         
         String sql;
         sql = "SELECT itemID, itemName, img, costs, category FROM items";
         ResultSet rs = stmt.executeQuery(sql);
         Map<String, HashMap<String, String>> itemList = new HashMap<String, HashMap<String, String>>();

         // Extract data from result set
         while(rs.next()){
            itemList.put(rs.getString("itemID"), new HashMap<String, String>());
            itemList.get(rs.getString("itemID")).put("itemName", rs.getString("itemName"));
            itemList.get(rs.getString("itemID")).put("img", rs.getString("img"));
            itemList.get(rs.getString("itemID")).put("costs", rs.getString("costs"));
            itemList.get(rs.getString("itemID")).put("category", rs.getString("category"));
         }
         
            s.setAttribute("recent", recent);
            PrintWriter out = response.getWriter();
            Integer count = 5;
            out.println("<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>Our Products</title>\n" +
"        <link rel=\"stylesheet\" href=\"products/product.css\">\n" +
"        <link rel=\"stylesheet\" href=\"navbar.css\">\n" +
"    </head>\n" +
"    <body>\n" +
"\n" +
"        \n" +
"        <div class=\"container\">\n" +
"        <div class=\"navbar\">\n" +
"            <a href=\"/in4_124_pa3\">\n" +
"                <div class=\"logo-container\">\n" +
"                    <img id=\"logo\" src=\"assets/images/ornn-logo.png\">\n" +
"                    <h1 id=\"text-logo\">ORNN'S<br/>\n" +
"                    <span style=\"font-size: 18px\">WORKSHOP</span></h1>\n" +
"                </div>\n" +
"            </a>\n" +
"\n" +
"            <div class=\"nav-container\">\n" +
"                <a class=\"active\" href=\"/in4_124_pa3/products\">Our Products</a>\n" +
"                <a href=\"/in4_124_pa3/about\">About us</a>\n" +
"                <a href=\"/in4_124_pa3/about#team\">Our Team</a>\n" +
"                <a href=\"/in4_124_pa3/Checkout\"><img style=\"height: 20px\" src=\"assets/images/cart.svg\"></a>\n" +
"            </div>\n" +
"        </div>");
            
            if (recent.size() > 0) {
                out.println("<div style=\"margin-top: 120px\">\n" +
"            <h2 class=\"page-title\">RECENTLY VIEWED</h2>\n" +
"        </div>\n");
            } else {
                
                out.println("<div style=\"margin-top: 120px\">\n");
                out.println("<p style='color: white'>Session ID: " + s.getId() + "</p>");
                out.println("<p style='color: white'>Session Creation Time: " + s.getCreationTime() + "</p>");
                out.println("</div>");
            }
            out.println("<table><tr id=\"result\"> ");
                for (int i = recent.size(); i-- > 0; ) {
                    if(count == 0) {
                        break;
                    }
                    out.println("                            <td>\n" +
"                                <a href='recentItem?id=" + recent.get(i) + "'>\n" +
"                                    <div class='item-container'>\n" +
"                                            <div class='item-img-container'>\n" + "<img class='item-img' src='products/" + itemList.get(recent.get(i)).get("img") + "'>" +
"                                                \n" +
"                                            </div>\n" +
"                                                <p>" + itemList.get(recent.get(i)).get("itemName") + "</p>\n" +
"                                        <div>\n" +
"                                            <img class='gold-img' src='products/assets/gold.png'>\n" +
"                                            <span style='color: yellow'>Price: <span style='color: white'>" + itemList.get(recent.get(i)).get("costs") + "</span></span>\n" +
"                                        </div>\n" +
"                                        </a>\n" +
"                                    </div>\n" +
"                                </a>\n" +
"\n" +
"                            </td>");
               count--;
                    
                      
            }
                out.println("</tr></table> ");
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
