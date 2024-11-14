package eCommerceSaleTrack.model;

public class Products {
	protected String productId;
	protected String productCategoryName;
	protected int productNameLength;
	protected int productDescriptionLength;
	protected int productPhotosQty;
	protected int productWeightG;
	protected int productLengthCm;
	protected int productHeightCm;
	protected int productWidthCm;

	/**
	 * Construct a Products instance, with input arguments:
	 * 
	 * @param productId                is the id
	 * @param productCategoryName      is the category name
	 * @param productNameLength        is the length of the name
	 * @param productDescriptionLength is length of description
	 * @param productPhotosQty         is quantity of the photo
	 * @param productWeightG           is weight in gram
	 * @param productLengthCm          is length in cm
	 * @param productHeightCm          is height in cm
	 * @param productWidthCm           is width in cm
	 */
	public Products(String productId, String productCategoryName, int productNameLength, int productDescriptionLength,
			int productPhotosQty, int productWeightG, int productLengthCm, int productHeightCm, int productWidthCm) {
		this.productId = productId;
		this.productCategoryName = productCategoryName;
		this.productNameLength = productNameLength;
		this.productDescriptionLength = productDescriptionLength;
		this.productPhotosQty = productPhotosQty;
		this.productWeightG = productWeightG;
		this.productLengthCm = productLengthCm;
		this.productHeightCm = productHeightCm;
		this.productWidthCm = productWidthCm;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public int getProductNameLength() {
		return productNameLength;
	}

	public void setProductNameLength(int productNameLength) {
		this.productNameLength = productNameLength;
	}

	public int getProductDescriptionLength() {
		return productDescriptionLength;
	}

	public void setProductDescriptionLength(int productDescriptionLength) {
		this.productDescriptionLength = productDescriptionLength;
	}

	public int getProductPhotosQty() {
		return productPhotosQty;
	}

	public void setProductPhotosQty(int productPhotosQty) {
		this.productPhotosQty = productPhotosQty;
	}

	public int getProductWeightG() {
		return productWeightG;
	}

	public void setProductWeightG(int productWeightG) {
		this.productWeightG = productWeightG;
	}

	public int getProductLengthCm() {
		return productLengthCm;
	}

	public void setProductLengthCm(int productLengthCm) {
		this.productLengthCm = productLengthCm;
	}

	public int getProductHeightCm() {
		return productHeightCm;
	}

	public void setProductHeightCm(int productHeightCm) {
		this.productHeightCm = productHeightCm;
	}

	public int getProductWidthCm() {
		return productWidthCm;
	}

	public void setProductWidthCm(int productWidthCm) {
		this.productWidthCm = productWidthCm;
	}

}
