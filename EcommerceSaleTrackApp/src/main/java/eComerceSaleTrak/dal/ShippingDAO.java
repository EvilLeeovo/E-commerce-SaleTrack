package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Shipping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShippingDAO {
    protected ConnectionManager connectionManager;

    private static ShippingDAO instance = null;

    protected ShippingDAO() {
        connectionManager = new ConnectionManager();
    }

    public static ShippingDAO getInstance() {
        if (instance == null) {
            instance = new ShippingDAO();
        }
        return instance;
    }

    public Shipping create(Shipping shipping) throws SQLException {
        String insertShipping = "INSERT INTO Shipping(OrderId, CustomerId, ZipCodePrefix, State, TotalFreightValue, TotalItemValue, TotalOrderValue, OrderEstimatedDeliveryDate) VALUES(?,?,?,?,?,?,?,?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertShipping, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, shipping.getOrderId());
            insertStmt.setString(2, shipping.getCustomerId());
            insertStmt.setString(3, shipping.getZipCodePrefix());
            insertStmt.setString(4, shipping.getState());
            insertStmt.setBigDecimal(5, shipping.getTotalFreightValue());
            insertStmt.setBigDecimal(6, shipping.getTotalItemValue());
            insertStmt.setBigDecimal(7, shipping.getTotalOrderValue());
            insertStmt.setTimestamp(8, new Timestamp(shipping.getOrderEstimatedDeliveryDate().getTime()));

            insertStmt.executeUpdate();
            ResultSet keys = insertStmt.getGeneratedKeys();
            if (keys.next()) {
                shipping.setShippingId(keys.getInt(1));
            }
            return shipping;
        }
    }

    public Shipping getShippingById(int shippingId) throws SQLException {
        String selectShipping = "SELECT * FROM Shipping WHERE ShippingId=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectShipping)) {
            selectStmt.setInt(1, shippingId);
            ResultSet results = selectStmt.executeQuery();

            if (results.next()) {
                Shipping shipping = new Shipping(
                        results.getInt("ShippingId"),
                        results.getString("OrderId"),
                        results.getString("CustomerId"),
                        results.getString("ZipCodePrefix"),
                        results.getString("State"),
                        results.getBigDecimal("TotalFreightValue"),
                        results.getBigDecimal("TotalItemValue"),
                        results.getBigDecimal("TotalOrderValue"),
                        results.getTimestamp("OrderEstimatedDeliveryDate")
                );
                return shipping;
            }
            return null;
        }
    }

    public Shipping updateState(Shipping shipping, String newState) throws SQLException {
        String updateShipping = "UPDATE Shipping SET State=? WHERE ShippingId=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateShipping)) {
            updateStmt.setString(1, newState);
            updateStmt.setInt(2, shipping.getShippingId());
            updateStmt.executeUpdate();
            shipping.setState(newState);
            return shipping;
        }
    }

    public Shipping delete(Shipping shipping) throws SQLException {
        String deleteShipping = "DELETE FROM Shipping WHERE ShippingId=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteShipping)) {
            deleteStmt.setInt(1, shipping.getShippingId());
            deleteStmt.executeUpdate();
            return null;
        }
    }
}

