
import com.s2020iae.in4_124_pa3.Items;
import com.s2020iae.in4_124_pa3.ItemsService;
import com.s2020iae.in4_124_pa3.SQLConnection;
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
            //Items itemREST = ItemsService.getItemById(Integer.parseInt(itemId));
            Map<String, String> item = new HashMap<String, String>();

            // Extract data from result set
            /*
                   item.put("itemID", ""+itemREST.getItemID());
                   item.put("itemName", itemREST.getItemName());
                   item.put("img", itemREST.getImg());
                   item.put("costs", ""+itemREST.getCost());
                   item.put("itemDescription", itemREST.getItemDescription());
                   item.put("category", itemREST.getCategory());
            */
               //TODO ADD STOCK
                    //item.put("stock", itemREST.getStock());

            request.setAttribute("item", item);

            this.getServletContext().getRequestDispatcher("/products/item/index.jsp").forward(request, response);
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
