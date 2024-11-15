<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>E-commerce-SaleTrack</title>
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
        h1 {
            color: #4CAF50;
            text-align: center;
            font-size: 2em;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            text-align: center;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container form {
            margin: 10px 0;
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
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>E-commerce-SaleTrack</h1>
        
        <div class="button-container">
            <form action="CreateCustomer.jsp" method="get">
                <input type="submit" value="Create Customer" />
            </form>
            
            <form action="CustomerUpdate.jsp" method="get">
                <input type="submit" value="Customer Update" />
            </form>
            
            <form action="CustomerDelete.jsp" method="get">
                <input type="submit" value="Customer Delete" />
            </form>
            
            <form action="FindCustomers.jsp" method="get">
                <input type="submit" value="Find Customers" />
            </form>
            
            <form action="OrderHistory.jsp" method="get">
                <input type="submit" value="Order History" />
            </form>
            
            <form action="CategoriesByZipcode.jsp" method="get">
                <input type="submit" value="Categories By Zipcode" />
            </form>
            
            <form action="findTopPurchasedItemsByUserId.jsp" method="get">
                <input type="submit" value="Recommend Product by Customer ID" />
            </form>
            
            <form action="FreightValuesByCategory.jsp" method="get">
                <input type="submit" value="FreightValuesByCategory" />
            </form>
        </div>
    </div>
</body>
</html>
