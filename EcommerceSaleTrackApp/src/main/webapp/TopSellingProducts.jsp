<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Top Selling Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 20px;
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
            padding: 10px;
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
        .back-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        .back-button a {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            font-weight: bold;
        }
        .back-button a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="back-button">
        <a href="index.jsp">Return to Home</a>
    </div>

    <h1>Top Selling Products</h1>

    <form method="get" action="TopSellingProducts">
        <label for="categoryName">Enter Category Name:</label>
        <input type="text" id="categoryName" name="categoryName" placeholder="e.g., Electronics" required>
        <button type="submit">Search</button>
    </form>

    <table>
        <tr>
            <th>Product ID</th>
            <th>Category Name</th>
            <th>Sales Count</th>
        </tr>
        <c:choose>
            <c:when test="${not empty products}">
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.productCategoryName}</td>
                        <td>${product.purchaseCount}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3" class="no-data">No products found for the specified category.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>
