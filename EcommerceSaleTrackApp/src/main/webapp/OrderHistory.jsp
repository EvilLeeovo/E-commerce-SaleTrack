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
    <br/>
    <span>${messages.success}</span>
    <br/><br/>
    
    <h1>Matching Orders</h1>
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
                    <td colspan="7" style="text-align: center;">No matching orders found</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>
