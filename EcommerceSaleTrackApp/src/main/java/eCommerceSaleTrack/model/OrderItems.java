package eCommerceSaleTrack.model;

import java.util.Date;

public class OrderItems {
	protected String orderId;
	protected int orderItemId;
	protected String productId;
	protected String sellerId;
	protected Date shippingLimitDate;
	protected double price;
	protected double freightValue;

	/**
	 * Construct a OrderItems instance, with input arguments:
	 * 
	 * @param orderId           is the order id
	 * @param orderItemId       is the order item id
	 * @param productId         is product id
	 * @param sellerId          is seller's id
	 * @param shippingLimitDate is shipping limit date
	 * @param price             is the price
	 * @param freightValue      is the freight value
	 */
	public OrderItems(String orderId, int orderItemId, String productId, String sellerId, Date shippingLimitDate,
			double price, double freightValue) {
		super();
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.shippingLimitDate = shippingLimitDate;
		this.price = price;
		this.freightValue = freightValue;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Date getShippingLimitDate() {
		return shippingLimitDate;
	}

	public void setShippingLimitDate(Date shippingLimitDate) {
		this.shippingLimitDate = shippingLimitDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(double freightValue) {
		this.freightValue = freightValue;
	}

}
