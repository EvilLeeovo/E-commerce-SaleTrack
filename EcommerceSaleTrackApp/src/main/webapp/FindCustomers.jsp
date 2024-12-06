<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Find a Customer</title>
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
    <h1>Search for a Customer by City</h1>
    <form action="findcustomers" method="post">
        <label for="city">City:</label>
        <input type="text" id="city" name="city" placeholder="Enter city name" value="${fn:escapeXml(param.city)}" required>
        <button type="submit">Search</button>
    </form>

    <h1>Matching Customers</h1>
    <table>
        <tr>
            <th>Customer ID</th>
            <th>Unique ID</th>
            <th>Zip Code Prefix</th>
            <th>City</th>
            <th>State</th>
            <th>Delete Customer</th>
            <th>Update Customer</th>
            <th>Order History</th>
        </tr>
        <c:choose>
            <c:when test="${not empty customers}">
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${customer.getCustomerId()}</td>
                        <td>${customer.getCustomerUniqueId()}</td>
                        <td>${customer.getCustomerZipCodePrefix()}</td>
                        <td>${customer.getCustomerCity()}</td>
                        <td>${customer.getCustomerState()}</td>
                        <td><a href="customerDelete?customerId=${customer.getCustomerId()}">Delete</a></td>
                        <td><a href="customerupdate?customerId=${customer.getCustomerId()}">Update</a></td>
                        <td><a href="getOrderHistoryByCustomerId?customerId=${customer.getCustomerId()}">Order History</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8" class="no-data">No matching customer found</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>
