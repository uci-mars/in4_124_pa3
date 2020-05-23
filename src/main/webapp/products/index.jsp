<%-- 
    Document   : index
    Created on : May 19, 2020, 5:59:51 PM
    Author     : Mars
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <div style="margin-top: 60px">
            <h2 class="page-title">OUR PRODUCTS</h2>
        </div>

        <div class="content-container">
            <div class="item-table-container">
                <table>
                        <tr id="result">                  
                    <% 
                    Map<Integer, HashMap<String, String>> data = (HashMap<Integer, HashMap<String, String>>) request.getAttribute("itemList"); 
                    
                    RequestDispatcher rd = request.getRequestDispatcher("/recentItem");
                    rd.include(request, response);

                        for(Entry<Integer, HashMap<String, String>> item : data.entrySet()) {
                        %>
                            <td>
                                <a href='recentItem?id=<%=item.getKey() %>'>
                                    <p class='category-label'><%=item.getValue().get("category") %></p>
                                    <div class='item-container'>
                                            <div class='item-img-container'>
                                                <img class='item-img' src='<%= "products/" + item.getValue().get("img") %>'>
                                            </div>
                                                <p><%=item.getValue().get("itemName") %></p>
                                        <div>
                                            <img class='gold-img' src='products/assets/gold.png'>
                                            <span style='color: yellow'>Price: <span style='color: white'><%=item.getValue().get("costs") %></span></span>
                                        </div>
                                        </a>
                                    </div>
                                </a>

                            </td>
                        
                       <% 
                        }
                     %>
                     </tr>
                    
                </table>

            </div>
                <div class="footer">
                    <p>Created for Educational Purposes. All assets belongs to Riot Games. </p>
                </div>
            </div>
        </div>
    </body>
</html>
