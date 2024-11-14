package eCommerceSaleTrack.model;

public class ZipcodeCategoryDTO {
  private String CustomerZipCodePrefix;
  private String ProductCategoryNameEnglish;
  private int Count;

  public String getCustomerZipCodePrefix() {
    return CustomerZipCodePrefix;
  }

  public void setCustomerZipCodePrefix(String customerZipCodePrefix) {
    CustomerZipCodePrefix = customerZipCodePrefix;
  }

  public String getProductCategoryNameEnglish() {
    return ProductCategoryNameEnglish;
  }

  public void setProductCategoryNameEnglish(String productCategoryNameEnglish) {
    ProductCategoryNameEnglish = productCategoryNameEnglish;
  }

  public int getCount() {
    return Count;
  }

  public void setCount(int count) {
    Count = count;
  }
}