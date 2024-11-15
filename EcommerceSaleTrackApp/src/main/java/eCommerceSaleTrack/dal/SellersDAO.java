package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Sellers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellersDAO {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static SellersDAO instance = null;

  protected SellersDAO() {
    connectionManager = new ConnectionManager();
  }

  public static SellersDAO getInstance() {
    if (instance == null) {
      instance = new SellersDAO();
    }
    return instance;
  }

  /**
   * Save a new seller to the database.
   */
  public Sellers create(Sellers seller) throws SQLException {
    String insertSeller = "INSERT INTO Sellers(SellerId, SellerZipCodePrefix, SellerCity, SellerState) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertSeller);
      insertStmt.setString(1, seller.getSellerId());
      insertStmt.setString(2, seller.getSellerZipCodePrefix());
      insertStmt.setString(3, seller.getSellerCity());
      insertStmt.setString(4, seller.getSellerState());
      insertStmt.executeUpdate();

      return seller;
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
   * Get a seller by SellerId.
   */
  public Sellers getSellerById(String sellerId) throws SQLException {
    String selectSeller = "SELECT SellerId, SellerZipCodePrefix, SellerCity, SellerState FROM Sellers WHERE SellerId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSeller);
      selectStmt.setString(1, sellerId);
      results = selectStmt.executeQuery();

      if (results.next()) {
        String sellerZipCodePrefix = results.getString("SellerZipCodePrefix");
        String sellerCity = results.getString("SellerCity");
        String sellerState = results.getString("SellerState");

        Sellers seller = new Sellers(sellerId, sellerZipCodePrefix, sellerCity, sellerState);
        return seller;
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
   * Update the seller's city and state based on SellerId.
   */
  public Sellers updateSellerCityAndState(Sellers seller, String newCity, String newState) throws SQLException {
    String updateSeller = "UPDATE Sellers SET SellerCity=?, SellerState=? WHERE SellerId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateSeller);
      updateStmt.setString(1, newCity);
      updateStmt.setString(2, newState);
      updateStmt.setString(3, seller.getSellerId());
      updateStmt.executeUpdate();

      // Update the seller param before returning to the caller.
      seller.setSellerCity(newCity);
      seller.setSellerState(newState);
      return seller;
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
   * Delete a seller from the database.
   */
  public Sellers delete(Sellers seller) throws SQLException {
    String deleteSeller = "DELETE FROM Sellers WHERE SellerId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteSeller);
      deleteStmt.setString(1, seller.getSellerId());
      deleteStmt.executeUpdate();

      // Return null to indicate the seller has been deleted.
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
   * Get all sellers from a specific city.
   */
  public List<Sellers> getSellersByCity(String city) throws SQLException {
    List<Sellers> sellers = new ArrayList<>();
    String selectSellers = "SELECT SellerId, SellerZipCodePrefix, SellerCity, SellerState FROM Sellers WHERE SellerCity=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSellers);
      selectStmt.setString(1, city);
      results = selectStmt.executeQuery();

      while (results.next()) {
        String sellerId = results.getString("SellerId");
        String sellerZipCodePrefix = results.getString("SellerZipCodePrefix");
        String sellerCity = results.getString("SellerCity");
        String sellerState = results.getString("SellerState");

        Sellers seller = new Sellers(sellerId, sellerZipCodePrefix, sellerCity, sellerState);
        sellers.add(seller);
      }
      return sellers;
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
