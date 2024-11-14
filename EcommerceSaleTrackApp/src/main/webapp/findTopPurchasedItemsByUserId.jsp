<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="eCommerceSaleTrack.model.Products" %>
<html>
<head>
    <title>Recommend Product by Customer ID</title>
</head>
<body>
<h2>Find Recommended Products by Customer ID</h2>

<form action="findTopPurchasedItemsByUserId" method="get">
    <label for="userId">Enter Customer ID:</label>
    <input type="text" id="userId" name="userId" required>
    <button type="submit">Search</button>
</form>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) { 
%>
    <p style="color:red;"><%= error %></p>
<% 
    } 
%>

<%
    String userId = (String) request.getAttribute("userId");
    if (userId != null) { 
%>
    <h3>Recommended Products for Customer ID: <%= userId %></h3>
    <%
        List<Products> topPurchasedProducts = (List<Products>) request.getAttribute("topPurchasedProducts");

        if (topPurchasedProducts != null && !topPurchasedProducts.isEmpty()) { 
    %>
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Category</th>
                <th>Product Name Length</th>
                <th>Purchase Count</th>
            </tr>
            <% 
                for (Products product : topPurchasedProducts) { 
            %>
                <tr>
                    <td><%= product.getProductId() %></td>
                    <td><%= product.getProductCategoryName() %></td>
                    <td><%= product.getProductNameLength() %></td>
                    <td><%= product.getPurchaseCount() %></td>
                </tr>
            <% 
                } 
            %>
        </table>
    <% 
        } else { 
    %>
        <p>No purchases found for the provided Customer ID.</p>
    <% 
        } 
    %>
<% 
    } 
%>

</body>
</html>
