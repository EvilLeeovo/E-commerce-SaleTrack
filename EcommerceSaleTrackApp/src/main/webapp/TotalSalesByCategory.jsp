<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Total Sales by Product Category</title>
</head>
<body>
<h1>Total Sales by Product Category</h1>

<table border="1">
    <tr>
        <th>Category Name</th>
        <th>Total Sales</th>
    </tr>
    <c:forEach var="entry" items="${totalSalesByCategory}">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<span>${messages.success}</span>
</body>
</html>
