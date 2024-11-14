package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.dto.ZipcodeCategoryDTO;
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

    public List<ZipcodeCategoryDTO> getCategoriesByZipcode(String zipcode) throws SQLException {
      List<ZipcodeCategoryDTO> categoryDTOList = new ArrayList<>();
      try (Connection connection = connectionManager.getConnection();) {
        PreparedStatement getStmt = connection.prepareStatement("select Customers.CustomerZipCodePrefix, ProductOrder.ProductCategoryNameEnglish, count(*) as Count from Customers\n" +
                "inner join (\n" +
                "\tselect C.ProductCategoryNameEnglish, O.CustomerId from (\n" +
                "\t\t\tselect Products.ProductId, ProductCategory.ProductCategoryNameEnglish \n" +
                "\t\t\tfrom Products inner join ProductCategory \n" +
                "\t\t\ton Products.ProductCategoryName = ProductCategory.ProductCategoryName\n" +
                "\t\t) as C\n" +
                "\t\tinner join (\n" +
                "\t\t\tselect Orders.OrderId, Orders.CustomerId, OrderItems.ProductId\n" +
                "\t\t\tfrom Orders inner join OrderItems\n" +
                "\t\t\ton Orders.OrderId = OrderItems.OrderId\n" +
                "\t\t) as O\n" +
                "\t\ton C.ProductId = O.ProductId\n" +
                ") as ProductOrder\n" +
                "on Customers.CustomerId = ProductOrder.CustomerId\n" +
                "group by Customers.CustomerZipCodePrefix, ProductOrder.ProductCategoryNameEnglish\n" +
                "having Customers.CustomerZipCodePrefix = ?\n" +
                "order by count(*) desc");
        getStmt.setString(1, zipcode);
        ResultSet rs = getStmt.executeQuery();
        while (rs.next()) {
          ZipcodeCategoryDTO zipcodeCategoryDTO = new ZipcodeCategoryDTO();
          zipcodeCategoryDTO.setCustomerZipCodePrefix(rs.getString("CustomerZipCodePrefix"));
          zipcodeCategoryDTO.setProductCategoryNameEnglish(rs.getString("ProductCategoryNameEnglish"));
          zipcodeCategoryDTO.setCount(Integer.parseInt(rs.getString("Count")));
          categoryDTOList.add(zipcodeCategoryDTO);
        }
        return categoryDTOList;
      }
    }
}
