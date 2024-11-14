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
        <h1>Create a New Customer</h1>
        <form action="createCustomer" method="post">
            <p>
                <label for="customerId">Customer ID:</label>
                <input type="text" id="customerId" name="customerId" required />
            </p>
            <p>
                <label for="uniqueId">Unique ID:</label>
                <input type="text" id="uniqueId" name="uniqueId" required />
            </p>
            <p>
                <label for="zipCodePrefix">Zip Code Prefix:</label>
                <input type="text" id="zipCodePrefix" name="zipCodePrefix" />
            </p>
            <p>
                <label for="city">City:</label>
                <input type="text" id="city" name="city" />
            </p>
            <p>
                <label for="state">State:</label>
                <input type="text" id="state" name="state" />
            </p>
            <p>
                <input type="submit" value="Create Customer" />
            </p>
            <div class="message">${messages.success}</div>
        </form>
    </div>
</body>
</html>
