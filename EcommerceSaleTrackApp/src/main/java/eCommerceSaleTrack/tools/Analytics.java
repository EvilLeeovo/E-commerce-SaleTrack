package eCommerceSaleTrack.tools;

import eCommerceSaleTrack.dal.ProductCategoryDAO;
import eCommerceSaleTrack.dto.ZipcodeCategoryDTO;

import java.sql.SQLException;
import java.util.List;

public class Analytics {
  public static void main(String[] args) throws SQLException {
    // DAO instances
    ProductCategoryDAO productCategoryDAO = ProductCategoryDAO.getInstance();


    // Find categories bought by geolocation
    int zipcode = 11065;
    List<ZipcodeCategoryDTO> categoryByZipcode = productCategoryDAO.getCategoriesByZipcode(String.valueOf(zipcode));
    for (ZipcodeCategoryDTO zipcodeCategoryDTO : categoryByZipcode) {
      System.out.println("Category: " + zipcodeCategoryDTO.getProductCategoryNameEnglish());
      System.out.println("ZipCode: " + zipcodeCategoryDTO.getCustomerZipCodePrefix());
      System.out.println("Count: " + zipcodeCategoryDTO.getCount());
      System.out.println("________");
    }
  }
}
