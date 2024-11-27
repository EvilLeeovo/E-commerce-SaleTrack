<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>E-commerce-SaleTrack</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            font-size: 2em;
        }
        .main-container {
            display: flex;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 900px;
        }
        .section {
            flex: 1;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        .section:last-child {
            border-right: none;
        }
        .section h2 {
            color: #4CAF50;
            font-size: 1.5em;
            margin-bottom: 20px;
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
    <div class="main-container">
        <!-- Customer Operations Section -->
        <div class="section">
            <h2>Customer Operations</h2>
            <div class="button-container">
                <form action="CreateCustomer.jsp" method="get">
                    <input type="submit" value="Create Customer" />
                </form>
                <form action="CustomerUpdate.jsp" method="get">
                    <input type="submit" value="Update Customer" />
                </form>
                <form action="CustomerDelete.jsp" method="get">
                    <input type="submit" value="Delete Customer" />
                </form>
            </div>
        </div>

        <!-- Customer Queries Section -->
        <div class="section">
            <h2>Customer Queries</h2>
            <div class="button-container">
                <form action="FindCustomers.jsp" method="get">
                    <input type="submit" value="Find Customers" />
                </form>
                <form action="OrderHistory.jsp" method="get">
                    <input type="submit" value="Order History" />
                </form>
                <form action="findTopPurchasedItemsByUserId.jsp" method="get">
                    <input type="submit" value="Recommend Product by Customer ID" />
                </form>
            </div>
        </div>

        <!-- Seller Queries Section -->
        <div class="section">
            <h2>Seller Queries</h2>
            <div class="button-container">
                <form action="CategoriesByZipcode.jsp" method="get">
                    <input type="submit" value="Categoriy sales By Zipcode" />
                </form>
                <form action="FreightValuesByCategory.jsp" method="get">
                    <input type="submit" value="Freight Values By Category" />
                </form>
                <form action="TopSellingProducts.jsp" method="get">
                    <input type="submit" value="Top Products By Category" />
                </form>
            </div>
        </div>
    </div>
</body>
</html>
