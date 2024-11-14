<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Customer</title>
</head>
<body>
    <h1>${messages.title}</h1>
    <form action="customerDelete" method="post">
        <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
            <label for="customerId">Customer ID:</label>
            <input id="customerId" name="customerId" value="${fn:escapeXml(param.customerId)}">
        </div>
        <p>
            <span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
            <input type="submit" value="Delete Customer">
            </span>
        </p>
    </form>
    <br/><br/>
</body>
</html>
