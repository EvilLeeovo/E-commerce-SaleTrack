package eCommerceSaleTrack.model;

public class Sellers {
	protected String sellerId;
	protected String sellerZipCodePrefix;
	protected String sellerCity;
	protected String sellerState;

	/**
	 * Construct a Customers instance, with input arguments:
	 * 
	 * @param sellerId            is seller's id
	 * @param sellerZipCodePrefix is zip code
	 * @param sellerCity          is city
	 * @param sellerState         is state
	 */
	public Sellers(String sellerId, String sellerZipCodePrefix, String sellerCity, String sellerState) {
		this.sellerId = sellerId;
		this.sellerZipCodePrefix = sellerZipCodePrefix;
		this.sellerCity = sellerCity;
		this.sellerState = sellerState;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerZipCodePrefix() {
		return sellerZipCodePrefix;
	}

	public void setSellerZipCodePrefix(String sellerZipCodePrefix) {
		this.sellerZipCodePrefix = sellerZipCodePrefix;
	}

	public String getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}

	public String getSellerState() {
		return sellerState;
	}

	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}

}
