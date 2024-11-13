package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.OrderPayments;
import eCommerceSaleTrack.model.OrderPayments.PaymentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderPaymentsDAO {
    protected ConnectionManager connectionManager;

    // Singleton pattern: limit instantiation to one object.
    private static OrderPaymentsDAO instance = null;

    protected OrderPaymentsDAO() {
        connectionManager = new ConnectionManager();
    }

    public static OrderPaymentsDAO getInstance() {
        if (instance == null) {
            instance = new OrderPaymentsDAO();
        }
        return instance;
    }

    /**
     * Save a new OrderPayments to the database.
     */
    public OrderPayments create(OrderPayments orderPayment) throws SQLException {
        String insertOrderPayment = "INSERT INTO OrderPayments(OrderId, PaymentSequential, PaymentType, PaymentInstallments, PaymentValue) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOrderPayment, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, orderPayment.getOrderId());
            insertStmt.setInt(2, orderPayment.getPaymentSequential());
            insertStmt.setString(3, orderPayment.getPaymentType().name());
            insertStmt.setInt(4, orderPayment.getPaymentInstallments());
            insertStmt.setDouble(5, orderPayment.getPaymentValue());
            insertStmt.executeUpdate();
            
            return orderPayment;
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
     * Get an OrderPayments record by OrderId and PaymentSequential.
     */
    public OrderPayments getOrderPaymentById(String orderId, int paymentSequential) throws SQLException {
        String selectOrderPayment = "SELECT OrderId, PaymentSequential, PaymentType, PaymentInstallments, PaymentValue FROM OrderPayments WHERE OrderId=? AND PaymentSequential=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderPayment);
            selectStmt.setString(1, orderId);
            selectStmt.setInt(2, paymentSequential);
            results = selectStmt.executeQuery();

            if (results.next()) {
                PaymentType paymentType = PaymentType.valueOf(results.getString("PaymentType"));
                int paymentInstallments = results.getInt("PaymentInstallments");
                double paymentValue = results.getDouble("PaymentValue");

                OrderPayments orderPayment = new OrderPayments(orderId, paymentSequential, paymentType, paymentInstallments, paymentValue);
                return orderPayment;
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
     * Update the payment type and value of an OrderPayment record.
     */
    public OrderPayments updatePaymentTypeAndValue(OrderPayments orderPayment, PaymentType newPaymentType, double newPaymentValue) throws SQLException {
        String updateOrderPayment = "UPDATE OrderPayments SET PaymentType=?, PaymentValue=? WHERE OrderId=? AND PaymentSequential=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateOrderPayment);
            updateStmt.setString(1, newPaymentType.name());
            updateStmt.setDouble(2, newPaymentValue);
            updateStmt.setString(3, orderPayment.getOrderId());
            updateStmt.setInt(4, orderPayment.getPaymentSequential());
            updateStmt.executeUpdate();

            // Update the orderPayment instance before returning.
            orderPayment.setPaymentType(newPaymentType);
            orderPayment.setPaymentValue(newPaymentValue);
            return orderPayment;
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
     * Delete an OrderPayment by OrderId and PaymentSequential.
     */
    public OrderPayments delete(OrderPayments orderPayment) throws SQLException {
        String deleteOrderPayment = "DELETE FROM OrderPayments WHERE OrderId=? AND PaymentSequential=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteOrderPayment);
            deleteStmt.setString(1, orderPayment.getOrderId());
            deleteStmt.setInt(2, orderPayment.getPaymentSequential());
            deleteStmt.executeUpdate();

            // Return null to indicate the record has been deleted.
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

    /**
     * Get all OrderPayments by a specific PaymentType.
     */
    public List<OrderPayments> getOrderPaymentsByPaymentType(PaymentType paymentType) throws SQLException {
        List<OrderPayments> orderPaymentsList = new ArrayList<>();
        String selectOrderPayments = "SELECT OrderId, PaymentSequential, PaymentType, PaymentInstallments, PaymentValue FROM OrderPayments WHERE PaymentType=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderPayments);
            selectStmt.setString(1, paymentType.name());
            results = selectStmt.executeQuery();

            while (results.next()) {
                String orderId = results.getString("OrderId");
                int paymentSequential = results.getInt("PaymentSequential");
                int paymentInstallments = results.getInt("PaymentInstallments");
                double paymentValue = results.getDouble("PaymentValue");

                OrderPayments orderPayment = new OrderPayments(orderId, paymentSequential, paymentType, paymentInstallments, paymentValue);
                orderPaymentsList.add(orderPayment);
            }
            return orderPaymentsList;
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
