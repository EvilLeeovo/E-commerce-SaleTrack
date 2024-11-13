package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Orders;
import eCommerceSaleTrack.model.Orders.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDAO {
  protected ConnectionManager connectionManager;

  private static OrdersDAO instance = null;

  protected OrdersDAO() {
    connectionManager = new ConnectionManager();
  }

  public static OrdersDAO getInstance() {
    if (instance == null) {
      instance = new OrdersDAO();
    }
    return instance;
  }

  /**
   * Save a new order to the database.
   */
  public Orders create(Orders order) throws SQLException {
    String insertOrder = "INSERT INTO Orders(OrderId, CustomerId, OrderStatus, OrderPurchaseTimestamp, " +
        "OrderApprovedAt, OrderDeliveredCarrierDate, OrderDeliveredCustomerDate, OrderEstimatedDeliveryDate) " +
        "VALUES(?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertOrder);
      insertStmt.setString(1, order.getOrderId());
      insertStmt.setString(2, order.getCustomerId());
      insertStmt.setString(3, order.getOrderStatus().name());
      insertStmt.setTimestamp(4, order.getOrderPurchaseTimestamp() != null ?
          new Timestamp(order.getOrderPurchaseTimestamp().getTime()) : null);
      insertStmt.setTimestamp(5, order.getOrderApprovedAt() != null ?
          new Timestamp(order.getOrderApprovedAt().getTime()) : null);
      insertStmt.setTimestamp(6, order.getOrderDeliveredCarrierDate() != null ?
          new Timestamp(order.getOrderDeliveredCarrierDate().getTime()) : null);
      insertStmt.setTimestamp(7, order.getOrderDeliveredCustomerDate() != null ?
          new Timestamp(order.getOrderDeliveredCustomerDate().getTime()) : null);
      insertStmt.setTimestamp(8, order.getOrderEstimatedDeliveryDate() != null ?
          new Timestamp(order.getOrderEstimatedDeliveryDate().getTime()) : null);
      insertStmt.executeUpdate();

      return order;
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
   * Get an order by OrderId.
   */
  public Orders getOrderById(String orderId) throws SQLException {
    String selectOrder = "SELECT OrderId, CustomerId, OrderStatus, OrderPurchaseTimestamp, " +
        "OrderApprovedAt, OrderDeliveredCarrierDate, OrderDeliveredCustomerDate, OrderEstimatedDeliveryDate " +
        "FROM Orders WHERE OrderId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectOrder);
      selectStmt.setString(1, orderId);
      results = selectStmt.executeQuery();

      if (results.next()) {
        String customerId = results.getString("CustomerId");
        OrderStatus orderStatus = OrderStatus.valueOf(results.getString("OrderStatus"));
        Date orderPurchaseTimestamp = new Date(results.getTimestamp("OrderPurchaseTimestamp").getTime());
        Date orderApprovedAt = new Date(results.getTimestamp("OrderApprovedAt").getTime());
        Date orderDeliveredCarrierDate = new Date(results.getTimestamp("OrderDeliveredCarrierDate").getTime());
        Date orderDeliveredCustomerDate = new Date(results.getTimestamp("OrderDeliveredCustomerDate").getTime());
        Date orderEstimatedDeliveryDate = new Date(results.getTimestamp("OrderEstimatedDeliveryDate").getTime());

        Orders order = new Orders(orderId, customerId, orderStatus, orderPurchaseTimestamp, orderApprovedAt,
            orderDeliveredCarrierDate, orderDeliveredCustomerDate, orderEstimatedDeliveryDate);
        return order;
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
   * Get orders by CustomerId.
   */
  public List<Orders> getOrdersByCustomerId(String customerId) throws SQLException {
    List<Orders> ordersList = new ArrayList<>();
    String selectOrders = "SELECT OrderId, CustomerId, OrderStatus, OrderPurchaseTimestamp, " +
        "OrderApprovedAt, OrderDeliveredCarrierDate, OrderDeliveredCustomerDate, OrderEstimatedDeliveryDate " +
        "FROM Orders WHERE CustomerId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectOrders);
      selectStmt.setString(1, customerId);
      results = selectStmt.executeQuery();

      while (results.next()) {
        String orderId = results.getString("OrderId");
        OrderStatus orderStatus = OrderStatus.valueOf(results.getString("OrderStatus"));
        Date orderPurchaseTimestamp = results.getTimestamp("OrderPurchaseTimestamp") != null ? 
            new Date(results.getTimestamp("OrderPurchaseTimestamp").getTime()) : null;
        Date orderApprovedAt = results.getTimestamp("OrderApprovedAt") != null ? 
            new Date(results.getTimestamp("OrderApprovedAt").getTime()) : null;
        Date orderDeliveredCarrierDate = results.getTimestamp("OrderDeliveredCarrierDate") != null ? 
            new Date(results.getTimestamp("OrderDeliveredCarrierDate").getTime()) : null;
        Date orderDeliveredCustomerDate = results.getTimestamp("OrderDeliveredCustomerDate") != null ? 
            new Date(results.getTimestamp("OrderDeliveredCustomerDate").getTime()) : null;
        Date orderEstimatedDeliveryDate = results.getTimestamp("OrderEstimatedDeliveryDate") != null ? 
            new Date(results.getTimestamp("OrderEstimatedDeliveryDate").getTime()) : null;

        Orders order = new Orders(orderId, customerId, orderStatus, orderPurchaseTimestamp, orderApprovedAt,
            orderDeliveredCarrierDate, orderDeliveredCustomerDate, orderEstimatedDeliveryDate);
        ordersList.add(order);
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
    return ordersList;
  }

  /**
   * Update the order status.
   */
  public Orders updateOrderStatus(Orders order, OrderStatus newStatus) throws SQLException {
    String updateOrder = "UPDATE Orders SET OrderStatus=? WHERE OrderId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateOrder);
      updateStmt.setString(1, newStatus.name());
      updateStmt.setString(2, order.getOrderId());
      updateStmt.executeUpdate();

      // Update the order object before returning.
      order.setOrderStatus(newStatus);
      return order;
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
   * Delete an order from the database by OrderId.
   */
  public Orders delete(Orders order) throws SQLException {
    String deleteOrder = "DELETE FROM Orders WHERE OrderId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteOrder);
      deleteStmt.setString(1, order.getOrderId());
      deleteStmt.executeUpdate();

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
}
