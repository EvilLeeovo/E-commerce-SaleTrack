<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<html>
<head>
    <title>Create Customer</title>
</head>
<body>
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
        <span><b>${messages.success}</b></span>
    </form>
</body>
</html>
