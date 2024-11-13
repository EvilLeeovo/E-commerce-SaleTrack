<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Order History</title>
</head>
<body>
    <h1>Order History</h1>
    <form action="getOrderHistoryByCustomerId" method="get">
        <label for="customerId">Customer ID:</label>
        <input type="text" name="customerId" id="customerId" />
        <input type="submit" value="Search" />
    </form>
    <br>
    <c:if test="${not empty orders}">
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>Order Status</th>
                <th>Order Purchase Timestamp</th>
                <th>Order Approved At</th>
                <th>Order Delivered Carrier Date</th>
                <th>Order Delivered Customer Date</th>
                <th>Order Estimated Delivery Date</th>
            </tr>
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
        </table>
    </c:if>
    <span>${messages.success}</span>
</body>
</html>
