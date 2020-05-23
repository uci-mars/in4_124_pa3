<%-- 
    Document   : index.jsp
    Created on : May 22, 2020, 1:01:36 AM
    Author     : Mars
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Page</title>
        <link rel="stylesheet" href="products/item/item.css">
        <link rel="stylesheet" href="products/product.css">
        <link rel="stylesheet" href="navbar.css">
    </head>
    
    <body>
        <div class="container">
        <div class="navbar">
            <a href="/in4_124_pa3">
                <div class="logo-container">
                    <img id="logo" src="assets/images/ornn-logo.png">
                    <h1 id="text-logo">ORNN'S<br/>
                    <span style="font-size: 18px">WORKSHOP</span></h1>
                </div>
            </a>

            <div class="nav-container">
                <a class="active" href="/in4_124_pa3/products">Our Products</a>
                <a href="/in4_124_pa3/about">About us</a>
                <a href="/in4_124_pa3/about#team">Our Team</a>
                <a href="/in4_124_pa3/Checkout"><img style="height: 20px" src="assets/images/cart.svg"></a>
            </div>
        </div>
            
        <div class="product-container">

    
            <%  
                Map<String, String> data = (HashMap<String, String>) request.getAttribute("item");
            %>
            <h2 id="product-name"><%=data.get("itemName") %></h2>
            <img style="display: flex; margin: auto" id="product-image" src="products/<%=data.get("img") %>">
            
            
            <div style="display: flex; margin: auto; margin-top: 12px;">
                <img class="gold-img" src="products/assets/gold.png">
                <span style="color: yellow">Price:</span> <span style="color: white">&nbsp;$ </span> <span id="product-cost" style="color: white">&nbsp;<%=data.get("costs") %></span>
            </div>

            <p id="product-description">
                <%=data.get("itemDescription") %>
            </p>
            
           <form name="orderForm" method="post" action="AddToCart">
            <input type="hidden" name="itemId" value="<%=data.get("itemID") %>"/>
            <div>
                <label for="quantity">Choose amount</label>
                <select id="quantity" name="quantity">
                    <option value='1'>1</option>
                    <option value='2'>2</option>
                    <option value='3'>3</option>
                    <option value='4'>4</option>
                    <option value='5'>5</option>
                    <option value='6'>6</option>
                    <option value='7'>7</option>
                    <option value='8'>8</option>
                    <option value='9'>9</option>
                    <option value='10'>10</option>
                </select>
            </div>
  
            <input type="submit" value="Order">

            </form>

        </div>

        </div>
        
    </body>

</html>