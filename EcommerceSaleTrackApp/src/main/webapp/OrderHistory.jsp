<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
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
            padding: 8px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            margin-left: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .message {
            text-align: center;
            font-size: 1.2em;
            color: #4CAF50;
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            background: #fff;
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
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        table tr:hover {
            background-color: #f1f1f1;
        }
        table td {
            text-align: center;
        }
        .no-data {
            text-align: center;
            font-style: italic;
            color: #777;
        }
    </style>
</head>
<body>
    <h1>Order History</h1>
    <form action="getOrderHistoryByCustomerId" method="get">
        <label for="customerId">Customer ID:</label>
        <input type="text" name="customerId" id="customerId" placeholder="Enter Customer ID" />
        <input type="submit" value="Search" />
    </form>

    <div class="message">${messages.success}</div>

    <h1>Matching Orders</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Order Status</th>
            <th>Purchase Timestamp</th>
            <th>Approved At</th>
            <th>Carrier Delivery Date</th>
            <th>Customer Delivery Date</th>
            <th>Estimated Delivery Date</th>
        </tr>
        <c:choose>
            <c:when test="${not empty orders}">
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderStatus}</td>
                        <td><fmt:formatDate value="${order.orderPurchaseTimestamp}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.orderApprovedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.orderDeliveredCarrierDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.orderDeliveredCustomerDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${order.orderEstimatedDeliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7" class="no-data">No matching orders found</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>
