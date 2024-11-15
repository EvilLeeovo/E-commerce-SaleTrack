package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static CustomersDAO instance = null;

    protected CustomersDAO() {
        connectionManager = new ConnectionManager();
    }

    public static CustomersDAO getInstance() {
        if (instance == null) {
            instance = new CustomersDAO();
        }
        return instance;
    }

    /**
     * Save a new customer to the database.
     */
    public Customers create(Customers customer) throws SQLException {
        String insertCustomer = "INSERT INTO Customers(CustomerId, CustomerUniqueId, CustomerZipCodePrefix, CustomerCity, CustomerState) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, customer.getCustomerId());
            insertStmt.setString(2, customer.getCustomerUniqueId());
            insertStmt.setString(3, customer.getCustomerZipCodePrefix());
            insertStmt.setString(4, customer.getCustomerCity());
            insertStmt.setString(5, customer.getCustomerState());
            insertStmt.executeUpdate();
            
            return customer;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get a customer by CustomerId.
     */
    public Customers getCustomerById(String customerId) throws SQLException {
        String selectCustomer = "SELECT CustomerId, CustomerUniqueId, CustomerZipCodePrefix, CustomerCity, CustomerState FROM Customers WHERE CustomerId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCustomer);
            selectStmt.setString(1, customerId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String customerUniqueId = results.getString("CustomerUniqueId");
                String customerZipCodePrefix = results.getString("CustomerZipCodePrefix");
                String customerCity = results.getString("CustomerCity");
                String customerState = results.getString("CustomerState");

                Customers customer = new Customers(customerId, customerUniqueId, customerZipCodePrefix, customerCity, customerState);
                return customer;
            }
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    /**
     * Update the customer's city and state based on CustomerId.
     */
    public Customers updateCustomerCityAndState(Customers customer, String newCity, String newState) throws SQLException {
        String updateCustomer = "UPDATE Customers SET CustomerCity=?, CustomerState=? WHERE CustomerId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCustomer);
            updateStmt.setString(1, newCity);
            updateStmt.setString(2, newState);
            updateStmt.setString(3, customer.getCustomerId());
            updateStmt.executeUpdate();

            // Update the customer param before returning to the caller.
            customer.setCustomerCity(newCity);
            customer.setCustomerState(newState);
            return customer;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Delete a customer from the database.
     */
    public Customers delete(Customers customer) throws SQLException {
        String deleteCustomer = "DELETE FROM Customers WHERE CustomerId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCustomer);
            deleteStmt.setString(1, customer.getCustomerId());
            deleteStmt.executeUpdate();

            // Return null to indicate the customer has been deleted.
            return null;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public boolean deleteByCustomerId(String customerId) throws SQLException {
        String deleteCustomer = "DELETE FROM Customers WHERE CustomerId = ?;";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(deleteCustomer);
            statement.setString(1, customerId);
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0; // If affectedRows > 0, delete was successful
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Get all customers from a specific city.
     */
    public List<Customers> getCustomersByCity(String city) throws SQLException {
        List<Customers> customers = new ArrayList<>();
        String selectCustomers = "SELECT CustomerId, CustomerUniqueId, CustomerZipCodePrefix, CustomerCity, CustomerState FROM Customers WHERE CustomerCity=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCustomers);
            selectStmt.setString(1, city);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String customerId = results.getString("CustomerId");
                String customerUniqueId = results.getString("CustomerUniqueId");
                String customerZipCodePrefix = results.getString("CustomerZipCodePrefix");
                String customerCity = results.getString("CustomerCity");
                String customerState = results.getString("CustomerState");

                Customers customer = new Customers(customerId, customerUniqueId, customerZipCodePrefix, customerCity, customerState);
                customers.add(customer);
            }
            return customers;
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
