package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.OrderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDAO {
    protected ConnectionManager connectionManager;

    // Singleton pattern: restricts instantiation to one object.
    private static OrderItemsDAO instance = null;

    protected OrderItemsDAO() {
        connectionManager = new ConnectionManager();
    }

    public static OrderItemsDAO getInstance() {
        if (instance == null) {
            instance = new OrderItemsDAO();
        }
        return instance;
    }

    /**
     * Save a new OrderItem to the database.
     */
    public OrderItems create(OrderItems orderItem) throws SQLException {
        String insertOrderItem = "INSERT INTO OrderItems(OrderId, OrderItemId, ProductId, SellerId, ShippingLimitDate, Price, FreightValue) VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOrderItem, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, orderItem.getOrderId());
            insertStmt.setInt(2, orderItem.getOrderItemId());
            insertStmt.setString(3, orderItem.getProductId());
            insertStmt.setString(4, orderItem.getSellerId());
            insertStmt.setDate(5, new Date(orderItem.getShippingLimitDate().getTime()));
            insertStmt.setDouble(6, orderItem.getPrice());
            insertStmt.setDouble(7, orderItem.getFreightValue());
            insertStmt.executeUpdate();
            
            return orderItem;
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
     * Get an OrderItem by OrderId and OrderItemId.
     */
    public OrderItems getOrderItemById(String orderId, int orderItemId) throws SQLException {
        String selectOrderItem = "SELECT OrderId, OrderItemId, ProductId, SellerId, ShippingLimitDate, Price, FreightValue FROM OrderItems WHERE OrderId=? AND OrderItemId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderItem);
            selectStmt.setString(1, orderId);
            selectStmt.setInt(2, orderItemId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String productId = results.getString("ProductId");
                String sellerId = results.getString("SellerId");
                Date shippingLimitDate = results.getDate("ShippingLimitDate");
                double price = results.getDouble("Price");
                double freightValue = results.getDouble("FreightValue");

                OrderItems orderItem = new OrderItems(orderId, orderItemId, productId, sellerId, shippingLimitDate, price, freightValue);
                return orderItem;
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
     * Update the price and freight value of an OrderItem.
     */
    public OrderItems updatePriceAndFreightValue(OrderItems orderItem, double newPrice, double newFreightValue) throws SQLException {
        String updateOrderItem = "UPDATE OrderItems SET Price=?, FreightValue=? WHERE OrderId=? AND OrderItemId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateOrderItem);
            updateStmt.setDouble(1, newPrice);
            updateStmt.setDouble(2, newFreightValue);
            updateStmt.setString(3, orderItem.getOrderId());
            updateStmt.setInt(4, orderItem.getOrderItemId());
            updateStmt.executeUpdate();

            // Update the orderItem instance before returning.
            orderItem.setPrice(newPrice);
            orderItem.setFreightValue(newFreightValue);
            return orderItem;
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
     * Delete an OrderItem by OrderId and OrderItemId.
     */
    public OrderItems delete(OrderItems orderItem) throws SQLException {
        String deleteOrderItem = "DELETE FROM OrderItems WHERE OrderId=? AND OrderItemId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteOrderItem);
            deleteStmt.setString(1, orderItem.getOrderId());
            deleteStmt.setInt(2, orderItem.getOrderItemId());
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
     * Get all OrderItems by a specific seller's ID.
     */
    public List<OrderItems> getOrderItemsBySellerId(String sellerId) throws SQLException {
        List<OrderItems> orderItemsList = new ArrayList<>();
        String selectOrderItems = "SELECT OrderId, OrderItemId, ProductId, SellerId, ShippingLimitDate, Price, FreightValue FROM OrderItems WHERE SellerId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderItems);
            selectStmt.setString(1, sellerId);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String orderId = results.getString("OrderId");
                int orderItemId = results.getInt("OrderItemId");
                String productId = results.getString("ProductId");
                Date shippingLimitDate = results.getDate("ShippingLimitDate");
                double price = results.getDouble("Price");
                double freightValue = results.getDouble("FreightValue");

                OrderItems orderItem = new OrderItems(orderId, orderItemId, productId, sellerId, shippingLimitDate, price, freightValue);
                orderItemsList.add(orderItem);
            }
            return orderItemsList;
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
