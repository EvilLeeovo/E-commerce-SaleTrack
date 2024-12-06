<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<html>
<head>
    <title>Create Customer</title>
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
            color: #4CAF50;
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
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            font-weight: bold;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .message {
            color: #4CAF50;
            font-weight: bold;
            margin-top: 20px;
            font-size: 1.1em;
        }
        .message.error {
            color: red;
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
    <div class="container">
        <h1>Create a New Customer</h1>
        <form action="createCustomer" method="post">
            <p>
                <label for="customerId">Customer ID:</label>
                <input type="text" id="customerId" name="customerId" placeholder="Enter Customer ID" required />
            </p>
            <p>
                <label for="uniqueId">Unique ID:</label>
                <input type="text" id="uniqueId" name="uniqueId" placeholder="Enter Unique ID" required />
            </p>
            <p>
                <label for="zipCodePrefix">Zip Code Prefix:</label>
                <input type="text" id="zipCodePrefix" name="zipCodePrefix" placeholder="Enter ZIP Code Prefix" />
            </p>
            <p>
                <label for="city">City:</label>
                <input type="text" id="city" name="city" placeholder="Enter City" />
            </p>
            <p>
                <label for="state">State:</label>
                <input type="text" id="state" name="state" placeholder="Enter State" />
            </p>
            <p>
                <input type="submit" value="Create Customer" />
            </p>
            <div class="message">${messages.success}</div>
        </form>
    </div>
</body>
</html>
