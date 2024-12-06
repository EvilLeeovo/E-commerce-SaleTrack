<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Total Freight Value By Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-size: 1.2em;
            margin-right: 10px;
        }
        input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        button:hover {
            background-color: #45a049;
        }
        hr {
            margin: 20px 0;
            border: 0;
            border-top: 1px solid #ddd;
        }
        .message {
            text-align: center;
            font-size: 1.2em;
            color: #4CAF50;
            font-weight: bold;
        }
        .result {
            text-align: center;
            font-size: 1.2em;
            margin-top: 20px;
        }
        .result strong {
            color: #4CAF50;
        }
        .no-input {
            text-align: center;
            font-style: italic;
            color: #777;
            margin-top: 20px;
        }
        .back-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        .back-button a {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            font-weight: bold;
        }
        .back-button a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="back-button">
        <a href="index.jsp">Return to Home</a>
    </div>

    <h1>Total Freight Value By Category</h1>

    <form method="get" action="freightValuesByCategory">
        <label for="categoryName">Enter Product Category:</label>
        <input type="text" id="categoryName" name="categoryName" placeholder="e.g., cool_stuff" required>
        <button type="submit">Search</button>
    </form>

    <hr>

    <c:choose>
        <c:when test="${empty categoryName && empty totalFreightValue}">
            <div class="no-input">
                <p>Please enter a product category to view the total freight value.</p>
            </div>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty messages}">
                <div class="message">
                    <strong>${messages.success}</strong>
                </div>
            </c:if>

            <c:if test="${not empty totalFreightValue}">
                <div class="result">
                    <p>Total Freight Value: <strong>${totalFreightValue}</strong></p>
                </div>
            </c:if>
        </c:otherwise>
    </c:choose>
</body>
</html>
