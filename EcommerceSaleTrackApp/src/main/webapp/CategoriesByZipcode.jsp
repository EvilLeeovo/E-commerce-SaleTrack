<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories by Zipcode</title>
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
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-size: 1.2em;
        }
        input[type="text"] {
            padding: 8px;
            width: 200px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        table th {
            background-color: #4CAF50;
            color: white;
        }
        table td {
            background-color: #fff;
        }
        table tr:nth-child(even) td {
            background-color: #f9f9f9;
        }
        .message {
            text-align: center;
            color: #4CAF50;
            font-weight: bold;
        }
        .no-data {
            text-align: center;
            font-style: italic;
            color: #777;
        }
    </style>
</head>
<body>
    <h1>Categories by Zipcode</h1>
    <form action="getCategoriesByZipcode" method="get">
        <label for="zipcode">ZIP Code:</label>
        <input type="text" name="zipcode" id="zipcode" placeholder="Enter ZIP Code" />
        <input type="submit" value="Search" />
    </form>

    <table>
        <tr>
            <th>Category Name</th>
            <th>ZIP Code</th>
            <th>Count</th>
        </tr>
        <c:choose>
            <c:when test="${not empty categoryByZipcode}">
                <c:forEach var="category" items="${categoryByZipcode}">
                    <tr>
                        <td>${category.productCategoryNameEnglish}</td>
                        <td>${category.customerZipCodePrefix}</td>
                        <td>${category.count}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3" class="no-data">No categories found for the provided ZIP code.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

    <div class="message">${messages.success}</div>
</body>
</html>
