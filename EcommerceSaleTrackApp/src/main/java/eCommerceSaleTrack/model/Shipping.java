package eCommerceSaleTrack.model;

import java.util.Date;

public class Shipping {
	protected int shippingId;
	protected String orderId;
	protected String customerId;
	protected String zipCodePrefix;
	protected String state;
	protected double totalFreightValue;
	protected double totalItemValue;
	protected double totalOrderValue;
	protected Date orderEstimatedDeliveryDate;

	/**
	 * Construct a Shipping instance, with input arguments:
	 * 
	 * @param shippingId                 is the shipping id
	 * @param orderId                    is the order id
	 * @param customerId                 is customer id
	 * @param zipCodePrefix              is zip code
	 * @param state                      is state
	 * @param totalFreightValue          is total freight value
	 * @param totalItemValue             is total item value
	 * @param totalOrderValue            is total order value
	 * @param orderEstimatedDeliveryDate is estimated delivered date
	 */
	public Shipping(int shippingId, String orderId, String customerId, String zipCodePrefix, String state,
			double totalFreightValue, double totalItemValue, double totalOrderValue, Date orderEstimatedDeliveryDate) {
		super();
		this.shippingId = shippingId;
		this.orderId = orderId;
		this.customerId = customerId;
		this.zipCodePrefix = zipCodePrefix;
		this.state = state;
		this.totalFreightValue = totalFreightValue;
		this.totalItemValue = totalItemValue;
		this.totalOrderValue = totalOrderValue;
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}

	/**
	 * Construct a Shipping instance, using auto-generate shipping id by mySQL, with
	 * input arguments:
	 * 
	 * @param orderId                    is the order id
	 * @param customerId                 is customer id
	 * @param zipCodePrefix              is zip code
	 * @param state                      is state
	 * @param totalFreightValue          is total freight value
	 * @param totalItemValue             is total item value
	 * @param totalOrderValue            is total order value
	 * @param orderEstimatedDeliveryDate is estimated delivered date
	 */
	public Shipping(String orderId, String customerId, String zipCodePrefix, String state, double totalFreightValue,
			double totalItemValue, double totalOrderValue, Date orderEstimatedDeliveryDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.zipCodePrefix = zipCodePrefix;
		this.state = state;
		this.totalFreightValue = totalFreightValue;
		this.totalItemValue = totalItemValue;
		this.totalOrderValue = totalOrderValue;
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}

	public int getShippingId() {
		return shippingId;
	}

	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getZipCodePrefix() {
		return zipCodePrefix;
	}

	public void setZipCodePrefix(String zipCodePrefix) {
		this.zipCodePrefix = zipCodePrefix;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getTotalFreightValue() {
		return totalFreightValue;
	}

	public void setTotalFreightValue(double totalFreightValue) {
		this.totalFreightValue = totalFreightValue;
	}

	public double getTotalItemValue() {
		return totalItemValue;
	}

	public void setTotalItemValue(double totalItemValue) {
		this.totalItemValue = totalItemValue;
	}

	public double getTotalOrderValue() {
		return totalOrderValue;
	}

	public void setTotalOrderValue(double totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}

	public Date getOrderEstimatedDeliveryDate() {
		return orderEstimatedDeliveryDate;
	}

	public void setOrderEstimatedDeliveryDate(Date orderEstimatedDeliveryDate) {
		this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
	}

}
