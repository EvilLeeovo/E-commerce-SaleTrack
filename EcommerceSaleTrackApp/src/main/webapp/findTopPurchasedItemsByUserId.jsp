<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="eCommerceSaleTrack.model.Products" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recommend Product by Customer ID</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #4CAF50;
            text-align: center;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-size: 1.2em;
            margin-right: 10px;
        }
        input[type="text"] {
            padding: 8px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            text-align: center;
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        table th {
            background-color: #4CAF50;
            color: white;
        }
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        table tr:hover {
            background-color: #f1f1f1;
        }
        .no-data {
            text-align: center;
            font-style: italic;
            color: #777;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Find Recommended Products by Customer ID</h2>

    <form action="findTopPurchasedItemsByUserId" method="get">
        <label for="userId">Enter Customer ID:</label>
        <input type="text" id="userId" name="userId" required>
        <button type="submit">Search</button>
    </form>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <% 
        String userId = (String) request.getAttribute("userId");
        List<Products> topPurchasedProducts = (List<Products>) request.getAttribute("topPurchasedProducts");
        
        if (userId == null) { 
    %>
        <p class="no-data">No recommendations available. Please enter a Customer ID to search.</p>
    <% 
        } else { 
    %>
        <h3 style="text-align:center;">Recommended Products for Customer ID: <%= userId %></h3>
        <%
            if (topPurchasedProducts != null && !topPurchasedProducts.isEmpty()) { 
        %>
            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Category</th>
                    <th>Product Name Length</th>
                    <th>Purchase Count</th>
                </tr>
                <% for (Products product : topPurchasedProducts) { %>
                    <tr>
                        <td><%= product.getProductId() %></td>
                        <td><%= product.getProductCategoryName() %></td>
                        <td><%= product.getProductNameLength() %></td>
                        <td><%= product.getPurchaseCount() %></td>
                    </tr>
                <% } %>
            </table>
        <% 
            } else { 
        %>
            <p class="no-data">No purchases found for the provided Customer ID.</p>
        <% } %>
    <% } %>
</body>
</html>
