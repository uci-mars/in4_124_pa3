/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhiyi
 */
public class AddToCart extends HttpServlet {

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
        
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        //int itemId = 1;
        int amt = Integer.parseInt(request.getParameter("quantity"));
        //int amt = 2;
        HttpSession s = request.getSession (true);
        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) s.getAttribute("cart"); //adds id, amount to cart
        /*TestObj i = (TestObj) s.getAttribute("ii");
        if(i == null)
            s.setAttribute("ii", new TestObj());
        //i.inc();
        */
        if(cart == null){
            cart = new HashMap<Integer, Integer>();
            //s.setAttribute("cart", new HashMap<String,Integer>());
        }
       // Integer quantity = cart.get(itemId);
        if(cart.get(itemId) != null){
            cart.put(itemId, amt+cart.get(itemId));
        }else{
            cart.put(itemId, amt);
        }
        s.setAttribute("cart", cart);
        
        response.sendRedirect("Checkout");
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
