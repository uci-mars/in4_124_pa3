
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Item extends HttpServlet {

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
        
        //int itemID = Integer.parseInt(request.getParameter("id"));
        int itemId = 1;
       
       
        try (PrintWriter out = response.getWriter()) {
            //get db stuff
            Connection conn = new SQLConnection().connect();
            int cost = 100000;
            String itemName = "item name";
            String img = "asset/deathcap.png";
            String description = "test des";
            //TODO FIX CONNECTION WHEN GET DRIVER IN
            
            /*Statement statement = conn.createStatement();
            String query = "SELECT * FROM items WHERE itemId = " + itemId;
            ResultSet rs = statement.executeQuery(query);
            
            
            rs.next();
            String itemName = rs.getString("itemName");
            String img = rs.getString("img");
            int cost = rs.getInt("costs");
            String description = rs.getString("itemDescription");
            
            
            rs.close();
            statement.close();
            SQLConnection.disconnect(conn);
            */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println(" <title>Product Page</title>");
                out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/products/item/item.css" + "\">");
                out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/products/product.css" + "\">");
                out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/navbar.css" + "\">");
            out.println("</head>");
     
            out.println("<body>");
            if(conn == null) out.println("<h1>null</h1>");
            else out.println("<h1>asdasfaaaaaaaaaaaaaaaaaaaaaaahhah</h1>");
            
            
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
            
            out.println("<div class=\"product-container\">");
            
            
        out.println("<h2 id=\"product-name\">" + itemName + "</h2>");
        out.println("<img style=\"display: flex; margin: auto\" id=\"product-image\" src=\"" + request.getContextPath() + "/products/" + img + "\">");


            out.println("<div style=\"display: flex; margin: auto; margin-top: 12px;\">");
                out.println("<img class=\"gold-img\" src=\"" + request.getContextPath() + "/products/assets/gold.png" + "\">");
                out.println("<span style=\"color: yellow\">Price:</span> <span style=\"color: white\">&nbsp;$ </span>");
                out.println("<span id=\"product-cost\" style=\"color: white\">&nbsp;" + cost + "</span>");
            out.println(" </div>");

            out.println("<p id=\"product-description\">");
            out.println(description);
            out.println("</p>");

            //TODO REDIRECT THIS TO THE SHOPPING CART SERVLET
            out.println("<a href=\"" + request.getContextPath() + "/order" +"\">");
                out.println("<section class=\"product-button\">");
                out.println("<span>ADD TO CART</span>");
                out.println("</section>");
           
            out.println("</a>");
                        
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            System.out.println("ASDASD");
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
