package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
  protected ConnectionManager connectionManager;

  private static ProductsDAO instance = null;

  protected ProductsDAO() {
    connectionManager = new ConnectionManager();
  }

  public static ProductsDAO getInstance() {
    if (instance == null) {
      instance = new ProductsDAO();
    }
    return instance;
  }

  /**
   * Save a new product to the database.
   */
  public Products create(Products product) throws SQLException {
    String insertProduct = "INSERT INTO Products(ProductId, ProductCategoryName, ProductNameLength, " +
        "ProductDescriptionLength, ProductPhotosQty, ProductWeightG, ProductLengthCm, ProductHeightCm, " +
        "ProductWidthCm) VALUES(?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertProduct);
      insertStmt.setString(1, product.getProductId());
      insertStmt.setString(2, product.getProductCategoryName());
      insertStmt.setInt(3, product.getProductNameLength());
      insertStmt.setInt(4, product.getProductDescriptionLength());
      insertStmt.setInt(5, product.getProductPhotosQty());
      insertStmt.setInt(6, product.getProductWeightG());
      insertStmt.setInt(7, product.getProductLengthCm());
      insertStmt.setInt(8, product.getProductHeightCm());
      insertStmt.setInt(9, product.getProductWidthCm());
      insertStmt.executeUpdate();

      return product;
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
   * Get a product by ProductId.
   */
  public Products getProductById(String productId) throws SQLException {
    String selectProduct = "SELECT ProductId, ProductCategoryName, ProductNameLength, ProductDescriptionLength, " +
        "ProductPhotosQty, ProductWeightG, ProductLengthCm, ProductHeightCm, ProductWidthCm " +
        "FROM Products WHERE ProductId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectProduct);
      selectStmt.setString(1, productId);
      results = selectStmt.executeQuery();

      if (results.next()) {
        String productCategoryName = results.getString("ProductCategoryName");
        int productNameLength = results.getInt("ProductNameLength");
        int productDescriptionLength = results.getInt("ProductDescriptionLength");
        int productPhotosQty = results.getInt("ProductPhotosQty");
        int productWeightG = results.getInt("ProductWeightG");
        int productLengthCm = results.getInt("ProductLengthCm");
        int productHeightCm = results.getInt("ProductHeightCm");
        int productWidthCm = results.getInt("ProductWidthCm");

        Products product = new Products(productId, productCategoryName, productNameLength,
            productDescriptionLength, productPhotosQty, productWeightG,
            productLengthCm, productHeightCm, productWidthCm);
        return product;
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

  public Products updateProductDetails(Products product, String newCategoryName, int newNameLength, int newDescriptionLength,
      int newPhotosQty, int newWeightG, int newLengthCm, int newHeightCm, int newWidthCm) throws SQLException {
    String updateProduct = "UPDATE Products SET ProductCategoryName=?, ProductNameLength=?, ProductDescriptionLength=?, " +
        "ProductPhotosQty=?, ProductWeightG=?, ProductLengthCm=?, ProductHeightCm=?, ProductWidthCm=? WHERE ProductId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateProduct);
      updateStmt.setString(1, newCategoryName);
      updateStmt.setInt(2, newNameLength);
      updateStmt.setInt(3, newDescriptionLength);
      updateStmt.setInt(4, newPhotosQty);
      updateStmt.setInt(5, newWeightG);
      updateStmt.setInt(6, newLengthCm);
      updateStmt.setInt(7, newHeightCm);
      updateStmt.setInt(8, newWidthCm);
      updateStmt.setString(9, product.getProductId());
      updateStmt.executeUpdate();

      product.setProductCategoryName(newCategoryName);
      product.setProductNameLength(newNameLength);
      product.setProductDescriptionLength(newDescriptionLength);
      product.setProductPhotosQty(newPhotosQty);
      product.setProductWeightG(newWeightG);
      product.setProductLengthCm(newLengthCm);
      product.setProductHeightCm(newHeightCm);
      product.setProductWidthCm(newWidthCm);

      return product;
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
   * Delete a product from the database by ProductId.
   */
  public Products delete(Products product) throws SQLException {
    String deleteProduct = "DELETE FROM Products WHERE ProductId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteProduct);
      deleteStmt.setString(1, product.getProductId());
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
