<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
            border-radius: 10px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
            padding: 25px 35px;
            width: 400px;
            text-align: center;
        }
        h1 {
            color: #d9534f;
            font-size: 2em;
            margin-bottom: 20px;
        }
        form p {
            text-align: left;
            margin: 15px 0;
        }
        label {
            display: inline-block;
            width: 120px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"] {
            width: calc(100% - 130px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #d9534f;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            font-weight: bold;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #c9302c;
        }
        .message {
            margin-top: 15px;
            font-size: 1.1em;
        }
        .hidden {
            display: none;
        }
        .disabled {
            opacity: 0.6;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${fn:escapeXml(messages.title)}</h1>
        <form action="customerDelete" method="post">
            <p>
                <label for="customerId">Customer ID:</label>
                <input type="text" id="customerId" name="customerId" 
                       placeholder="Enter Customer ID" 
                       value="${fn:escapeXml(param.customerId)}" 
                       <c:if test="${messages.disableSubmit}">disabled</c:if>
                />
            </p>
            <c:choose>
                <c:when test="${not messages.disableSubmit}">
                    <input type="submit" value="Delete Customer" />
                </c:when>
                <c:otherwise>
                    <input type="submit" value="Delete Customer" class="disabled" disabled />
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</body>
</html>
