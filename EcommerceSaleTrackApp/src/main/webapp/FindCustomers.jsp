<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Customer</title>
</head>
<body>
	<form action="findcustomers" method="post">
		<h1>Search for a Customer by City</h1>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="${fn:escapeXml(param.city)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="customerCreate"><a href="createCustomer">Create Customer</a></div>
	<br/>
	<h1>Matching Customers</h1>
        <table border="1">
            <tr>
                <th>Customer ID</th>
                <th>Unique ID</th>
                <th>Zip Code Prefix</th>
                <th>City</th>
                <th>State</th>
                <th>Delete Customer</th>
                <th>Update Customer</th>
                <th> Order History</th>
            </tr>
            <c:forEach items="${customers}" var="customer" >
                <tr>
                    <td><c:out value="${customer.getCustomerId()}" /></td>
                    <td><c:out value="${customer.getCustomerUniqueId()}" /></td>
                    <td><c:out value="${customer.getCustomerZipCodePrefix()}" /></td>
                    <td><c:out value="${customer.getCustomerCity()}" /></td>
                    <td><c:out value="${customer.getCustomerState()}" /></td>
                    <td><a href="customerDelete?customerId=<c:out value="${customer.getCustomerId()}"/>">Delete</a></td>
                    <td><a href="customerupdate?customerId=<c:out value="${customer.getCustomerId()}"/>">Update</a></td>
                    <td><a href="getOrderHistoryByCustomerId?customerId=<c:out value='${customer.getCustomerId()}'/>">Order History</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
