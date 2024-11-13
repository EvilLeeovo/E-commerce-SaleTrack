package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO {
    protected ConnectionManager connectionManager;

    private static ProductCategoryDAO instance = null;

    protected ProductCategoryDAO() {
        connectionManager = new ConnectionManager();
    }

    public static ProductCategoryDAO getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDAO();
        }
        return instance;
    }

    public ProductCategory create(ProductCategory productCategory) throws SQLException {
        String insertCategory = "INSERT INTO ProductCategory(ProductCategoryName, ProductCategoryNameEnglish) VALUES(?,?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertCategory)) {
            insertStmt.setString(1, productCategory.getProductCategoryName());
            insertStmt.setString(2, productCategory.getProductCategoryNameEnglish());
            insertStmt.executeUpdate();
            return productCategory;
        }
    }

    public ProductCategory getProductCategoryByName(String categoryName) throws SQLException {
        String selectCategory = "SELECT ProductCategoryName, ProductCategoryNameEnglish FROM ProductCategory WHERE ProductCategoryName=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectCategory)) {
            selectStmt.setString(1, categoryName);
            ResultSet results = selectStmt.executeQuery();

            if (results.next()) {
                String productCategoryNameEnglish = results.getString("ProductCategoryNameEnglish");
                ProductCategory productCategory = new ProductCategory(categoryName, productCategoryNameEnglish);
                return productCategory;
            }
            return null;
        }
    }

    public ProductCategory updateProductCategoryNameEnglish(ProductCategory productCategory, String newNameEnglish) throws SQLException {
        String updateCategory = "UPDATE ProductCategory SET ProductCategoryNameEnglish=? WHERE ProductCategoryName=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateCategory)) {
            updateStmt.setString(1, newNameEnglish);
            updateStmt.setString(2, productCategory.getProductCategoryName());
            updateStmt.executeUpdate();
            productCategory.setProductCategoryNameEnglish(newNameEnglish);
            return productCategory;
        }
    }

    public ProductCategory delete(ProductCategory productCategory) throws SQLException {
        String deleteCategory = "DELETE FROM ProductCategory WHERE ProductCategoryName=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteCategory)) {
            deleteStmt.setString(1, productCategory.getProductCategoryName());
            deleteStmt.executeUpdate();
            return null;
        }
    }
}
