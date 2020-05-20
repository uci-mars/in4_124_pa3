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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhiyi
 */
public class Checkout extends HttpServlet {

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
        
        
        double totalItemCost = 0;
        
        HttpSession s = request.getSession (true);
        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) s.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<Integer, Integer>();
            //s.setAttribute("cart", new HashMap<String,Integer>());
        }
        
        try ( PrintWriter out = response.getWriter()) {
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
            
                     
            out.println("<body onload = \"updateTotal()\">");
            out.println("<div class=\"container\">");
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
                        out.println("<a href=\"" + request.getContextPath() + "/order" + "\"><img style=\"height: 20px\" src=\"" +request.getContextPath() + "/assets/images/cart.svg\"></a>");
                    out.println("</div>");
                out.println("</div>");
                
               
                
                out.println("</div>     "
                        + "<div style=\"margin-top: 120px\">"); 
                out.println("<h2 style=\"width: 100%; text-align: center; color: #f0e6d2; margin-bottom: 8px\">ORDER FORM</h2>\n" +
                    "        </div>");
                
                
                
                
                 //show cart
                Connection conn = new SQLConnection().connect();
                Statement statement = conn.createStatement();
                               
                if(cart.size() == 0){
                    out.println("<h3 style =\"text-align: center; color: #f0e6d2\"> Your cart is empty </h3>");
                }else{
                    out.println("<table>");
                    //out.println("<tr> <th style =\" color: #f0e6d2\">item</th>\n <th style =\" color: #f0e6d2\">amount</th> </tr>");
                    for (int itemId : cart.keySet()){
                        String query = "SELECT * FROM items WHERE itemId = " + itemId;
                        ResultSet rs = statement.executeQuery(query);
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
               
                statement.close();
                SQLConnection.disconnect(conn);
               
                out.println("<div style=\"width: 500px; display: flex; flex-direction: column; margin: auto; margin-top: 40px;\">");
                
                //TODO FIX ORDER FORM ON SUBMIT
                out.println("<form action=\"sendorder.php\" name=\"order\" onSubmit=\"return processPurchaseForm()\" method=\"post\">");
                
                out.println("        <div style=\"display: flex; flex-direction: row; margin-bottom: 16px;\">\n" +
"            <label style=\"margin: auto; margin-left: 0; margin-right: 12px\">Delivery Method </label>\n" +
"            <div style=\"display: flex; flex-direction: row; margin: auto\">\n" +
"                <input type=\"radio\" id=\"overnight\" name=\"deliv\" required checked onchange=\"updateTotal()\" value=\"overnight\">\n" +
"                <label for=\"overnight\" style=\"margin: auto; margin-left: 6px; margin-right: 26px\">Overnight</label>\n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: row; margin: auto\">\n" +
"                <input type=\"radio\" id=\"2day\" name=\"deliv\" onchange=\"updateTotal()\" value=\"2day\">\n" +
"                <label for=\"2day\" style=\"margin: auto;  margin-left: 6px\">2-days expedited</label>            \n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: row; margin: auto\">\n" +
"                <input type=\"radio\" id=\"6day\" name=\"deliv\" onchange=\"updateTotal()\" value=\"6day\">\n" +
"                <label for=\"6day\" style=\"margin: auto;  margin-left: 6px\">6-days ground</label>\n" +
"            </div>\n" +
"\n" +
"        </div>\n" +
"\n" +
"\n" +
"        <label for=\"fname\">Full name</label>\n" +
"        <input type=\"text\" id=\"fname\" name=\"fname\" required>\n" +
"                \n" +
"        <div style=\"display: flex; flex-direction: row\">\n" +
"            <div style=\"display: flex; flex-direction: column; width: 50%;\">\n" +
"                <label for=\"email\">Email</label>\n" +
"                <input type=\"email\" id=\"email\" name=\"email\" placeholder=\"peter@uci.edu\" required>    \n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: column; width: 50%; margin-left: 46px;\">\n" +
"                <label for=\"phoneNum\">Phone number</label>\n" +
"                <input type=\"tel\" id=\"phoneNum\" name=\"phoneNum\" required maxlength=\"11\" minlength=\"10\" placeholder=\"(123)456-7890\">\n" +
"            </div>\n" +
"        </div>\n" +
"\n" +
"          \n" +
"        <div style=\"display: flex; flex-direction: row\">\n" +
"            <div style=\"display: flex; flex-direction: column; width: 50%;\">\n" +
"                <label for=\"street\">Street Address</label>\n" +
"                <input type=\"text\" id=\"street\" name=\"street\" placeholder=\"510 E Peltason Dr.\" required>\n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: column; width: 50%; margin-left: 46px;\">\n" +
"                <label for=\"city\">City</label>\n" +
"                <input type=\"text\" id=\"city\" name=\"city\" placeholder=\"Irvine\" required>\n" +
"            </div>\n" +
"        </div>\n" +
"        \n" +
"\n" +
"                \n" +
"        <div style=\"display: flex; flex-direction: row\">\n" +
"            <div style=\"display: flex; flex-direction: column; width: 33%;\">\n" +
"                <label for=\"state\">State</label>\n" +
"                <input type=\"text\" id=\"state\" name=\"state\" placeholder=\"California\" required>\n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: column; width: 23%; margin-left: 46px;\">\n" +
"                <label for=\"zip\">ZIP</label>\n" +
"                <input type=\"text\" id=\"zip\" name=\"zip\" placeholder=\"92612\" \"required>\n" +
"            </div>\n" +
"            <div style=\"display: flex; flex-direction: column; width: 33%; margin-left: 46px;\">\n" +
"                <label for=\"country\">Country/Reigion</label>\n" +
"                <input type=\"text\" id=\"country\" name=\"country\" placeholder=\"United States\" required>\n" +
"            </div>\n" +
"        </div>\n" +
"                \n" +
"\n" +
"        <div style=\"display: flex; flex-direction: row\">\n" +
"            <div style=\"display: flex; flex-direction: column; width: 60%;\">\n" +
"                <label for=\"ccard\">Credit card Number</label>\n" +
"                <input type=\"text\" id=\"ccard\" name=\"ccard\" minlength=\"16\" maxlength=\"20\" required>\n" +
"            </div>\n" +
"            \n" +
"            <div style=\"display: flex; flex-direction: column; width: 30%; margin-left: 46px;\">\n" +
"                    <label for=\"exDate\">Expiration date</label>\n" +
"                    <div style=\"display: flex; flex-direction: row\">\n" +
"                        <input type=\"text\" id=\"exMonth\" name=\"exMonth\" size=\"2\" placeholder=\"MM\" minlength=\"2\" maxlength=\"2\" required>\n" +
"                        <label style=\"margin: auto; font-size: 18px;\">/</label>\n" +
"                        <input type=\"text\" id=\"exYr\" name=\"exYr\" size=\"4\" placeholder=\"YY\" minlength=\"2\" maxlength=\"4\"required>\n" +
"                    </div>\n" +
"            </div>\n" +
"            \n" +
"            <div style=\"display: flex; flex-direction: column; width: 10%; margin-left: 26px;\">\n" +
"                <label for=\"cvv\">CVV</label>\n" +
"                <input type=\"text\" id=\"cvv\" name=\"cvv\" size=\"3\" minlength=\"3\" maxlength=\"4\" placeholder=\"000\" required>\n" +
"            </div>\n" +
"        </div>\n" +
"\n" +
"        <br><br>" );
                out.println(     
"        <div style=\"font-size: 1.1rem\">\n" +
"            <p>item(s): $<span id=\"itemTotal\">" + totalItemCost +"</span></p>\n" +
"            <p>tax: $<span id=\"taxTotal\">0</span></p>\n" +
"            <p>shipping: $<span id=\"shippingTotal\">0</span></p>\n" +
"        </div>\n" +
"        \n" +
"        <div style=\"display: flex; flex-direction: row;\">\n" +
"            <p style=\"font-size: 1.7rem\" style=\"display: flex; flex-direction: column; width: 70%;\">Total: $<span id=\"totalPrice\">0</span></p>\n" +
"        <input type=\"submit\" value=\"Order\" style=\"display: flex; flex-direction: column; width: 22%;  margin-right: 0\">\n" );
            out.println("</div>");
                
            out.println("</form>");
               
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
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
