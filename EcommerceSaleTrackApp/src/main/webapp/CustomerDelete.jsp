<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete a Customer</title>
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
            color: #d9534f;
            font-size: 1.8em;
            margin-bottom: 20px;
        }
        .form-group {
            margin: 15px 0;
            text-align: left;
        }
        label {
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-top: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #d9534f;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #c9302c;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${messages.title}</h1>
        <form action="customerDelete" method="post">
            <div class="form-group" 
                 <c:if test="${messages.disableSubmit}">class="form-group hidden"</c:if>>
                <label for="customerId">Customer ID:</label>
                <input id="customerId" name="customerId" value="${fn:escapeXml(param.customerId)}" />
            </div>
            <p>
                <span id="submitButton" 
                      <c:if test="${messages.disableSubmit}">class="hidden"</c:if>>
                    <input type="submit" value="Delete Customer" />
                </span>
            </p>
        </form>
    </div>
</body>
</html>
