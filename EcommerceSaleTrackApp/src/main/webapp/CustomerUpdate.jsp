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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px 30px;
            width: 350px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            font-size: 1.8em;
            margin-bottom: 20px;
        }
        form p {
            text-align: left;
            margin: 10px 0;
        }
        label {
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }
        input[type="text"] {
            width: calc(100% - 120px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[readonly] {
            background-color: #e9ecef;
            cursor: not-allowed;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .message {
            color: green;
            font-weight: bold;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Customer</h1>
        <form action="customerupdate" method="post">
            <p>
                <label for="customerId">Customer ID</label>
				<input id="customerId" name="customerId" value="${fn:escapeXml(customer.customerId)}" />

            </p>
            <p>
                <label for="city">New City</label>
                <input id="city" name="city" value="" />
            </p>
            <p>
                <label for="state">New State</label>
                <input id="state" name="state" value="" />
            </p>
            <p>
                <input type="submit" value="Update Customer" />
            </p>
        </form>
        <p class="message">${messages.success}</p>
    </div>
</body>
</html>
