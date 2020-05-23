
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/item"})
public class ItemDetails extends HttpServlet {

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
        
        String itemId = request.getParameter("id");

        try {
            Connection conn = new SQLConnection().connect();
            Statement stmt = conn.createStatement();
         
            String sql;
            sql = "SELECT * FROM items WHERE itemId = " + itemId;
            ResultSet rs = stmt.executeQuery(sql);
            Map<String, String> item = new HashMap<String, String>();

            // Extract data from result set
                   rs.next();
                   item.put("itemID", rs.getString("itemID"));
                   item.put("itemName", rs.getString("itemName"));
                   item.put("img", rs.getString("img"));
                   item.put("costs", rs.getString("costs"));
                   item.put("itemDescription", rs.getString("itemDescription"));
                   item.put("category", rs.getString("category"));
               

            request.setAttribute("item", item);

            this.getServletContext().getRequestDispatcher("/products/item/index.jsp").forward(request, response);

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
