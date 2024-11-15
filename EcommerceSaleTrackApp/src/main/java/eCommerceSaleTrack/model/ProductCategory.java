package eCommerceSaleTrack.model;

public class ProductCategory {
	protected String productCategoryName;
	protected String productCategoryNameEnglish;

	/**
	 * Construct a ProductCategory instance, with input arguments:
	 * 
	 * @param productCategoryName        is the category name
	 * @param productCategoryNameEnglish is the category name in English
	 */
	public ProductCategory(String productCategoryName, String productCategoryNameEnglish) {
		this.productCategoryName = productCategoryName;
		this.productCategoryNameEnglish = productCategoryNameEnglish;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getProductCategoryNameEnglish() {
		return productCategoryNameEnglish;
	}

	public void setProductCategoryNameEnglish(String productCategoryNameEnglish) {
		this.productCategoryNameEnglish = productCategoryNameEnglish;
	}

}
