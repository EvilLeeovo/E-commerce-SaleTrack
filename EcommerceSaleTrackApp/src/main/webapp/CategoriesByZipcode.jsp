<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories by Zipcode</title>
</head>
<body>
    <h1>Categories by Zipcode</h1>
    <form action="getCategoriesByZipcode" method="get">
        <label for="zipcode">ZIP Code:</label>
        <input type="text" name="zipcode" id="zipcode" />
        <input type="submit" value="Search" />
    </form>
    <br>

    <table border="1">
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
                    <td colspan="3" style="text-align: center;">No categories found for the provided ZIP code.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

    <span>${messages.success}</span>
</body>
</html>
