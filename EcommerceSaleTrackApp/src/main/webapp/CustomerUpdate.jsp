<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Customer</title>
</head>
<body>
	<h1>Update Customer</h1>
	<form action="customerupdate" method="post">
		<p>
			<label for="customerId">Customer ID</label>
			<!-- Display the customer ID as read-only -->
			<input id="customerId" name="customerId" value="${fn:escapeXml(customer.customerId)}">
		</p>
		<p>
			<label for="city">New City</label>
			<!-- Input field for the new city -->
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">New State</label>
			<!-- Input field for the new state -->
			<input id="state" name="state" value="">
		</p>
		<p>
			<!-- Submit button -->
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<!-- Display success or error messages -->
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
